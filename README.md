## 1. Overview

**Program Structure:**

```
.

├── README.md

└── src

  ├── EmulateMemoryManagement.java

  ├── Job.java

  └── JobState.java
```

**To get the code (The code is also listed in the bottom of the text):**

```bash
git clone -b assignment2 git@github.com:guxu11/CS305Assignment.git
```

The emulating codes are in the ***src*** folder. 

`Job.java`  is the Struct of a job. 

 `JobState.java` is the enum of finishing states.

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

**1. Memory Page Allocation:**

Assuming it's a **non-contiguous memory**, jobs can only allocated with separated space. If there remains pages that can contains the job, the OS will allocate the space for it. If not, the OS will free some pages to meet the page requirement according to **FIFO** page replacement algorithm.

My emulation program obey the memory rule above, and the executing result is shown below.



**2. Execution**

The simulation last 16 steps. The record for the simulation is recorded below, where '#' represents pages that are occupied while "~" represents free pages.

```
Jobs Loaded: 
----------------------------------
Time 1
Frame Occupied: ~~~~~~~~~~~~~~~~~~~~
	(Job 01) - ready: 2 pages required
	(Job 01) - allocated: pages [0, 1]
	(Job 01) - running: 6 steps remaining
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
Time 1 finished
----------------------------------


----------------------------------
Time 2
Frame Occupied: ##~~~~~~~~~~~~~~~~~~
	(Job 02) - ready: 3 pages required
	(Job 02) - allocated: pages [2, 3, 4]
	(Job 01) - running: 5 steps remaining
	(Job 02) - running: 7 steps remaining
Frame Occupied: #####~~~~~~~~~~~~~~~
Time 2 finished
----------------------------------


----------------------------------
Time 3
Frame Occupied: #####~~~~~~~~~~~~~~~
	(Job 03) - ready: 4 pages required
	(Job 03) - allocated: pages [5, 6, 7, 8]
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
	(Job 04) - allocated: pages [9, 10, 11]
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
	(Job 05) - allocated: pages [12, 13]
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
	(Job 06) - allocated: pages [14, 15, 16]
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
	(Job 07) - allocated: pages [17, 18]
	(Job 01) - running: 0 steps remaining
	(Job 01) - finishing: transitioned to BLOCKED
	(Job 02) - running: 2 steps remaining
	(Job 03) - running: 1 steps remaining
	(Job 04) - running: 2 steps remaining
	(Job 05) - running: 6 steps remaining
	(Job 06) - running: 4 steps remaining
	(Job 07) - running: 5 steps remaining
	(Job 01) - sleeping
Frame Occupied: ###################~
Time 7 finished
----------------------------------


----------------------------------
Time 8
Frame Occupied: ###################~
	(Job 02) - running: 1 steps remaining
	(Job 03) - running: 0 steps remaining
	(Job 03) - finishing: transitioned to BLOCKED
	(Job 04) - running: 1 steps remaining
	(Job 05) - running: 5 steps remaining
	(Job 06) - running: 3 steps remaining
	(Job 07) - running: 4 steps remaining
	(Job 01) - sleeping
	(Job 03) - sleeping
Frame Occupied: ###################~
Time 8 finished
----------------------------------


----------------------------------
Time 9
Frame Occupied: ###################~
	(Job 02) - running: 0 steps remaining
	(Job 02) - finishing: transitioned to BLOCKED
	(Job 04) - running: 0 steps remaining
	(Job 04) - finishing: transitioned to BLOCKED
	(Job 05) - running: 4 steps remaining
	(Job 06) - running: 2 steps remaining
	(Job 07) - running: 3 steps remaining
	(Job 01) - sleeping
	(Job 02) - sleeping
	(Job 03) - sleeping
	(Job 04) - sleeping
Frame Occupied: ###################~
Time 9 finished
----------------------------------


----------------------------------
Time 10
Frame Occupied: ###################~
	(Job 05) - running: 3 steps remaining
	(Job 06) - running: 1 steps remaining
	(Job 07) - running: 2 steps remaining
	(Job 01) - sleeping
	(Job 02) - sleeping
	(Job 03) - sleeping
	(Job 04) - sleeping
Frame Occupied: ###################~
Time 10 finished
----------------------------------


----------------------------------
Time 11
Frame Occupied: ###################~
	(Job 05) - running: 2 steps remaining
	(Job 06) - running: 0 steps remaining
	(Job 06) - finishing: transitioned to BLOCKED
	(Job 07) - running: 1 steps remaining
	(Job 01) - sleeping
	(Job 02) - sleeping
	(Job 03) - sleeping
	(Job 04) - sleeping
	(Job 06) - sleeping
Frame Occupied: ###################~
Time 11 finished
----------------------------------


----------------------------------
Time 12
Frame Occupied: ###################~
	(Job 08) - ready: 8 pages required
	(Job 08) - skipped: could not find 8 empty pages
	kill job [1, 2, 3] to free pages
	(Job 08) - allocated: pages [0, 1, 2, 3, 4, 5, 6, 7]
	(Job 05) - running: 1 steps remaining
	(Job 07) - running: 0 steps remaining
	(Job 07) - finishing: transitioned to BLOCKED
	(Job 08) - running: 4 steps remaining
	(Job 04) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ########~##########~
Time 12 finished
----------------------------------


----------------------------------
Time 13
Frame Occupied: ########~##########~
	(Job 05) - running: 0 steps remaining
	(Job 05) - finishing: transitioned to BLOCKED
	(Job 08) - running: 3 steps remaining
	(Job 04) - sleeping
	(Job 05) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ########~##########~
Time 13 finished
----------------------------------


----------------------------------
Time 14
Frame Occupied: ########~##########~
	(Job 08) - running: 2 steps remaining
	(Job 04) - sleeping
	(Job 05) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ########~##########~
Time 14 finished
----------------------------------


----------------------------------
Time 15
Frame Occupied: ########~##########~
	(Job 08) - running: 1 steps remaining
	(Job 04) - sleeping
	(Job 05) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ########~##########~
Time 15 finished
----------------------------------


----------------------------------
Time 16
Frame Occupied: ########~##########~
	(Job 08) - running: 0 steps remaining
	(Job 08) - finishing: transitioned to TERMINATED
	(Job 08) - released: [0, 1, 2, 3, 4, 5, 6, 7]
	(Job 04) - sleeping
	(Job 05) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ~~~~~~~~~##########~
Time 16 finished
----------------------------------
```

