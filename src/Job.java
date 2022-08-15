
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
