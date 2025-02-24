package johnny.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import johnny.storage.HardDisk;
import johnny.task.Task;
import johnny.task.TaskList;
import johnny.ui.Ui;

/**
 * The UndoCommand class undoes the most recent modifying command.
 */
public class UndoCommand extends Command {
    private static final String LATEST_CHANGES_FILE = "data/latest-changes.txt";

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
            String response = undoLastCommand(lastCommand, tasks, hardDisk, ui);

            // Clear the latest-changes.txt after undo
            new PrintWriter(file).close();

            return response;
        } catch (IOException e) {
            return ui.showError("Error undoing the last action: " + e.getMessage());
        }
    }

    private String undoLastCommand(String lastCommand, TaskList tasks, HardDisk hardDisk, Ui ui) {
        String[] parts = lastCommand.split(" ", 2);
        String commandType = parts[0];

        switch (commandType) {

        case "todo":
        case "deadline":
        case "event":
            if (!tasks.getTasks().isEmpty()) {
                Task removedTask = tasks.remove(tasks.size() - 1);
                hardDisk.saveTasks(tasks.getTasks());
                return ui.showUndoneTask(removedTask);
            }
            return ui.showNoTasksToUndo();

        case "delete":
            String[] deleteParts = parts[1].split(" ", 2);
            int deletedIndex = Integer.parseInt(deleteParts[0]);
            String deletedTaskString = deleteParts[1];

            Task restoredTask = hardDisk.parseTask(deletedTaskString);
            tasks.addTask(restoredTask);
            hardDisk.saveTasks(tasks.getTasks());
            return ui.showUndoneTask(restoredTask);

        case "mark":
            int markIndex = Integer.parseInt(parts[1]) - 1;
            Task taskToMark = tasks.get(markIndex);
            taskToMark.markAsNotDone();
            hardDisk.saveTasks(tasks.getTasks());
            return ui.showUndoMarkTask(taskToMark);

        case "unmark":
            int unmarkIndex = Integer.parseInt(parts[1]) - 1;
            Task taskToUnmark = tasks.get(unmarkIndex);
            taskToUnmark.markAsDone();
            hardDisk.saveTasks(tasks.getTasks());
            return ui.shoUndoUnmarkTask(taskToUnmark);

        default:
            return ui.showCannotUndoTask();
        }
    }
}
