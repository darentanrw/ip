import java.util.ArrayList;

/**
 * EventTask class for creating an Event task.
*/
public class EventTask extends Task {
    private String eventStart;
    private String eventEnd;

    /**
     * Constructor for EventTask.
     * @param taskName the name of the task
     * @param eventStart the start time of the event
     * @param eventEnd the end time of the event
     */
    public EventTask(String taskName, String eventStart, String eventEnd) {
        super(taskName, "E");
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Returns the storage representation of the EventTask.
     * @return the ArrayList of strings representing the EventTask
     */
    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        storageList.add(eventStart);
        storageList.add(eventEnd);
        return storageList;
    }

    public String toString() {
        return super.toString() + " (from: " + eventStart + " to: " + eventEnd + ")";
    }
}
