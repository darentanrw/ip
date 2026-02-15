package bloop.storage;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Scanner;

import bloop.task.Task;
import bloop.task.TaskList;
import bloop.task.ToDoTask;
import bloop.task.DeadlineTask;
import bloop.task.EventTask;

/**
 * Handles loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves all tasks in the task list to the file.
     *
     * @param taskList the task list to save
     */
    public void save(TaskList taskList) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                ArrayList<String> storageList = taskList.getTask(i).toStorage();
                fileWriter.write(String.join(" | ", storageList) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return an ArrayList of tasks loaded from the file
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();

        if (!Files.exists(Paths.get(filePath))) {
            return taskList;
        }

        try {
            File existingTasks = new File(filePath);
            Scanner sc = new Scanner(existingTasks);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.strip().isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s*\\|\\s*", -1);
                if (parts.length < 3) {
                    continue;
                }

                String taskType = parts[0].strip();
                String taskStatus = parts[1].strip();
                String taskName = parts[2].strip();

                Task newTask;
                if (taskType.equals("D")) {
                    if (parts.length < 4) {
                        continue;
                    }
                    newTask = new DeadlineTask(taskName, parts[3].strip());
                } else if (taskType.equals("E")) {
                    if (parts.length < 5) {
                        continue;
                    }
                    newTask = new EventTask(taskName, parts[3].strip(), parts[4].strip());
                } else if (taskType.equals("T")) {
                    newTask = new ToDoTask(taskName);
                } else {
                    System.out.println("Error loading tasks: Invalid task type");
                    newTask = new Task(taskName);
                }

                newTask.setStatus(taskStatus.equals("X"));
                taskList.add(newTask);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        return taskList;
    }
}
