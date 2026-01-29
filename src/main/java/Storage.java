import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Storage class for saving and loading tasks to a file.
*/

public class Storage {
    /**
     * Save the tasks to a file. Creates the data directory if it doesn't exist.
     * @param taskList the list of tasks to save
     */

    public void save(ArrayList<Task> taskList) {
        if (!Files.exists(Paths.get("data"))) {
            try {
                Files.createDirectory(Paths.get("data"));
            } catch (IOException e) {
                System.out.println("Error creating directory: " + e.getMessage());
            }
        }

        try {
            FileWriter fileWriter = new FileWriter("data/tasks.txt");
            for (Task task : taskList) {
                ArrayList<String> storageList = task.toStorage();
                fileWriter.write(String.join(" | ", storageList) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
