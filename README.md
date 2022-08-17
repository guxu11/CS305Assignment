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

**To get the code:**

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

## 2.  Execution

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
	(Job 04) - running: 0 steps remaining
	(Job 04) - finishing: transitioned to Sleep
	(Job 05) - running: 4 steps remaining
	(Job 06) - running: 2 steps remaining
	(Job 07) - running: 3 steps remaining
	(Job 02) - sleeping
	(Job 04) - sleeping
Frame Occupied: ~~###~~~~##########~
Time 9 finished
----------------------------------


----------------------------------
Time 10
Frame Occupied: ~~###~~~~##########~
	(Job 05) - running: 3 steps remaining
	(Job 06) - running: 1 steps remaining
	(Job 07) - running: 2 steps remaining
	(Job 02) - sleeping
	(Job 04) - sleeping
Frame Occupied: ~~###~~~~##########~
Time 10 finished
----------------------------------


----------------------------------
Time 11
Frame Occupied: ~~###~~~~##########~
	(Job 05) - running: 2 steps remaining
	(Job 06) - running: 0 steps remaining
	(Job 06) - finishing: transitioned to Sleep
	(Job 07) - running: 1 steps remaining
	(Job 02) - sleeping
	(Job 04) - sleeping
	(Job 06) - sleeping
Frame Occupied: ~~###~~~~##########~
Time 11 finished
----------------------------------


----------------------------------
Time 12
Frame Occupied: ~~###~~~~##########~
	(Job 05) - running: 1 steps remaining
	(Job 07) - running: 0 steps remaining
	(Job 07) - finishing: transitioned to Sleep
	(Job 02) - sleeping
	(Job 04) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ~~###~~~~##########~
Time 12 finished
----------------------------------


----------------------------------
Time 13
Frame Occupied: ~~###~~~~##########~
	(Job 05) - running: 0 steps remaining
	(Job 05) - finishing: transitioned to Sleep
	(Job 02) - sleeping
	(Job 04) - sleeping
	(Job 05) - sleeping
	(Job 06) - sleeping
	(Job 07) - sleeping
Frame Occupied: ~~###~~~~##########~
Time 13 finished
----------------------------------


Every job is sleeping or ending!

```

## 3. How does OS manage memory with *best-fi*t?

Best-fit method allows OS to allocate smallest space that can accommodate the job. A job that is running is called a process. When a process is finished, OS will release the space occupied by the process. However, when a process is sleeping, it will still reside in the memory space.

## 4. 

