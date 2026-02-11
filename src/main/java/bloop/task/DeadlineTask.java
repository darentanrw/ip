package bloop.task;

import java.util.ArrayList;
import java.time.LocalDateTime;

import bloop.parser.TimeParser;

/**
 * DeadlineTask class for creating a Deadline task.
*/
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for DeadlineTask.
     * @param taskName the name of the task
     * @param deadline the deadline of the task
     */
    public DeadlineTask(String taskName, String deadline) {
        super(taskName, "D");
        this.deadline = TimeParser.parseDateTime(deadline);
    }

    /**
     * Returns the storage representation of the DeadlineTask.
     * @return the ArrayList of strings representing the DeadlineTask
     */
    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        storageList.add(TimeParser.toStorage(deadline));
        return storageList;
    }

    public String toString() {
        return super.toString() + " (by: " + TimeParser.toString(deadline) + ")";
    } 
}
