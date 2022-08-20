
import jdk.nashorn.internal.scripts.JO;

import java.beans.beancontext.BeanContext;
import java.util.*;

public class EmulateMemoryManagement {
    public final int TOTAL_MEMORY = 20 * 1024;
    public final int PAGE_SIZE = 1024;
    private int[] frames;
    private Job[] jobs;
    private int steps;

    public EmulateMemoryManagement(Job[] jobs) {
        this.jobs = jobs;
        this.frames = new int[TOTAL_MEMORY / PAGE_SIZE];
    }

    public boolean execute() {
        System.out.println("----------------------------------");
        System.out.println("Time " + ++steps);
        printFrameSnapshot();

        Map<Job, Integer> jobs = new LinkedHashMap<>();
        Map<Job, Integer> jobStarting = new LinkedHashMap<>();
        for (int i = 0; i < this.jobs.length; i++) {
            Job job = this.jobs[i];
            if (job != null) {
                jobs.put(job, i);
                if (job.startTime == steps || (job.startTime < steps && job.heldPages == null)) {
                    jobStarting.put(job, i);
                }
            }
        }
        for (Job job: jobStarting.keySet()) {
            System.out.println("\t" + job + " - ready: " + job.requiredPages + " pages required");
            int[] firstFit = findFirstFit(job.requiredPages);
            int[] bestFit = findBestFit(job.requiredPages);
            if (!Arrays.equals(firstFit, bestFit)) {
                System.out.println("FirstFit and BestFit select different pages");
            }
//            int[] fit = bestFit;
            int[] fit = firstFit;
            if (fit == null) {
                System.out.println("\t" + job + " - skipped: could not find " + job.requiredPages + " empty pages");
            } else {
                for (int i = fit[0]; i < fit[1]; i++) {
                    frames[i] = 1;
                }
                job.heldPages = fit;
                System.out.println("\t" + job + " - allocated: pages " + fit[0] + "-" + fit[1]);
            }


        }
        Map<Job, Integer> jobsRunning = new LinkedHashMap<>();
        for (Job job: jobs.keySet()) {
            if (job.startTime <= steps && job.executionInterval > 0 && job.heldPages != null) {
                jobsRunning.put(job, jobs.get(job));
            }
        }
        for (Job job: jobsRunning.keySet()) {
            int length = job.heldPages[1] - job.heldPages[0];
            if (length > 0) {
                System.out.println("\t" + job + " - running: " + --job.executionInterval + " steps remaining");
            } else {
                System.out.println("\t" + job + " - not running");
            }
            if (job.executionInterval == 0) {
                System.out.println("\t" + job + " - finishing: transitioned to " + job.finishState);
//                switch (job.finishState) {
//                    case End:
                        for (int i = job.heldPages[0]; i < job.heldPages[1]; i++) {
                            frames[i] = 0;
                        }
                        System.out.println("\t" + job + " - released: " + job.heldPages[0] + "-" + job.heldPages[1] + " pages");
                        this.jobs[jobs.get(job)] = null;
//                        break;
//                    case Sleep:
//                        break;
//                }
            }
        }
        Map<Job, Integer> jobsSleeping = new LinkedHashMap<>();
        for (Job job: jobs.keySet()) {
            if (job.startTime <= steps && job.executionInterval == 0) {
                jobsSleeping.put(job, jobs.get(job));
            }
        }
        for (Job job: jobsSleeping.keySet()) {
            System.out.println("\t" + job + " - sleeping");
        }
        printFrameSnapshot();
        System.out.println("Time " + steps + " finished");
        System.out.println("----------------------------------");
        System.out.println();
        System.out.println();

        for(Job job: jobs.keySet()) {
            if (job.startTime > steps || job.executionInterval > 0) {
                return false;
            }
        }
        return true;
    }


    public int[] findFirstFit(int sizeAtLeast) {
        List<int[]> allFree = findAllUnoccupied();
        for(int[] af: allFree) {
            if (af[1] - af[0] >= sizeAtLeast) {
                return new int[]{af[0], af[0] + sizeAtLeast};
            }
        }
        return null;
    }

