import java.util.ArrayList;
public class EventTask extends Task {
    private String eventStart;
    private String eventEnd;

    public EventTask(String taskName, String eventStart, String eventEnd) {
        super(taskName, "E");
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public String toString() {
        return super.toString() + " (from: " + eventStart + " to: " + eventEnd + ")";
    }

    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        storageList.add(eventStart);
        storageList.add(eventEnd);
        return storageList;
    }
}
