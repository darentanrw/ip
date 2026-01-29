import java.util.ArrayList;

/**
 * General Task class for creating a Task.
*/
public class Task {
    private String taskName;
    private boolean isDone;
    private String type = null;

    /**
     * Constructor for Task.
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.type = null;
    }

    /**
     * Constructor for Task.
     * @param taskName the name of the task
     * @param type the type of the task
     */
    public Task(String taskName, String type) {
        this.taskName = taskName;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Sets the status of the task.
     * @param isDone the status of the task
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the storage representation of the Task.
     * @return the ArrayList of strings representing the Task
     */
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = new ArrayList<String>();
        storageList.add(type);
        storageList.add(isDone ? "X" : "");
        storageList.add(taskName);
        return storageList;
    }
    
    public String toString() {
        if (type != null) {
            return "[" + type + "] " + "[" + (isDone ? "X" : " ") + "] " + taskName;
        }
        return "[" + (isDone ? "X" : " ") + "] " + taskName;
    }
}