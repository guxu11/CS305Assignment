## 1. Overview

**Program Structure:**

```
.

├── README.md

└── src

  ├── EmulateMemoryManagement.java

  ├── Job.java

  └── JobFinishState.java
```

**To get the code (The code is also listed in the bottom of the text):**

```bash
git clone git@github.com:guxu11/CS305Assignment.git
```

The emulating codes are in the ***src*** folder. 

`Job.java`  is the Struct of a job. 

 `JobFinishState.java` is the enum of finishing states.

`EmulateMemoryManagement.java` is the code for emulating memory management.

To run the program, just compile them firstly.

```bash
cd src
javac *.java
```

To execute the emulation

```bash
java EmulateMemoryManagement
```

## 2.  Implementation

<<<<<<< Updated upstream
**1. Memory Page Allocation:**

Assuming it's a contiguous memory, jobs can only allocated with contiguous space. If there remains pages that can contains the job, the OS will allocate the space for it. If not, the job will wait until other jobs terminate and free its memory.

My emulation program obey the memory rule above, and the executing result is shown below.



**2. First Example** 
=======
Memory Page Allocation:

The memory size is 20kbyte in total, and is divided into 20 equal pages, where the page size is 1kbyte. 



Assuming it's a 
>>>>>>> Stashed changes

The simulation last 13 step. ***Best-fit*** is used as the default way to allocate memories for jobs. The record for the simulation is recorded below, where '#' represents pages that are occupied while "~" represents free pages.

