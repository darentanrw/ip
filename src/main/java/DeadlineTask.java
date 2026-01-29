import java.util.ArrayList;

/**
 * DeadlineTask class for creating a Deadline task.
*/
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Constructor for DeadlineTask.
     * @param taskName the name of the task
     * @param deadline the deadline of the task
     */
    public DeadlineTask(String taskName, String deadline) {
        super(taskName, "D");
        this.deadline = deadline;
    }

    /**
     * Returns the storage representation of the DeadlineTask.
     * @return the ArrayList of strings representing the DeadlineTask
     */
    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        storageList.add(deadline);
        return storageList;
    }

    public String toString() {
        return super.toString() + " (by: " + deadline + ")";
    } 
}