3. **Analysis**

The program simplified file loading job as `new Job(8, 12, 8, 5, JobState.TERMINATED)` , which is in the same format of the previous 7 job.

The first 7 jobs are all finished with the Blocked (Sleeping) state, which means this job free the CPU usage but still stays in memory. After all the 7 jobs ending in the blocked state in the 11th time step, the frame situation is **Frame Occupied: ###################~** , which means there is only one page left. 

Therefore the OS have to deallocate at least 7 pages to contain this file, by  `kill job [1, 2, 3] to free pages ` .

Then we can answer the questions listed in the assignment 2 page. The file occupied 8 single disk blocs, and it's allocated 8 pages,  from page 0 to page 7.

## 3. My Codes

> Codes can also be cloned from github.

1. Job.java

```java
import java.util.ArrayList;
import java.util.List;

public class Job {
    public int ID;
    public List<Integer> heldPages;

    public int startTime;
    public int requiredPages;
    public int executionInterval;
    public JobState finishState;
    public JobState currState;
    public Job(int id, int startTime, int requiredPages, int executionInterval, JobState jobFinishState) {
        this.ID = id;
        this.startTime = startTime;
        this.requiredPages = requiredPages;
        this.executionInterval = executionInterval;
        this.finishState = jobFinishState;
        heldPages = new ArrayList<>();
        currState = JobState.CREATED;
    }
    public String toString() {
        String jobId = ID >= 10 ? String.valueOf(ID) : '0' + String.valueOf(ID);
        return "(Job " + jobId + ")";
    }

}

```

2. JobState.java

```java
public enum JobState {
    CREATED,
    READY,
    RUNNING,
    BLOCKED,
    TERMINATED
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

```

