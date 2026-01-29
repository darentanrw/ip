import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bloop {
    public static void printLine() {
        System.out.println("\t--------------------------------");
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        ArrayList<Task> taskList = storage.load();
        
        System.out.println("\t--------------------------------\n\tHello I'm Bloop!\n\tWhat can I do for you?\n\t--------------------------------");
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

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
                String[] userInputArray = userInput.split(" ", 2);
                String[] validCommands = {"mark", "unmark", "deadline", "event", "todo", "delete"};

                if (!List.of(validCommands).contains(userInputArray[0])) {
                    System.out.println("\tBloop bloop.... Seems like you have entered an invalid command!");
                    printLine();
                    userInput = sc.nextLine();
                    continue;
                } else if (userInputArray.length < 2){
                    System.out.println("\t The description of a " + userInputArray[0] + " cannot be empty!");
                    printLine();
                    userInput = sc.nextLine();
                    continue;
                }
                String command = userInputArray[0];
                String userQuery = userInputArray[1];
                String[] commandsWithIndex = {"mark", "unmark", "delete"};

                if (List.of(commandsWithIndex).contains(command)) {
                    int taskIndex = Integer.parseInt(userQuery) -1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        System.out.println("\tInvalid task index.");

                    } else if (command.equals("delete")) {
                        System.out.println("\tNoted. I've removed this task: ");
                        System.out.println("\t" + taskList.get(taskIndex).toString());
                        taskList.remove(taskIndex);
                        System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");
                        
                    } else if (command.equals("mark") || command.equals("unmark")) {
                        taskList.get(taskIndex).setStatus(command.equals("mark") ? true : false);
                        System.out.println("\tNice! I have marked this task as " + (command.equals("mark") ? "done" : "not done") + ":");
                        System.out.println("\t" + taskList.get(taskIndex).toString());
                    }
                }

                else {
                    if (command.equals("deadline")) {    
                        String[] userQueryArray = userQuery.split(" /by ");
                        taskList.add(new DeadlineTask(userQueryArray[0], userQueryArray[1]));
                    } else if (command.equals("event")) {
                        String[] userQueryArray = userQuery.split("\\s+/from\\s+|\\s+/to\\s+");
                        taskList.add(new EventTask(userQueryArray[0], userQueryArray[1], userQueryArray[2]));
                    } else if (command.equals("todo")) {
                        taskList.add(new ToDoTask(userQuery));
                    }

                    System.out.println("\tGot it. I've added this task: ");
                    System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                    System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");
                }

                storage.save(taskList);

            }
            printLine();
            userInput = sc.nextLine();
        }

        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
        sc.close();
    }
}