public class Bloop {
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        System.out.println("Hello I'm Bloop!");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------");

        String userInput = System.console().readLine();

        while (!userInput.equals("bye")) {
            System.out.println("--------------------------------");
            System.out.println("" + userInput);
            System.out.println("--------------------------------");
            userInput = System.console().readLine();
        }

        System.out.println("--------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------");
        
    }
}
