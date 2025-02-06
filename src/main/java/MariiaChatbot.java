import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;
/**
 * The main class for the MariiaChatbot program.
 * 
 * To use my bot, run the program and use the following supported programs:
 * - `bye`: Exits the program and saves tasks to the hard disk.
 * - `list`: lists all current tasks.
 * - `todo <description>`: Adds a ToDo task.
 * - `deadline <description> /by <date>`: Adds a Deadline task.
 * - `event <description> /from <start> /to <end>`: Adds an Event task.
 * - `mark <index>`: Marks task as done. Index must be within boundaries.
 * - `unmark <index>`: Marks task as not done.
 * - `delete <index>`: Deletes the task at specified index.
 */
public class MariiaChatbot {
    /**
     * Marks the entry point for the MariiaChatbot program.
     * Initializes the task list, loads tasks from disk, and interacts with the user via the console.
     * Executes bye, list, todo, deadline, event, mark, unmark and delete commands read from console.
     * Saves tasks to disk when the user quits the program.
     *
     * @param args Command-line arguments (not used).
     */
     public static void main(String[] args) {
        List<Task> tasks = HardDisk.loadTasks();
        System.out.println("loaded tasks: " + tasks);

        System.out.println("***");
        System.out.println(" Hello! I'm MariiaChatbot");
        System.out.println(" What can I do for you?");
        System.out.println("***");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    HardDisk.saveTasks(tasks);
                    System.out.println("Tasks successfully saved to hard disk.");
                    System.out.println(" Bye. Hope to never see you again!");
                    break;
                } else if (input.startsWith("list")) {
                    System.out.println(" Here is your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if(input.startsWith("todo")) {
                    String description = input.length() > 5 ? input.substring(5).trim() : "";

                    if (!description.isEmpty()) {
                        tasks.add(new ToDo(description));
                        HardDisk.saveTasks(tasks);
    
                        System.out.println("***");
                        System.out.println(" You are a buzy man! (woman!) I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1).toString());
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("***");
                    } else {
                        System.out.println("The description is definitely missing");
                    }

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[0];
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                    }
                    if (!parts[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
                        System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                    } else {
                        LocalDate by = LocalDate.parse(parts[1]);
                        Task task = new Deadline(parts[0], by);
                        tasks.add(task);
                        HardDisk.saveTasks(tasks);

                        System.out.println("***");
                        System.out.println(" Wow deadline, you better rush. I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1).toString());
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("***");

                    }
                    
                } else if (input.startsWith("event")) {
                    String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[0];
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new EmptyDescriptionException("The description of an event cannot be empty, "
                                + "and it must include '/from' and '/to' parts.");
                    }
                    LocalDate from = LocalDate.parse(parts[1].trim());
                    LocalDate to = LocalDate.parse(parts[2].trim());
                    Task task = new Event(parts[0].trim(), from, to);
                    tasks.add(task);
                    HardDisk.saveTasks(tasks);

                    System.out.println("***");
                    System.out.println(" Woah new event, you better prepare well. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("***");
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index <= tasks.size() && index > 0) {
                        tasks.get(index - 1).markAsDone();
                        HardDisk.saveTasks(tasks);
                        System.out.println("***");
                        System.out.println(" Nice! I've marked this task as done, good job:");
                        System.out.println("   " + tasks.get(index - 1).toString());
                        System.out.println("***");
                    } else {
                        System.out.println("Your index is invalid dummy");
                    }

                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index <= tasks.size() && index > 0) {
                        tasks.get(index - 1).markAsNotDone();
                        HardDisk.saveTasks(tasks);

                        System.out.println("***");
                        System.out.println(" OK, I've marked this task as not done yet, i hope you dont lie " + 
                            "about finishing your work anymore:");
                        System.out.println("   " + tasks.get(index - 1).toString());
                        System.out.println("***");
                    } else {
                        System.out.println("Your index is invalid dummy");
                    }
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    if (index <= tasks.size() && index > 0) {
                        Task current = tasks.remove(index - 1);
                        HardDisk.saveTasks(tasks);
                        System.out.println("***");
                        System.out.println(" Sure, I ll remove this task since you are so lazy:");
                        System.out.println("   " + current.toString());
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("***");
                    } else {
                        System.out.println("Your index is invalid dummy");
                    }
                } else {
                    System.out.println("***");
                    System.out.println("Bro have no idea what you mean by \"" + input + 
                        "\", please write clearer...");
                    System.out.println("***");
                }
            } catch (Exception e) {
                System.out.println();
                System.out.println("Error starting chatbot: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
