



public class VirtualOperatingSystem {
    public final int TOTAL_MEMORY = 20 * 1024;
    public final int PAGE_SIZE = 1024;
    private int[] frames;
    private Job[] jobs;
    private int steps;
    public VirtualOperatingSystem (Job[] jobs) {
        this.jobs = jobs;
        this.frames = new int[TOTAL_MEMORY / PAGE_SIZE];
    }

    public void execute() {
        System.out.println("```");
        System.out.println("TimeStamp " + ++steps);
        printFrameSnapshot();

        Job[] jobs = new Job[this.jobs.length];
        Job[] jobStarting = new Job[this.jobs.length];
        for (Job job: this.jobs) {

        }

    }
    public boolean isCompleted() {
        for (int i = 0; i < jobs.length; i++) {

        }
        return false;
    }




    public void printFrameSnapshot() {
        System.out.print("Frame Snapshot: ");
        System.out.print("[");
        for (int i = 0; i < frames.length; i++) {
            System.out.print(frames[i] > 0 ? "X" : "_");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        VirtualOperatingSystem virtualOperatingSystem = new VirtualOperatingSystem(null);
        virtualOperatingSystem.frames[0] = 1;
        virtualOperatingSystem.printFrameSnapshot();
    }


}
