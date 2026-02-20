package bloop;

import bloop.parser.Parser;
import bloop.storage.Storage;
import bloop.task.TaskList;
import bloop.ui.GuiUi;

/**
 * Chat-oriented service that reuses existing parser/storage logic.
 */
public class BloopChatService {
    private final Storage storage;
    private final TaskList tasks;
    private final GuiUi ui;

    public BloopChatService(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.ui = new GuiUi();
    }

    /**
     * Returns the initial greeting shown in the GUI.
     */
    public String getWelcomeMessage() {
        ui.resetBuffer();
        ui.printWelcomeMessage();
        return ui.consumeOutput();
    }

    /**
     * Returns Bloop's response for a user command.
     */
    public String getResponse(String userInput) {
        assert userInput != null : "Chat expects non-null user input.";
        ui.resetBuffer();
        try {
            boolean shouldContinue = Parser.handleCommand(userInput, tasks, ui, storage);
            if (!shouldContinue) {
                ui.printGoodbyeMessage();
            }
        } catch (RuntimeException e) {
            ui.printCustomErrorMessage("Something went wrong while processing your command.");
            ui.printCustomErrorMessage("Please check your command format and try again.");
            ui.printHelpHint();
            ui.printLine();
        }
        String response = ui.consumeOutput();
        assert !response.isBlank() : "Command should produce a visible response.";
        return response;
    }

    /**
     * Indicates whether the app should exit based on user input.
     */
    public boolean shouldExit(String userInput) {
        return "bye".equals(userInput.strip());
    }
}
