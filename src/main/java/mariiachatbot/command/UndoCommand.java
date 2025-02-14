package mariiachatbot.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.Task;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;

/**
 * The UndoCommand class undoes the most recent modifying command.
 */
public class UndoCommand extends Command {
    private static final String LATEST_CHANGES_FILE = "./src/main/java/data/latest-changes.txt";

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        try {
            File file = new File(LATEST_CHANGES_FILE);
            if (!file.exists() || file.length() == 0) {
                return ui.showError("No recent changes to undo.");
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String lastCommand = reader.readLine();
            reader.close();

            if (lastCommand == null) {
                return ui.showError("No recent changes to undo.");
            }

            // Handle different types of modifications
            String response = undoLastCommand(lastCommand, tasks, hardDisk);

            // Clear the latest-changes.txt after undo
            new PrintWriter(file).close();

            return response;
        } catch (IOException e) {
            return ui.showError("Error undoing the last action: " + e.getMessage());
        }
    }

    private String undoLastCommand(String lastCommand, TaskList tasks, HardDisk hardDisk) {
        String[] parts = lastCommand.split(" ", 2);
        String commandType = parts[0];

        switch (commandType) {

        case "todo":
        case "deadline":
        case "event":
            if (!tasks.getTasks().isEmpty()) {
                Task removedTask = tasks.remove(tasks.size() - 1);
                hardDisk.saveTasks(tasks.getTasks());
                return "Undid: Added task '" + removedTask.getDescription() + "'";
            }
            return "No tasks to remove.";

        case "delete":
            // Undoing delete requires re-adding the task
            String[] deleteParts = parts[1].split(" ", 2);
            int deletedIndex = Integer.parseInt(deleteParts[0]);
            String deletedTaskString = deleteParts[1];

            // Convert task string back to task object (assuming saved in a simple format)
            Task restoredTask = hardDisk.parseTask(deletedTaskString);
            tasks.addTask(restoredTask);
            hardDisk.saveTasks(tasks.getTasks());
            return "Undid: Deleted task '" + restoredTask.getDescription() + "'";

        case "mark":
            int markIndex = Integer.parseInt(parts[1]) - 1;
            tasks.get(markIndex).markAsNotDone();
            hardDisk.saveTasks(tasks.getTasks());
            return "Undid: Marked task as not done.";

        case "unmark":
            int unmarkIndex = Integer.parseInt(parts[1]) - 1;
            tasks.get(unmarkIndex).markAsDone();
            hardDisk.saveTasks(tasks.getTasks());
            return "Undid: Marked task as done.";

        default:
            return "Cannot undo this action.";
        }
    }
}