```
Jobs Loaded: 
----------------------------------
Time 1
Frame Occupied: ~~~~~~~~~~~~~~~~~~~~
	(Job 01) - ready: 2 pages required
	(Job 01) - allocated: pages 0-2
	(Job 01) - running: 6 steps remaining
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
Time 1 finished
----------------------------------


----------------------------------
Time 2
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
	(Job 02) - ready: 3 pages required
	(Job 02) - allocated: pages 2-5
	(Job 01) - running: 5 steps remaining
	(Job 02) - running: 7 steps remaining
Frame Occupied: #####~~~~~~~~~~~~~~~
Time 2 finished
----------------------------------


----------------------------------
Time 3
Frame Occupied: #####~~~~~~~~~~~~~~~
	(Job 03) - ready: 4 pages required
	(Job 03) - allocated: pages 5-9
	(Job 01) - running: 4 steps remaining
	(Job 02) - running: 6 steps remaining
	(Job 03) - running: 5 steps remaining
Frame Occupied: #########~~~~~~~~~~~
Time 3 finished
----------------------------------


----------------------------------
Time 4
Frame Occupied: #########~~~~~~~~~~~
	(Job 04) - ready: 3 pages required
	(Job 04) - allocated: pages 9-12
	(Job 01) - running: 3 steps remaining
	(Job 02) - running: 5 steps remaining
	(Job 03) - running: 4 steps remaining
	(Job 04) - running: 5 steps remaining
Frame Occupied: ############~~~~~~~~
Time 4 finished
----------------------------------


----------------------------------
Time 5
Frame Occupied: ############~~~~~~~~
	(Job 05) - ready: 2 pages required
	(Job 05) - allocated: pages 12-14
	(Job 01) - running: 2 steps remaining
	(Job 02) - running: 4 steps remaining
	(Job 03) - running: 3 steps remaining
	(Job 04) - running: 4 steps remaining
	(Job 05) - running: 8 steps remaining
Frame Occupied: ##############~~~~~~
Time 5 finished
----------------------------------


----------------------------------
Time 6
Frame Occupied: ##############~~~~~~
	(Job 06) - ready: 3 pages required
	(Job 06) - allocated: pages 14-17
	(Job 01) - running: 1 steps remaining
	(Job 02) - running: 3 steps remaining
	(Job 03) - running: 2 steps remaining
	(Job 04) - running: 3 steps remaining
	(Job 05) - running: 7 steps remaining
	(Job 06) - running: 5 steps remaining
Frame Occupied: #################~~~
Time 6 finished
----------------------------------


----------------------------------
Time 7
Frame Occupied: #################~~~
	(Job 07) - ready: 2 pages required
	(Job 07) - allocated: pages 17-19
	(Job 01) - running: 0 steps remaining
	(Job 01) - finishing: transitioned to End
	(Job 01) - released: 0-2 pages
	(Job 02) - running: 2 steps remaining
	(Job 03) - running: 1 steps remaining
	(Job 04) - running: 2 steps remaining
	(Job 05) - running: 6 steps remaining
	(Job 06) - running: 4 steps remaining
	(Job 07) - running: 5 steps remaining
	(Job 01) - sleeping
Frame Occupied: ~~#################~
Time 7 finished
----------------------------------


----------------------------------
Time 8
Frame Occupied: ~~#################~
	(Job 02) - running: 1 steps remaining
	(Job 03) - running: 0 steps remaining
	(Job 03) - finishing: transitioned to End
	(Job 03) - released: 5-9 pages
	(Job 04) - running: 1 steps remaining
	(Job 05) - running: 5 steps remaining
	(Job 06) - running: 3 steps remaining
	(Job 07) - running: 4 steps remaining
	(Job 03) - sleeping
Frame Occupied: ~~###~~~~##########~
Time 8 finished
----------------------------------


----------------------------------
Time 9
Frame Occupied: ~~###~~~~##########~
	(Job 02) - running: 0 steps remaining
	(Job 02) - finishing: transitioned to Sleep
	(Job 02) - released: 2-5 pages
	(Job 04) - running: 0 steps remaining
	(Job 04) - finishing: transitioned to Sleep
	(Job 04) - released: 9-12 pages
	(Job 05) - running: 4 steps remaining
	(Job 06) - running: 2 steps remaining
	(Job 07) - running: 3 steps remaining
	(Job 02) - sleeping
	(Job 04) - sleeping
Frame Occupied: ~~~~~~~~~~~~#######~
Time 9 finished
----------------------------------


----------------------------------
Time 10
Frame Occupied: ~~~~~~~~~~~~#######~
	(Job 05) - running: 3 steps remaining
	(Job 06) - running: 1 steps remaining
	(Job 07) - running: 2 steps remaining
Frame Occupied: ~~~~~~~~~~~~#######~
Time 10 finished
----------------------------------


----------------------------------
Time 11
Frame Occupied: ~~~~~~~~~~~~#######~
	(Job 05) - running: 2 steps remaining
	(Job 06) - running: 0 steps remaining
	(Job 06) - finishing: transitioned to Sleep
	(Job 06) - released: 14-17 pages
	(Job 07) - running: 1 steps remaining
	(Job 06) - sleeping
Frame Occupied: ~~~~~~~~~~~~##~~~##~
Time 11 finished
----------------------------------


----------------------------------
Time 12
Frame Occupied: ~~~~~~~~~~~~##~~~##~
	(Job 05) - running: 1 steps remaining
	(Job 07) - running: 0 steps remaining
	(Job 07) - finishing: transitioned to Sleep
	(Job 07) - released: 17-19 pages
	(Job 07) - sleeping
Frame Occupied: ~~~~~~~~~~~~##~~~~~~
Time 12 finished
----------------------------------


----------------------------------
Time 13
Frame Occupied: ~~~~~~~~~~~~##~~~~~~
	(Job 05) - running: 0 steps remaining
	(Job 05) - finishing: transitioned to Sleep
	(Job 05) - released: 12-14 pages
	(Job 05) - sleeping
Frame Occupied: ~~~~~~~~~~~~~~~~~~~~
Time 13 finished
----------------------------------


Every job is sleeping or ending!
<<<<<<< Updated upstream
```

3. **More Jobs with *Best-Fit* and *First-Fit***

The ***Best-Fit*** algorithm will find the more matched space for a job, which is the smallest space that is larger than the required space.

The ***First-Fit*** algorithm will find the first unoccupied space that is larger that the required space.

Here is the executing results.

Using Best-Fit to allocate memory space, it takes 47 steps to finish. Step 40 -- Step 47 is listed below.

Using First-Fit to allocate memory space, it takes 47 steps to finish. Step 40 -- Step 47 is listed below.

In this experiment, the selected two algorithm draw in time consuming, but review their executing process, wo find that best-fit tend to generate more contiguous space while first leave a lot of piecemeal parts.  

Considering this experiment is a ideal assumption where each job's can fully use several pages without leaving any internal segments, the first-fit will probably leave much segment in the real scenes. Therefore I think the best-fit algorithm is better.

