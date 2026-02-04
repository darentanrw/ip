import java.util.ArrayList;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println("\t--------------------------------\n\tHello I'm Bloop!\n\tWhat can I do for you?\n\t--------------------------------");
    }

    public static void printGoodbyeMessage() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t--------------------------------");
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("\tNo tasks added yet");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t" + (i+1) + ". " + taskList.get(i).toString());
            }
        }
    }

    public static void taskUpdateMessage(Task task, String action) {
        System.out.println("\t" + action); 
        System.out.println("\t\t" + task.toString());
        printLine();
    }

    public static void newTaskMessage(Task task, int size) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + task.toString());
        printTaskListSize(size);
    }


    public static void printInvalidCommandMessage() {
        System.out.println("\tBloop bloop.... Seems like you have entered an invalid command!");
        printLine();
    }

    public static void printTaskListSize(int size) {
        System.out.println("\tYou now have " + size + " tasks in the list.");
    }

    public static void printCustomErrorMessage(String errorMessage) {
        System.out.println("\t" + errorMessage);
        printLine();
    }
}