    public int[] findBestFit(int sizeAtLeast) {
        int minSize = frames.length + 1;
        int[] res = null;
        List<int[]> allFree = findAllUnoccupied();
        for (int[] af: allFree) {
            int currSize = af[1] - af[0];
            if (currSize >= sizeAtLeast) {
                if (minSize > currSize) {
                    res =  new int[]{af[0], af[0]+sizeAtLeast};
                    minSize = currSize;
                }
            }
        }
        return res;
    }

    public List<int[]> findAllUnoccupied() {
        List<int[]> allFreePages = new ArrayList<>();
        int[] freePage = null;
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == 0) {
                if (freePage == null) {
                    freePage = new int[2];
                    freePage[0] = i;
                    freePage[1] = i+1;
                } else {
                    freePage[1] = i + 1;
                }
            } else if (freePage != null) {
                allFreePages.add(freePage);
                freePage = null;
            }
        }
        if (freePage != null) allFreePages.add(freePage);
        return allFreePages;
    }


    public void printFrameSnapshot() {
        System.out.print("Frame Occupied: ");
        for (int i = 0; i < frames.length; i++) {
            System.out.print(frames[i] > 0 ? "#" : "~");
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        Job[] jobs = new Job[]{
                new Job(1, 1, 2, 7, JobFinishState.End),
                new Job(2, 2, 3, 8, JobFinishState.Sleep),
                new Job(3, 3, 4, 6, JobFinishState.End),
                new Job(4, 4, 3, 6, JobFinishState.Sleep),
                new Job(5, 5, 2, 9, JobFinishState.Sleep),
                new Job(6, 6, 3, 6, JobFinishState.Sleep),
                new Job(7, 7, 2, 6, JobFinishState.Sleep),
                new Job(8,8,3,4,JobFinishState.Sleep),
                new Job(9,9,5,5,JobFinishState.Sleep),
                new Job(10,10,2,8,JobFinishState.Sleep),
                new Job(11, 11, 4, 6, JobFinishState.End),
                new Job(12, 12, 6, 5, JobFinishState.Sleep),
                new Job(2, 13, 3, 6, JobFinishState.End ),
                new Job(4, 13, 3, 4, JobFinishState.Sleep),
                new Job(13, 13, 5, 3, JobFinishState.End),
                new Job(7, 13, 2, 3, JobFinishState.End),
                new Job(9, 17, 4, 4, JobFinishState.Sleep),
                new Job(10, 19, 2, 11, JobFinishState.End),
                new Job(6, 19, 3, 6, JobFinishState.End),
                new Job(5, 20, 2, 10, JobFinishState.Sleep),
                new Job(4, 21, 3, 12, JobFinishState.Sleep),
                new Job(12, 22, 6, 13, JobFinishState.End),
                new Job(8, 22, 3, 9, JobFinishState.End),
                new Job(9, 28, 5, 11, JobFinishState.End),
                new Job(5, 33, 2, 3, JobFinishState.Sleep),
                new Job(4, 34, 3, 10, JobFinishState.End),
                new Job(5, 38, 2, 10, JobFinishState.End)
        };
        EmulateMemoryManagement virtualOperatingSystem = new EmulateMemoryManagement(jobs);
        System.out.println("Jobs Loaded: ");
        while (!virtualOperatingSystem.execute()) {
            Thread.sleep(100);
        }
        System.out.println("Every job is sleeping or ending!");

//        virtualOperatingSystem.frames[1] = 1;
//        virtualOperatingSystem.frames[14] = 1;
//        virtualOperatingSystem.frames[1] = 1;
////        Arrays.fill(virtualOperatingSystem.frames, 1);
//        virtualOperatingSystem.printFrameSnapshot();
//        List<int[]> res = virtualOperatingSystem.findAllUnoccupied();
//        System.out.println(res.size());
//        for (int[] r: res) {
//            System.out.println(r[0] + " " + r[1]);
//        }
//
//        int[] bestFit = virtualOperatingSystem.findBestFit(5);
//        int[] firstFit = virtualOperatingSystem.findFirstFit(5);
//        System.out.println(bestFit[0] + " " + bestFit[1]);
//        System.out.println(firstFit[0] + " " + firstFit[1]);
//    }
    }
}
