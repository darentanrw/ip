package bloop.ui;

import bloop.task.Task;
import bloop.task.TaskList;

/**
 * Handles all interactions with the user (reading input, printing output).
 */
public class Ui {
    /**
     * Prints the welcome message when the application starts.
     */
    public void printWelcomeMessage() {
        System.out.println("\t--------------------------------\n\tHello I'm Bloop!\n\tWhat can I do for you?\n\t--------------------------------");
    }

    /**
     * Prints the goodbye message when the user exits the application.
     */
    public void printGoodbyeMessage() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Prints a horizontal separator line.
     */
    public void printLine() {
        System.out.println("\t--------------------------------");
    }

    /**
     * Prints all tasks in the given task list, numbered sequentially.
     * If the list is empty, prints a message indicating no tasks have been added.
     *
     * @param taskList The task list to display.
     */
    public void printTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            System.out.println("\tNo tasks added yet");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + taskList.getTask(i).toString());
            }
        }
    }

    /**
     * Prints a message confirming that a task has been updated with the given action.
     *
     * @param task   The task that was updated.
     * @param action A description of the action performed (e.g. "marked as done").
     */
    public void taskUpdateMessage(Task task, String action) {
        System.out.println("\t" + action);
        System.out.println("\t\t" + task.toString());
    }

    /**
     * Prints a message confirming that a new task has been added, along with the updated list size.
     *
     * @param task The newly added task.
     * @param size The total number of tasks after addition.
     */
    public void newTaskMessage(Task task, int size) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + task.toString());
        printTaskListSize(size);
    }

    /**
     * Prints an error message for unrecognised commands.
     */
    public void printInvalidCommandMessage() {
        System.out.println("\tBloop bloop.... Seems like you have entered an invalid command!");
    }

    /**
     * Prints the current number of tasks in the list.
     *
     * @param size The number of tasks in the list.
     */
    public void printTaskListSize(int size) {
        System.out.println("\tYou now have " + size + " tasks in the list.");
    }

    /**
     * Prints a custom error message.
     *
     * @param errorMessage The error message to display.
     */
    public void printCustomErrorMessage(String errorMessage) {
        System.out.println("\t" + errorMessage);
    }

    /**
     * Prints an error message indicating that tasks could not be loaded from the save file.
     */
    public void showLoadingError() {
        System.out.println("\tError loading tasks from file.");
    }
}
