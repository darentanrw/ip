public class Bloop {
    public static void main(String[] args) {
        System.out.println("\t--------------------------------");
        System.out.println("\tHello I'm Bloop!");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t--------------------------------");

        String userInput = System.console().readLine();

        while (!userInput.equals("bye")) {
            System.out.println("\t--------------------------------");
            System.out.println("\t" + userInput);
            System.out.println("\t--------------------------------");
            userInput = System.console().readLine();
        }

        System.out.println("\t--------------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t--------------------------------");
        
    }
}
