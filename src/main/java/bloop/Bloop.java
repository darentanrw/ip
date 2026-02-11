package bloop;

import java.util.Scanner;

import bloop.parser.Parser;
import bloop.storage.Storage;
import bloop.task.TaskList;
import bloop.ui.Ui;

public class Bloop {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Bloop(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.printWelcomeMessage();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            if (!Parser.handleCommand(userInput, tasks, ui, storage)) {
                break;
            }
        }

        ui.printGoodbyeMessage();
        sc.close();
    }

    public static void main(String[] args) {
        new Bloop("data/tasks.txt").run();
    }
}
