package bloop.task;

import java.util.ArrayList;

/**
 * ToDoTask class for creating a ToDo task.
 */
public class ToDoTask extends Task {
    /**
     * Constructor for ToDoTask.
     * 
     * @param taskName the name of the task
     */
    public ToDoTask(String taskName) {
        super(taskName, "T");
    }

    /**
     * Returns the storage representation of the ToDoTask.
     * 
     * @return the storage representation of the ToDoTask
     */
    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        return storageList;
    }

    public String toString() {
        return super.toString();
    }
}
