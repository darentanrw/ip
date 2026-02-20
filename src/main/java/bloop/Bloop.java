package bloop;

import java.util.Scanner;

import bloop.parser.Parser;
import bloop.storage.Storage;
import bloop.task.TaskList;
import bloop.ui.Ui;

/**
 * The Bloop class is the entry point for the Bloop application.
 * It initialises necessary components such as Storage,
 * TaskList, and Ui, and for handling the main application loop which processes user input.
 */
public class Bloop {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Bloop object with the specified file path for task storage.
     *
     * @param filePath The file path to load and save tasks.
     */
    public Bloop(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the Bloop application.
     * This method prints a welcome message, enters an input loop to receive and process commands,
     * and finally prints a goodbye message upon exiting.
     */
    public void run() {
        ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            try {
                if (!Parser.handleCommand(userInput, tasks, ui, storage)) {
                    break;
                }
            } catch (RuntimeException e) {
                ui.printCustomErrorMessage("Something went wrong while processing your command.");
                ui.printCustomErrorMessage("Please check your command format and try again.");
                ui.printHelpHint();
                ui.printLine();
            }
        }

        ui.printGoodbyeMessage();
        sc.close();
    }

    /**
     * The main method and entry point to start the Bloop application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Bloop("data/tasks.txt").run();
    }
}
