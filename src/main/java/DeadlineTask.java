import java.util.ArrayList;
public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName, "D");
        this.deadline = deadline;
    }

    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    } 

    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        storageList.add(deadline);
        return storageList;
    }
}
