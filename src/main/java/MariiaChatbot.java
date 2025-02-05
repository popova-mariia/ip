
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MariiaChatbot {
    public static File PATH = new File("./data/mariia.txt");

    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
		try {
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
			String line = reader.readLine();
			while (line != null) {
                try {
                    if (line.substring(0, 3).equals("[T]")) {
                        String description = line.substring(7);
                        ToDo task = new ToDo(description);
                        if (line.substring(3, 6).equals("[X]")) {
                            task.markAsDone();
                        } else {
                            task.markAsNotDone();
                        }
                        tasks.add(task);
                    } else if (line.substring(0, 3).equals("[D]")) {
                        String descriptionFull = line.substring(7);
                        String description = descriptionFull.split(" \\(by: ")[0];
                        String by = descriptionFull.split(" \\(by: ")[1];
                        Deadline task = new Deadline(description, by);
                        if (line.substring(3, 6).equals("[X]")) {
                            task.markAsDone();
                        } else {
                            task.markAsNotDone();
                        }
                        tasks.add(task);
                    } else if (line.substring(0, 3).equals("[E]")) {
                        String descriptionFull = line.substring(7);
                        String description = descriptionFull.split(" \\(from: ")[0];
                        String fullTimeline = descriptionFull.split(" \\(from: ")[1];
                        String from = fullTimeline.split(" to: ")[0];
                        String to = fullTimeline.split(" to: ")[1];
                        Event task = new Event(description, from, to);
                        if (line.substring(3, 6).equals("[X]")) {
                            task.markAsDone();
                        } else {
                            task.markAsNotDone();
                        }
                        tasks.add(task);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
				line = reader.readLine();
                reader.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        return tasks;
    }

    public static void main(String[] args) {
        List<Task> tasks = MariiaChatbot.loadTasks();

        System.out.println("***");
        System.out.println(" Hello! I'm MariiaChatbot");
        System.out.println(" What can I do for you?");
        System.out.println("***");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    try {
                        FileWriter fileWriter = new FileWriter("./data/mariia.txt", false);
                        for (Task task : tasks) {
                            fileWriter.write(task.toString() + '\n');
                        }
                        fileWriter.close();
                        System.out.println("Tasks successfully saved to file.");
                    } catch (IOException e) {
                        System.out.println("Error writing to file: " + e.getMessage());
                    }
                    System.out.println(" Bye. Hope to never see you again!");
                    break;
                } else if (input.equals("list")) {
                    System.out.println(" Here is your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                } else if (input.startsWith("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task current = tasks.remove(index - 1);

                    System.out.println("***");
                    System.out.println(" Sure, I ll remove this task since you are so lazy:");
                    System.out.println("   " + current.toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("***");

                } else if(input.startsWith("todo")) {
                        String description = input.length() > 5 ? input.substring(5).trim() : "";
                        if (description.isEmpty()) {
                            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                        }
                        tasks.add(new ToDo(description));

                        System.out.println("***");
                        System.out.println(" You are a buzy man! (woman!) I've added this task:");
                        System.out.println("   " + tasks.get(tasks.size() - 1).toString());
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println("***");
                } else if (input.startsWith("deadline")){
                    String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[0];
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                    }
                    Task task = new Deadline(parts[0], parts[1]);
                    tasks.add(task);
                    System.out.println("***");
                    System.out.println(" Wow deadline, you better rush. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1).toString());
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("***");

                } else if (input.startsWith("event")) {
                    String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[0];
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new EmptyDescriptionException("The description of an event cannot be empty, and it must include '/from' and '/to' parts.");
                    }
                    Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    tasks.add(task);
                    System.out.println("***");
                    System.out.println(" Woah new event, you better prepare well. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("***");

                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(index - 1).markAsDone();

                    System.out.println("***");
                    System.out.println(" Nice! I've marked this task as done, good job:");
                    System.out.println("   " + tasks.get(index - 1).toString());
                    System.out.println("***");

                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    tasks.get(index - 1).markAsNotDone();

                    System.out.println("***");
                    System.out.println(" OK, I've marked this task as not done yet, i hope you dont lie " + 
                        "about finishing your work anymore:");
                    System.out.println("   " + tasks.get(index - 1).toString());
                    System.out.println("***");
                    
                } else {
                    throw new UnknownCommandException("Bro have no idea what you mean by \"" + input + 
                        "\", please write clearer...");
                }
            } catch (Exception e) {
                System.out.println("***");
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println("***");
            }
        }
        scanner.close();
    }
}
