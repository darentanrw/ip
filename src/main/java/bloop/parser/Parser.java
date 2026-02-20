package bloop.parser;

import java.time.format.DateTimeParseException;
import java.util.List;

import bloop.task.Task;
import bloop.task.TaskList;
import bloop.task.ToDoTask;
import bloop.task.DeadlineTask;
import bloop.task.EventTask;
import bloop.ui.Ui;
import bloop.storage.Storage;

/**
 * Handles parsing and execution of user commands.
 */
public class Parser {
    private static final String[] VALID_COMMANDS = {"mark", "unmark", "deadline", "event", "todo", "delete", "find"};
    private static final String[] INDEX_COMMANDS = {"mark", "unmark", "delete"};
    private static final int USER_TO_ARRAY_INDEX_OFFSET = 1;

    /**
     * Parses and executes the user's command.
     *
     * @param userInput the raw user input
     * @param tasks     the task list to operate on
     * @param ui        the UI for displaying messages
     * @param storage   the storage for saving tasks
     * @return true if the program should continue, false if user wants to exit
     */
    public static boolean handleCommand(String userInput, TaskList tasks, Ui ui, Storage storage) {
        assert userInput != null : "Parser expects non-null user input.";
        assert tasks != null : "Parser expects a non-null task list.";
        assert ui != null : "Parser expects a non-null UI instance.";
        assert storage != null : "Parser expects a non-null storage instance.";

        String trimmed = userInput.strip();

        if (trimmed.isEmpty()) {
            ui.printCustomErrorMessage("Please enter a command.");
            ui.printHelpHint();
            ui.printLine();
            return true;
        }

        if (trimmed.equals("bye")) {
            return false;
        }

        ui.printLine();

        if (trimmed.equals("list")) {
            ui.printTaskList(tasks);
            ui.printLine();
            return true;
        }

        if (trimmed.equals("help")) {
            ui.printHelpMessage();
            ui.printLine();
            return true;
        }

        String[] userInputArray = trimmed.split(" ", 2);
        String command = userInputArray[0];

        if (!List.of(VALID_COMMANDS).contains(command)) {
            ui.printInvalidCommandMessage();
            ui.printHelpHint();
            ui.printLine();
            return true;
        }

        if (userInputArray.length < 2) {
            ui.printCustomErrorMessage("The description of a " + command + " cannot be empty!");
            ui.printHelpHint();
            ui.printLine();
            return true;
        }

        String userQuery = userInputArray[1].strip();

        if (userQuery.isEmpty()) {
            ui.printCustomErrorMessage("The description of a " + command + " cannot be empty!");
            ui.printHelpHint();
            ui.printLine();
            return true;
        }

        if (command.equals("find")) {
            ui.printMatchingTasks(tasks.findTasks(userQuery));
            ui.printLine();
            return true;
        }

        boolean didMutate;
        if (List.of(INDEX_COMMANDS).contains(command)) {
            didMutate = handleIndexCommand(command, userQuery, tasks, ui);
        } else {
            didMutate = handleTaskCreation(command, userQuery, tasks, ui);
        }

        if (!didMutate) {
            ui.printLine();
            return true;
        }

        storage.save(tasks);
        ui.printLine();
        return true;
    }

    private static boolean handleIndexCommand(String command, String userQuery, TaskList tasks, Ui ui) {
        assert List.of(INDEX_COMMANDS).contains(command) : "Index handler expects mark/unmark/delete commands only.";
        assert userQuery != null : "Index handler expects a non-null query.";
        assert tasks != null : "Index handler expects a non-null task list.";
        assert ui != null : "Index handler expects a non-null UI instance.";

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(userQuery) - USER_TO_ARRAY_INDEX_OFFSET;
        } catch (NumberFormatException e) {
            ui.printCustomErrorMessage("Invalid task index. Please provide a number.");
            ui.printHelpHint();
            return false;
        }

        if (!tasks.isValidIndex(taskIndex)) {
            ui.printCustomErrorMessage("Invalid task index.");
            ui.printHelpHint();
            return false;
        }

        if (command.equals("delete")) {
            Task removed = tasks.getTask(taskIndex);
            ui.taskUpdateMessage(removed, "Noted. I've removed this task: ");
            tasks.deleteTask(taskIndex);
            return true;
        }

        boolean isDone = command.equals("mark");
        tasks.getTask(taskIndex).setStatus(isDone);
        ui.taskUpdateMessage(tasks.getTask(taskIndex),
                "Nice! I have marked this task as " + (isDone ? "done" : "not done") + ":");
        return true;
    }

    private static boolean handleTaskCreation(String command, String userQuery, TaskList tasks, Ui ui) {
        assert command.equals("deadline") || command.equals("event") || command.equals("todo")
                : "Task creation expects deadline/event/todo commands only.";
        assert userQuery != null : "Task creation expects a non-null query.";
        assert tasks != null : "Task creation expects a non-null task list.";
        assert ui != null : "Task creation expects a non-null UI instance.";

        Task newTask;

        if (command.equals("deadline")) {
            String[] parts = userQuery.split("\\s+/by\\s+", 2);
            if (parts.length < 2 || parts[0].isBlank() || parts[1].isBlank()) {
                ui.printCustomErrorMessage("Invalid deadline format. Use: deadline <description> /by <d/M/yyyy HHmm>");
                ui.printHelpHint();
                return false;
            }
            try {
                newTask = new DeadlineTask(parts[0].strip(), parts[1].strip());
            } catch (DateTimeParseException e) {
                ui.printCustomErrorMessage("Invalid date/time format. Use: d/M/yyyy HHmm (e.g. 2/12/2019 1800)");
                ui.printHelpHint();
                return false;
            }
        } else if (command.equals("event")) {
            String[] fromSplit = userQuery.split("\\s+/from\\s+", 2);
            if (fromSplit.length < 2 || fromSplit[0].isBlank() || fromSplit[1].isBlank()) {
                ui.printCustomErrorMessage(
                        "Invalid event format. Use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
                ui.printHelpHint();
                return false;
            }

            String[] toSplit = fromSplit[1].split("\\s+/to\\s+", 2);
            if (toSplit.length < 2 || toSplit[0].isBlank() || toSplit[1].isBlank()) {
                ui.printCustomErrorMessage(
                        "Invalid event format. Use: event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>");
                ui.printHelpHint();
                return false;
            }

            try {
                newTask = new EventTask(fromSplit[0].strip(), toSplit[0].strip(), toSplit[1].strip());
            } catch (DateTimeParseException e) {
                ui.printCustomErrorMessage("Invalid date/time format. Use: d/M/yyyy HHmm (e.g. 2/12/2019 1800)");
                ui.printHelpHint();
                return false;
            }
        } else {
            newTask = new ToDoTask(userQuery.strip());
        }

        tasks.addTask(newTask);
        ui.newTaskMessage(newTask, tasks.size());
        return true;
    }
}
