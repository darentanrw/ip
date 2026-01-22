import java.util.ArrayList;
public class Bloop {
    public static void main(String[] args) {

        ArrayList<Pair> taskList = new ArrayList<>();

        System.out.println("\t--------------------------------");
        System.out.println("\tHello I'm Bloop!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t--------------------------------");

        String userInput = System.console().readLine();

        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                System.out.println("\t--------------------------------");
                if (taskList.size() == 0) {
                    System.out.println("\tNo tasks added yet");
                } else {
                    System.out.println("\tHere are the tasks in your list:");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("\t" + (i+1) + ". " + taskList.get(i).toString());
                    }
                }
                System.out.println("\t--------------------------------");
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]); 
                taskIndex-=1; // since user input starts from 1
                
                if (taskIndex < 0 || taskIndex >= taskList.size()) {
                    System.out.println("\tInvalid task index");
                } else {
                    taskList.get(taskIndex).setStatus(true);
                    System.out.println("\t--------------------------------");
                    System.out.println("\tNice! I have marked this task as done:");
                    System.out.println("\t" + taskList.get(taskIndex).toString());
                    System.out.println("\t--------------------------------");
                }
            } else {
                System.out.println("\t--------------------------------");
                taskList.add(new Pair(userInput));
                System.out.println("\t added: " + userInput);
                System.out.println("\t--------------------------------");
            }
            userInput = System.console().readLine();
        }

        System.out.println("\t--------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------");
        
    }
}