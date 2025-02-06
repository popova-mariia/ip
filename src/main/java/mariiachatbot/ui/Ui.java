package mariiachatbot.ui;

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
    public void displayWelcomeMessage() {
        System.out.println("***");
        System.out.println(" Hello! I'm MariiaChatbot");
        System.out.println(" What can I do for you?");
        System.out.println("***");
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public String readNextLine() {
        return scanner.nextLine();
    }
    public void showGoodbyeMessage() {
        System.out.println(" Bye. Hope to never see you again!");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks TaskList containing the tasks.
     */
    public void showTaskList(TaskList tasks) {
        System.out.println(" Here is your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }
    /**
     * Shows that a new tasks is added.
     *
     * @param task The task to be added.
     * @param size The total number of tasks after in the list.
     */
    public void showAddTask(Task task, int size) {
        System.out.println("***");
        System.out.println(" You are a busy person! I've added this task:");
        System.out.println("   " + task.toString());
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("***");
    }
    /**
     * Shows that a task was marked as done.
     *
     * @param task The task that is to be marked as done.
     */
    public void showMarkTask(Task task) {
        System.out.println("***");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("***");
    }
    /**
     * The task that is to be marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarkTask(Task task) {
        System.out.println("***");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("***");
    }
    /**
     * Shows that message was deleted.
     *
     * @param task The task that is to be deleted.
     * @param size The total number of tasks in the list.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println("***");
        System.out.println(" Sure, I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("***");
    }
    /**
     * Shows an error message.
     *
     * @param message The error message to be shown.
     */
    public void showError(String message) {
        System.out.println("***");
        System.out.println(" OOPS!!! " + message);
        System.out.println("***");
    }

    /**
     * Shows an error message when there is an problem loading tasks from storage.
     */
    public void showLoadingError() {
        System.out.println("***");
        System.out.println(" OOPS!!! There was an error loading your tasks.");
        System.out.println(" Starting with an empty task list.");
        System.out.println("***");
    }
}
