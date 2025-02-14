package mariiachatbot.ui;

import java.util.List;
import java.util.Scanner;

import mariiachatbot.task.Task;
import mariiachatbot.task.TaskList;


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
        sb.append("***\n");
        sb.append(" Hello! I'm MariiaChatbot\n");
        sb.append(" What can I do for you?\n");
        sb.append("***\n");
        return sb.toString();
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String readNextLine() {
        return scanner.nextLine();
    }
    public String showGoodbyeMessage() {
        return "Bye. Hope to never see you again!";
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks TaskList containing the tasks.
     */
    public String showTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Here is your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
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
        sb.append("***\n");
        sb.append(" You are a busy person! I've added this task:\n");
        sb.append("   ").append(task.toString()).append("\n");
        sb.append(" Now you have ").append(size).append(" tasks in the list.\n");
        sb.append("***\n");
        return sb.toString();
    }
    /**
     * Shows that a task was marked as done.
     *
     * @param task The task that is to be marked as done.
     */
    public String showMarkTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("***\n");
        sb.append(" Nice! I've marked this task as done:\n");
        sb.append("   ").append(task.toString()).append("\n");
        sb.append("***\n");
        return sb.toString();
    }
    /**
     * The task that is to be marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public String showUnmarkTask(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("***\n");
        sb.append(" OK, I've marked this task as not done yet:\n");
        sb.append("   ").append(task.toString()).append("\n");
        sb.append("***\n");
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
        sb.append("***\n");
        sb.append(" Sure, I've removed this task:\n");
        sb.append("   ").append(task.toString()).append("\n");
        sb.append(" Now you have ").append(size).append(" tasks in the list.\n");
        sb.append("***\n");
        return sb.toString();
    }
    /**
     * Shows an error message.
     *
     * @param message The error message to be shown.
     */
    public String showError(String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("***\n");
        sb.append(" OOPS!!! ").append(message).append("\n");
        sb.append("***\n");
        return sb.toString();
    }

    /**
     * Shows an error message when there is an problem loading tasks from storage.
     */
    public String showLoadingError() {
        StringBuilder sb = new StringBuilder();
        sb.append("***\n");
        sb.append(" OOPS!!! There was an error loading your tasks.\n");
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
        sb.append(" Here are the matching tasks in your list:\n");
        if (tasks.isEmpty()) {
            sb.append(" No matching tasks found.\n");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(".").append(tasks.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
