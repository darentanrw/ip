import java.util.ArrayList;
public class Bloop {
    public static void printLine() {
        System.out.println("\t--------------------------------");
    }

    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        printLine();
        System.out.println("\tHello I'm Bloop!");
        System.out.println("\tWhat can I do for you?");
        printLine();

        String userInput = System.console().readLine();

        while (!userInput.equals("bye")) {
            printLine();
            if (userInput.equals("list")) {
                if (taskList.size() == 0) {
                    System.out.println("\tNo tasks added yet");
                } else {
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i+1) + ". " + taskList.get(i).toString());
                    }
                }
            } else {
                String[] userInputArray = userInput.split(" ");
                String command = userInputArray[0];
                String userQuery = userInputArray[1];

                if (command.equals("mark") || command.equals("unmark")) {
                    int taskIndex = Integer.parseInt(userQuery) -1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        System.out.println("\tInvalid task index");
                    } else {
                        taskList.get(taskIndex).setStatus(command == "mark" ? true : false);
                        System.out.println("\tNice! I have marked this task as " + (command == "mark" ? "done" : "not done") + ":");
                        System.out.println("\t" + taskList.get(taskIndex).toString());
                    }
                }

                else if (command.equals("deadline")) {
                    String[] userQueryArray = userQuery.split(" /by ");
                    taskList.add(new DeadlineTask(userQueryArray[0], userQueryArray[1]));
                    System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                    System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");
                    
                } else if (command.equals("event")) {
                    String[] userQueryArray = userQuery.split("\\s+/from\\s+|\\s+/to\\s+");
                    taskList.add(new EventTask(userQueryArray[0], userQueryArray[1], userQueryArray[2]));
                    System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                    System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");
                
                } else if (command.equals("todo")) {
                    taskList.add(new ToDoTask(userQuery));
                    System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                    System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");

                } else {
                    System.out.println("\tBloop bloop.... Seems like you have entered an invalid command!");
                }
            }
            printLine();
            userInput = System.console().readLine();
        }

        System.out.println("\t--------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------");
        
    }
}