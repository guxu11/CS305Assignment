
public class Job {
    public int ID;
    public int startPage;
    public int endPage;

    public int startTime;
    public int requiredPages;
    public int executionInterval;
    public JobFinishState finishState;

    public String toString() {
        String jobId = ID >= 10 ? String.valueOf(ID) : '0' + String.valueOf(ID);
        return "(Job " + jobId + ")";
    }

}
