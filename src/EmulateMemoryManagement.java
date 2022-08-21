
import java.util.*;

public class EmulateMemoryManagement {
    public final int TOTAL_MEMORY = 20 * 1024;
    public final int PAGE_SIZE = 1024;
    private int[] frames;
    private Job[] jobs;
    private int steps;
    private Deque<Job> jobDeque;

    public EmulateMemoryManagement(Job[] jobs) {
        this.jobs = jobs;
        this.frames = new int[TOTAL_MEMORY / PAGE_SIZE];
        jobDeque = new LinkedList<>();
    }

    public boolean execute() {
        System.out.println("----------------------------------");
        System.out.println("Time " + ++steps);
        printFrameSnapshot();

        Map<Job, Integer> jobs = new LinkedHashMap<>();
        Map<Job, Integer> jobStarting = new LinkedHashMap<>();
        for (int i = 0; i < this.jobs.length; i++) {
            Job job = this.jobs[i];
            if (job.currState != JobState.TERMINATED) {
                jobs.put(job, i);
                if (job.startTime == steps || (job.startTime < steps && job.heldPages.isEmpty())) {
                    jobStarting.put(job, i);
                }
            }
        }
        for (Job job: jobStarting.keySet()) {

            System.out.println("\t" + job + " - ready: " + job.requiredPages + " pages required");
            List<Integer> allocate = allocate(job.requiredPages);
            if (allocate.isEmpty()) {
                System.out.println("\t" + job + " - skipped: could not find " + job.requiredPages + " empty pages");
                List<Integer> deallocateJobs = deallocate(job.requiredPages);
                allocate = allocate(job.requiredPages);
                System.out.println("\t" + "kill job " + deallocateJobs + " to free pages");
            }
            for (int page: allocate) {
                frames[page] = 1;
            }
            job.heldPages = allocate;
            jobDeque.addLast(job);
            System.out.println("\t" + job + " - allocated: pages " + allocate);

        }
        Map<Job, Integer> jobsRunning = new LinkedHashMap<>();
        for (Job job: jobs.keySet()) {
            if (job.startTime <= steps && job.executionInterval > 0 && !job.heldPages.isEmpty()) {
                jobsRunning.put(job, jobs.get(job));
            }
        }
        for (Job job: jobsRunning.keySet()) {
            if (!job.heldPages.isEmpty()) {
                System.out.println("\t" + job + " - running: " + --job.executionInterval + " steps remaining");
                job.currState = JobState.RUNNING;
            } else {
                System.out.println("\t" + job + " - not running");
            }
            if (job.executionInterval == 0) {
                System.out.println("\t" + job + " - finishing: transitioned to " + job.finishState);
                switch (job.finishState) {
                    case TERMINATED:
                        for (int heldpage: job.heldPages) {
                            frames[heldpage] = 0;
                        }
                        System.out.println("\t" + job + " - released: " + job.heldPages);
                        this.jobs[jobs.get(job)].currState = JobState.TERMINATED;
                        job.heldPages.clear();
                        job.currState = JobState.TERMINATED;
                        jobDeque.remove(job);
                        break;
                    case BLOCKED:
                        job.currState = JobState.BLOCKED;
                        this.jobs[jobs.get(job)].currState = JobState.BLOCKED;
                        break;
                }
            }
        }
        Map<Job, Integer> jobsSleeping = new LinkedHashMap<>();
        for (Job job: jobs.keySet()) {
//            if (job.startTime <= steps && job.executionInterval == 0) {
            if (job.currState == JobState.BLOCKED) {
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
//            if (job.startTime > steps || job.executionInterval > 0) {
            if (job.currState != JobState.BLOCKED && job.currState != JobState.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> deallocate(int needSize) {
        List<Integer> killedJobs = new ArrayList<>();
        int freeSize = findAllUnoccupied().size();
        int needToFree = needSize - freeSize;
        while (needToFree > 0) {
            Job oldJob = jobDeque.poll();
            oldJob.currState = JobState.TERMINATED;
            oldJob.executionInterval = 0;
            for (int page: oldJob.heldPages) {
                frames[page] = 0;
            }
            int oldJobSize = oldJob.heldPages.size();
            killedJobs.add(oldJob.ID);
            needToFree -= oldJobSize;
        }
        return killedJobs;

    }


    public List<Integer> allocate(int sizeAtLeast) {
        List<Integer> allUnoccupied = findAllUnoccupied();
        List<Integer> toAllocate = new ArrayList<>();
        if (sizeAtLeast > allUnoccupied.size()) return toAllocate;
        for (int unoccupied: allUnoccupied) {
            if (sizeAtLeast-- == 0) break;
            toAllocate.add(unoccupied);
        }
        return toAllocate;
    }


    public List<Integer> findAllUnoccupied() {
        List<Integer> allUnoccupied = new ArrayList<>();
        for (int i = 0; i < frames.length; i++) {
            if (frames[i] == 0){
                allUnoccupied.add(i);
            }
        }
        return allUnoccupied;
    }


    public void printFrameSnapshot() {
        System.out.print("Frame Occupied: ");
        for (int i = 0; i < frames.length; i++) {
            System.out.print(frames[i] > 0 ? "#" : "~");
        }
        System.out.println();
    }

//    public void

    public static void main(String[] args) throws InterruptedException {
        Job[] jobs = new Job[]{
                new Job(1, 1, 2, 7, JobState.BLOCKED),
                new Job(2, 2, 3, 8, JobState.BLOCKED),
                new Job(3, 3, 4, 6, JobState.BLOCKED),
                new Job(4, 4, 3, 6, JobState.BLOCKED),
                new Job(5, 5, 2, 9, JobState.BLOCKED),
                new Job(6, 6, 3, 6, JobState.BLOCKED),
                new Job(7, 7, 2, 6, JobState.BLOCKED),
                new Job(8, 12, 8, 5, JobState.TERMINATED),


        };
        EmulateMemoryManagement virtualOperatingSystem = new EmulateMemoryManagement(jobs);
        System.out.println("Jobs Loaded: ");
        while (!virtualOperatingSystem.execute()) {
            Thread.sleep(100);
        }
        System.out.println("Every job is sleeping or ending!");

    }
}
