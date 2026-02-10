import java.util.List;

/**
 * Handles parsing and execution of user commands.
 */
public class Parser {
    private static final String[] VALID_COMMANDS = {"mark", "unmark", "deadline", "event", "todo", "delete"};
    private static final String[] INDEX_COMMANDS = {"mark", "unmark", "delete"};

    /**
     * Parses and executes the user's command.
     *
     * @param userInput the raw user input
     * @param tasks the task list to operate on
     * @param ui the UI for displaying messages
     * @param storage the storage for saving tasks
     * @return true if the program should continue, false if user wants to exit
     */
    public static boolean handleCommand(String userInput, TaskList tasks, Ui ui, Storage storage) {
        if (userInput.equals("bye")) {
            return false;
        }

        ui.printLine();

        if (userInput.equals("list")) {
            ui.printTaskList(tasks);
            ui.printLine();
            return true;
        }

        String[] userInputArray = userInput.split(" ", 2);
        String command = userInputArray[0];

        if (!List.of(VALID_COMMANDS).contains(command)) {
            ui.printInvalidCommandMessage();
            ui.printLine();
            return true;
        }

        if (userInputArray.length < 2) {
            ui.printCustomErrorMessage("The description of a " + command + " cannot be empty!");
            ui.printLine();
            return true;
        }

        String userQuery = userInputArray[1];

        if (List.of(INDEX_COMMANDS).contains(command)) {
            handleIndexCommand(command, userQuery, tasks, ui);
        } else {
            handleTaskCreation(command, userQuery, tasks, ui);
        }

        storage.save(tasks);
        ui.printLine();
        return true;
    }

    private static void handleIndexCommand(String command, String userQuery, TaskList tasks, Ui ui) {
        int taskIndex = Integer.parseInt(userQuery) - 1;

        if (!tasks.isValidIndex(taskIndex)) {
            ui.printCustomErrorMessage("Invalid task index.");
        } else if (command.equals("delete")) {
            Task removed = tasks.getTask(taskIndex);
            ui.taskUpdateMessage(removed, "Noted. I've removed this task: ");
            tasks.deleteTask(taskIndex);
        } else {
            boolean isDone = command.equals("mark");
            tasks.getTask(taskIndex).setStatus(isDone);
            ui.taskUpdateMessage(tasks.getTask(taskIndex),
                    "Nice! I have marked this task as " + (isDone ? "done" : "not done") + ":");
        }
    }

    private static void handleTaskCreation(String command, String userQuery, TaskList tasks, Ui ui) {
        Task newTask;

        if (command.equals("deadline")) {
            String[] parts = userQuery.split(" /by ");
            newTask = new DeadlineTask(parts[0], parts[1]);
        } else if (command.equals("event")) {
            String[] parts = userQuery.split("\\s+/from\\s+|\\s+/to\\s+");
            newTask = new EventTask(parts[0], parts[1], parts[2]);
        } else {
            newTask = new ToDoTask(userQuery);
        }

        tasks.addTask(newTask);
        ui.newTaskMessage(newTask, tasks.size());
    }
}
