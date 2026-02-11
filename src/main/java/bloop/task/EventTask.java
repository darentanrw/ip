package bloop.task;

import java.util.ArrayList;
import java.time.LocalDateTime;

import bloop.parser.TimeParser;

/**
 * EventTask class for creating an Event task.
*/
public class EventTask extends Task {
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

    /**
     * Constructor for EventTask.
     * @param taskName the name of the task
     * @param eventStart the start time of the event
     * @param eventEnd the end time of the event
     */
    public EventTask(String taskName, String eventStart, String eventEnd) {
        super(taskName, "E");
        this.eventStart = TimeParser.parseDateTime(eventStart);
        this.eventEnd = TimeParser.parseDateTime(eventEnd);
    }

    /**
     * Returns the storage representation of the EventTask.
     * @return the ArrayList of strings representing the EventTask
     */
    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        storageList.add(TimeParser.toStorage(eventStart));
        storageList.add(TimeParser.toStorage(eventEnd));
        return storageList;
    }

    public String toString() {
        return super.toString() + " (from: " + TimeParser.toString(eventStart) + " to: " + TimeParser.toString(eventEnd) + ")";
    }
}
