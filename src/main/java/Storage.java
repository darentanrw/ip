import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Scanner;

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

    /**
     * Load the tasks from a file.
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();

        if (!Files.exists(Paths.get("data/tasks.txt"))) {
            return taskList;
        } else {
            try {
                File existingTasks = new File("data/tasks.txt");
                Scanner sc = new Scanner(existingTasks);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(" \\| ");

                    String taskType = parts[0].strip();
                    String taskStatus = parts[1].strip();
                    String taskName = parts[2].strip(); 

                    Task newTask;
                    if (taskType.equals("D")) {
                        newTask = new DeadlineTask(taskName, parts[3].strip());
                    } else if (taskType.equals("E")) {
                        newTask = new EventTask(taskName, parts[3].strip(), parts[4].strip());
                    } else if (taskType.equals("T")) {
                        newTask = new ToDoTask(taskName);
                    }
                    else {
                        System.out.println("Error loading tasks: Invalid task type");
                        newTask = new Task(taskName);
                    }

                    newTask.setStatus(taskStatus.equals("X") ? true : false);
                    taskList.add(newTask);    
                }
                sc.close();
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        }
        return taskList;
    }
}
