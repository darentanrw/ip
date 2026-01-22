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

            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]); 
                taskIndex-=1; // since user input starts from 1
                
                if (taskIndex < 0 || taskIndex >= taskList.size()) {
                    System.out.println("\tInvalid task index");
                } else {
                    taskList.get(taskIndex).setStatus(true);
                    System.out.println("\tNice! I have marked this task as done:");
                    System.out.println("\t" + taskList.get(taskIndex).toString());

                }

            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]); 
                taskIndex-=1; // since user input starts from 1
                
                if (taskIndex < 0 || taskIndex >= taskList.size()) {
                    System.out.println("\tInvalid task index");
                } else {
                    taskList.get(taskIndex).setStatus(false);
                    System.out.println("\tOK, I have marked this task as not done yet:");
                    System.out.println("\t" + taskList.get(taskIndex).toString());

                }

            } else if (userInput.startsWith("deadline")) {
                userInput = userInput.replaceFirst("deadline ", "");

                String[] userInputArray = userInput.split(" /by ");
                String deadlineName = userInputArray[0];
                String deadlineTime = userInputArray[1];

                taskList.add(new DeadlineTask(deadlineName, deadlineTime));
                System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");


            } else if (userInput.startsWith("event")) {
                userInput = userInput.replaceFirst("event ", "");

                String[] userInputArray = userInput.split(" /from ");
                String eventName = userInputArray[0];
                String eventTime = userInputArray[1].replaceFirst("/from","");

                String[] eventTimeArray = eventTime.split(" /to ");
                String eventStart = eventTimeArray[0];
                String eventEnd = eventTimeArray[1];

                taskList.add(new EventTask(eventName, eventStart, eventEnd));
                System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");
                
            } else if (userInput.startsWith("todo")) {
                userInput = userInput.replaceFirst("todo ", "");
                taskList.add(new ToDoTask(userInput));
                
                System.out.println("\t\t" + taskList.get(taskList.size() - 1).toString());
                System.out.println("\tYou now have " + taskList.size() + " tasks in the list.");
                
            } else {
                System.out.println("\tBloop bloop.... Seems like you have entered an invalid command!");
            }
            printLine();
            userInput = System.console().readLine();
        }

        System.out.println("\t--------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------");
        
    }
}