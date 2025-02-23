package johnny.ui;

import java.util.List;
import java.util.Scanner;

import johnny.task.Task;
import johnny.task.TaskList;


/**
 * Handles input and output from user.
 */
public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message.
     */
    public String displayWelcomeMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("You're talking to Johnny now.\n");
        sb.append("Not here to waste time—just tell me what you need.\n");
        return sb.toString();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String readNextLine() {
        return scanner.nextLine();
    }
    public String showGoodbyeMessage() {
        return "Alright, I'm out. Try not to mess things up while I'm gone.";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks TaskList containing the tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here’s what you've got:\n");
        if (tasks.size() <= 0) {
            sb.append("Nothing. Either you're done, or you've done nothing.\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Shows that a new tasks is added.
     *
     * @param task The task to be added.
     * @param size The total number of tasks after in the list.
     */
    public String showAddTask(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("Another task? You sure you can handle this?\n");
        sb.append("Added: ").append(task.toString()).append("\n");
        sb.append("That makes ").append(size).append(" tasks. Better get moving.\n");
        return sb.toString();
    }
    /**
     * Shows that a task was marked as done.
     *
     * @param task The task that is to be marked as done.
     */
    public String showMarkTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Finally, some progress. Task completed:\n");
        sb.append("   ").append(task.toString()).append("\n");
        return sb.toString();
    }
    /**
     * Returns a message indicating that the specified task has been marked as incomplete.
     *
     * @param task The task that was unmarked.
     * @return A formatted string confirming the task is now incomplete.
     */
    public String showUnmarkTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Changed your mind? Alright.\n");
        sb.append("Task is back to incomplete:\n");
        sb.append("   ").append(task.toString()).append("\n");
        return sb.toString();
    }
    /**
     * Shows that message was deleted.
     *
     * @param task The task that is to be deleted.
     * @param size The total number of tasks in the list.
     */
    public String showDeleteTask(Task task, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Task scrapped:\n");
        sb.append("   ").append(task.toString()).append("\n");
        sb.append("You're down to ").append(size).append(" tasks in the list.\n");
        return sb.toString();
    }
    /**
     * Shows an error message.
     *
     * @param message The error message to be shown.
     */
    public String showError(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append(message).append("\n");
        return sb.toString();
    }

    /**
     * Shows an error message when there is a problem loading tasks from storage.
     */
    public String showLoadingError() {
        StringBuilder sb = new StringBuilder();
        sb.append("***\n");
        sb.append(" There was an error loading your tasks.\n");
        sb.append(" Starting with an empty task list.\n");
        sb.append("***\n");
        return sb.toString();
    }
    /**
     * Shows the find results based on the keyword.
     *
     * @param tasks The list of matching tasks.
     */
    public String showFindResults(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here’s what I found:\n");
        if (tasks.isEmpty()) {
            sb.append("No matches. Guess you're searching for ghosts.\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * Shows task that was recently undone.
     *
     * @param task The task to be undone.
     */
    public String showUndoneTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Backpedaling already? Fine, I'll undo it.\n");
        sb.append("Task: " + task.getDescription()).append(task.toString()).append("\n");
        return sb.toString();
    }
    public String showNoTasksToUndo() {
        return "Nothing left to undo.";
    }
    public String showUndoMarkTask(Task task) {
        return "Undid: Marked task as not done.\n" + task.getDescription();
    }
    public String shoUndoUnmarkTask(Task task) {
        return "Undid: Marked task as done.\n" + task.getDescription();
    }
    public String showCannotUndoTask() {
        return "Cannot undo this action.";
    }
}
