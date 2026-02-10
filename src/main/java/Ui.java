/**
 * Handles all interactions with the user (reading input, printing output).
 */
public class Ui {
    public void printWelcomeMessage() {
        System.out.println("\t--------------------------------\n\tHello I'm Bloop!\n\tWhat can I do for you?\n\t--------------------------------");
    }

    public void printGoodbyeMessage() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public void printLine() {
        System.out.println("\t--------------------------------");
    }

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

    public void taskUpdateMessage(Task task, String action) {
        System.out.println("\t" + action);
        System.out.println("\t\t" + task.toString());
    }

    public void newTaskMessage(Task task, int size) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + task.toString());
        printTaskListSize(size);
    }

    public void printInvalidCommandMessage() {
        System.out.println("\tBloop bloop.... Seems like you have entered an invalid command!");
    }

    public void printTaskListSize(int size) {
        System.out.println("\tYou now have " + size + " tasks in the list.");
    }

    public void printCustomErrorMessage(String errorMessage) {
        System.out.println("\t" + errorMessage);
    }

    public void showLoadingError() {
        System.out.println("\tError loading tasks from file.");
    }
}