=======
>>>>>>> Stashed changes
```
Time 41 finished
----------------------------------


----------------------------------
Time 42
Frame Occupied: ##########~~~~~~~~~~
	(Job 09) - running: 2 steps remaining
	(Job 04) - running: 1 steps remaining
	(Job 05) - running: 5 steps remaining
Frame Occupied: ##########~~~~~~~~~~
Time 42 finished
----------------------------------


----------------------------------
Time 43
Frame Occupied: ##########~~~~~~~~~~
	(Job 09) - running: 1 steps remaining
	(Job 04) - running: 0 steps remaining
	(Job 04) - finishing: transitioned to End
	(Job 04) - released: 5-8 pages
	(Job 05) - running: 4 steps remaining
	(Job 04) - sleeping
Frame Occupied: #####~~~##~~~~~~~~~~
Time 43 finished
----------------------------------


----------------------------------
Time 44
Frame Occupied: #####~~~##~~~~~~~~~~
	(Job 09) - running: 0 steps remaining
	(Job 09) - finishing: transitioned to End
	(Job 09) - released: 0-5 pages
	(Job 05) - running: 3 steps remaining
	(Job 09) - sleeping
Frame Occupied: ~~~~~~~~##~~~~~~~~~~
Time 44 finished
----------------------------------


----------------------------------
Time 45
Frame Occupied: ~~~~~~~~##~~~~~~~~~~
	(Job 05) - running: 2 steps remaining
Frame Occupied: ~~~~~~~~##~~~~~~~~~~
Time 45 finished
----------------------------------


----------------------------------
Time 46
Frame Occupied: ~~~~~~~~##~~~~~~~~~~
	(Job 05) - running: 1 steps remaining
Frame Occupied: ~~~~~~~~##~~~~~~~~~~
Time 46 finished
----------------------------------


----------------------------------
Time 47
Frame Occupied: ~~~~~~~~##~~~~~~~~~~
	(Job 05) - running: 0 steps remaining
	(Job 05) - finishing: transitioned to End
	(Job 05) - released: 8-10 pages
	(Job 05) - sleeping
Frame Occupied: ~~~~~~~~~~~~~~~~~~~~
Time 47 finished
----------------------------------
```

```
----------------------------------
Time 41
Frame Occupied: ##~~~~~~~~~~~###~~~~
	(Job 04) - running: 2 steps remaining
	(Job 05) - running: 6 steps remaining
Frame Occupied: ##~~~~~~~~~~~###~~~~
Time 41 finished
----------------------------------


----------------------------------
Time 42
Frame Occupied: ##~~~~~~~~~~~###~~~~
	(Job 04) - running: 1 steps remaining
	(Job 05) - running: 5 steps remaining
Frame Occupied: ##~~~~~~~~~~~###~~~~
Time 42 finished
----------------------------------


----------------------------------
Time 43
Frame Occupied: ##~~~~~~~~~~~###~~~~
	(Job 04) - running: 0 steps remaining
	(Job 04) - finishing: transitioned to End
	(Job 04) - released: 13-16 pages
	(Job 05) - running: 4 steps remaining
	(Job 04) - sleeping
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
Time 43 finished
----------------------------------


----------------------------------
Time 44
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
	(Job 05) - running: 3 steps remaining
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
Time 44 finished
----------------------------------


----------------------------------
Time 45
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
	(Job 05) - running: 2 steps remaining
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
Time 45 finished
----------------------------------


----------------------------------
Time 46
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
	(Job 05) - running: 1 steps remaining
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
Time 46 finished
----------------------------------


----------------------------------
Time 47
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
	(Job 05) - running: 0 steps remaining
	(Job 05) - finishing: transitioned to End
	(Job 05) - released: 0-2 pages
	(Job 05) - sleeping
Frame Occupied: ~~~~~~~~~~~~~~~~~~~~
Time 47 finished
----------------------------------
```

## 3. My Codes

> Codes can also be cloned from github.

1. Job.java

```java

public class Job {
    public int ID;
    public int[] heldPages = null;

    public int startTime;
    public int requiredPages;
    public int executionInterval;
    public JobFinishState finishState;
    public Job(int id, int startTime, int requiredPages, int executionInterval, JobFinishState jobFinishState) {
        this.ID = id;
        this.startTime = startTime;
        this.requiredPages = requiredPages;
        this.executionInterval = executionInterval;
        this.finishState = jobFinishState;
    }
    public String toString() {
        String jobId = ID >= 10 ? String.valueOf(ID) : '0' + String.valueOf(ID);
        return "(Job " + jobId + ")";
    }

}


```

2. JobFinishState.java

```java
public enum JobFinishState {
    End,
    Sleep
}

```

3. EmulateMemoryManagement.java

```java

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

```

