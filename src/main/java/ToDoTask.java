import java.util.ArrayList;
public class ToDoTask extends Task {
    public ToDoTask(String taskName) {
        super(taskName, "T");
    }

    public String toString() {
        return super.toString();
    }

    @Override
    public ArrayList<String> toStorage() {
        ArrayList<String> storageList = super.toStorage();
        return storageList;
    }
}