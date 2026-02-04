import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bloop {
    private Storage storage;
    private ArrayList<Task> taskList;

    public Bloop() {
        this.storage = new Storage();
        this.taskList = this.storage.load();
    }

    public void run() {
        Ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            Ui.printLine();
            if (userInput.equals("list")) {
                Ui.printTaskList(taskList);
            } else {
                String[] userInputArray = userInput.split(" ", 2);
                String[] validCommands = {"mark", "unmark", "deadline", "event", "todo", "delete"};

                if (!List.of(validCommands).contains(userInputArray[0])) {
                    Ui.printInvalidCommandMessage();
                    userInput = sc.nextLine();
                    continue;
                } else if (userInputArray.length < 2){
                    Ui.printCustomErrorMessage("The description of a " + userInputArray[0] + " cannot be empty!");
                    userInput = sc.nextLine();
                    continue;
                }
                String command = userInputArray[0];
                String userQuery = userInputArray[1];
                String[] commandsWithIndex = {"mark", "unmark", "delete"};

                if (List.of(commandsWithIndex).contains(command)) {
                    int taskIndex = Integer.parseInt(userQuery) -1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        Ui.printCustomErrorMessage("Invalid task index.");

                    } else if (command.equals("delete")) {
                        Ui.taskUpdateMessage(taskList.get(taskIndex), "Noted. I've removed this task: ");
                        taskList.remove(taskIndex);
                        
                    } else if (command.equals("mark") || command.equals("unmark")) {
                        taskList.get(taskIndex).setStatus(command.equals("mark") ? true : false);
                        Ui.taskUpdateMessage(taskList.get(taskIndex), "Nice! I have marked this task as " + (command.equals("mark") ? "done" : "not done") + ":");
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

                    Ui.newTaskMessage(taskList.get(taskList.size() - 1), taskList.size());
                }

                storage.save(taskList);

            }
            Ui.printLine();
            userInput = sc.nextLine();
        }

        Ui.printGoodbyeMessage();
        sc.close();
    }

    public static void main(String[] args) {
        Bloop bloop = new Bloop();
        bloop.run();
    }
}