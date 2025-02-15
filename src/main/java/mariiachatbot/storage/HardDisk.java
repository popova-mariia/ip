package mariiachatbot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mariiachatbot.exception.EmptyDescriptionException;
import mariiachatbot.task.Deadline;
import mariiachatbot.task.Event;
import mariiachatbot.task.Task;
import mariiachatbot.task.ToDo;

/**
 * The HardDisk class for the MariiaChatbot program.
 * Works as a storage to save all the tasks to the hard disk and work with them.
 */
public class HardDisk {
    private String filePath;

    public HardDisk(String filePath) {
        assert filePath != null : "File path cannot be null";
        this.filePath = filePath;
    }
    /**
     * Parses the task from hard disk (string format) into a Task format.
     *
     * @param str String input from the hard disk.
     * @return A task which was represented by a string in the file.
     * @throws EmptyDescriptionException If some part of the command (ie description, by, from and to) is absent.
     */
    public Task parseTask(String str) {
        try {
            if (str.startsWith("todo")) {
                String[] parts = str.length() > 5 ? str.substring(5).split(" /done ") : new String[0];
                if (parts.length < 2 || parts[0].trim().isEmpty()) {
                    throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                }
                ToDo todo = new ToDo(parts[0]);
                if (parts[1].trim().equals("X")) {
                    todo.markAsDone();
                } else {
                    todo.markAsNotDone();
                }
                return todo;
            } else if (str.startsWith("deadline")) {
                String[] parts = str.length() > 9 ? str.substring(9).split(" /by | /done ") : new String[0];
                System.out.println(parts);
                if (parts.length < 2 || parts[0].trim().isEmpty()) {
                    throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                }
                LocalDate by = LocalDate.parse(parts[1].trim());
                Deadline deadline = new Deadline(parts[0], by);
                if (parts[2].trim().equals("X")) {
                    deadline.markAsDone();
                } else {
                    deadline.markAsNotDone();
                }
                return deadline;
            } else if (str.startsWith("event")) {
                String[] parts = str.length() > 6 ? str.substring(6).split(" /from | /to | /done ") : new String[0];
                if (parts.length < 4 || parts[0].trim().isEmpty()) {
                    throw new EmptyDescriptionException("The description of an event cannot be empty, and it must "
                            + "include '/from' and '/to' and '/done' parts.");
                }
                LocalDate from = LocalDate.parse(parts[1].trim());
                LocalDate to = LocalDate.parse(parts[2].trim());
                Event event = new Event(parts[0].trim(), from, to);
                if (parts[3].trim().equals("X")) {
                    event.markAsDone();
                } else {
                    event.markAsNotDone();
                }
                return event;
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e);
        }
        return null;
    }
    /**
     * Converts a task format into a string format which can then be saved to hard disk.
     *
     * @param task Task created.
     * @return Text version of the task that can be uploaded to hard disk.
     */
    public String convertToFileString(Task task) {
        String isDone = "N";
        if (task.isDone()) {
            isDone = "X";
        }
        if (task instanceof ToDo) {
            String description = task.getDescription();
            return "todo " + description + " /done " + isDone;
        } else if (task instanceof Deadline) {
            String description = task.getDescription();
            LocalDate by = ((Deadline) task).getBy();
            return "deadline " + description + " /by " + by + " /done " + isDone;
        } else if (task instanceof Event) {
            String description = task.getDescription();
            LocalDate from = ((Event) task).getFrom();
            LocalDate to = ((Event) task).getTo();
            return "event " + description + " /from " + from + " /to " + to + " /done " + isDone;
        } else {
            return " ";
        }
    }

    /**
     * Saves tasks created by the program into the hard disk.
     *
     * @param tasks A list that includes all the tasks ever created and modified.
     * @throws IOException If file does not exists or filewriter cannot be created.
     */
    public void saveTasks(List<Task> tasks) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(convertToFileString(task) + '\n');
            }
            System.out.println("wrote to file");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating a filewriter: " + e.getMessage());
        }
    }

    /**
     * Loads tasks created from the hard disk into the program so it can be modified.
     *
     * @returns A list of tasks loaded from the hard drive.
     * @throws IOException If file is not found, or tasks could not be loaded.
     */
    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        System.out.println("loading files");
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No saved tasks found. Starting with an empty list.");
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                Task task = parseTask(data);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find the file: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Logs the latest modifying command into latest-changes.txt
     */
    public static void logLatestChange(String change) {
        try (FileWriter writer = new FileWriter("./src/main/java/data/latest-changes.txt", false)) {
            writer.write(change);
        } catch (IOException e) {
            System.out.println("Error logging latest change: " + e.getMessage());
        }
    }

}

