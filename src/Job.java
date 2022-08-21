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
