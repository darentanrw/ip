import java.util.ArrayList;
public class Bloop {
    public static void main(String[] args) {
        ArrayList<String> taskList = new ArrayList<>();

        System.out.println("\t--------------------------------");
        System.out.println("\tHello I'm Bloop!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t--------------------------------");

        String userInput = System.console().readLine();

        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) {
                System.out.println("\t--------------------------------");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("\t" + (i+1) + ". " + taskList.get(i));
                }
                System.out.println("\t--------------------------------");
            } else {
                System.out.println("\t--------------------------------");
                taskList.add(userInput);
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