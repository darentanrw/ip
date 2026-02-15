package bloop.ui;

import java.util.ArrayList;

import bloop.task.Task;
import bloop.task.TaskList;

/**
 * A UI adapter that captures output for GUI rendering.
 */
public class GuiUi extends Ui {
    private final StringBuilder output = new StringBuilder();

    /**
     * Clears any previously captured output.
     */
    public void resetBuffer() {
        output.setLength(0);
    }

    /**
     * Returns the currently captured output and resets the buffer.
     *
     * @return captured output for the next GUI dialog bubble
     */
    public String consumeOutput() {
        String response = output.toString().stripTrailing();
        resetBuffer();
        return response;
    }

    private void appendLine(String text) {
        output.append(text).append(System.lineSeparator());
    }

    @Override
    public void printWelcomeMessage() {
        appendLine("--------------------------------");
        appendLine("Hello! I'm Bloop!");
        appendLine("What can I do for you?");
        appendLine("--------------------------------");
    }

    @Override
    public void printGoodbyeMessage() {
        printLine();
        appendLine("Bye. Hope to see you again soon!");
        printLine();
    }

    @Override
    public void printLine() {
        appendLine("--------------------------------");
    }

    @Override
    public void printTaskList(TaskList taskList) {
        if (taskList.size() == 0) {
            appendLine("No tasks added yet");
            return;
        }

        appendLine("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            appendLine((i + 1) + ". " + taskList.getTask(i));
        }
    }

    @Override
    public void taskUpdateMessage(Task task, String action) {
        appendLine(action);
        appendLine("  " + task);
    }

    @Override
    public void newTaskMessage(Task task, int size) {
        appendLine("Got it. I've added this task: ");
        appendLine("  " + task);
        printTaskListSize(size);
    }

    @Override
    public void printInvalidCommandMessage() {
        appendLine("Bloop bloop.... Seems like you have entered an invalid command!");
    }

    @Override
    public void printTaskListSize(int size) {
        appendLine("You now have " + size + " tasks in the list.");
    }

    @Override
    public void printCustomErrorMessage(String errorMessage) {
        appendLine(errorMessage);
    }

    @Override
    public void printMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            appendLine("No matching tasks found.");
            return;
        }

        appendLine("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            appendLine((i + 1) + ". " + matchingTasks.get(i));
        }
    }

    @Override
    public void showLoadingError() {
        appendLine("Error loading tasks from file.");
    }
}
