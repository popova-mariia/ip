import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MariiaChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println("***");
        System.out.println(" Hello! I'm MariiaChatbot");
        System.out.println(" What can I do for you?");
        System.out.println("***");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
            
                if (input.equals("bye")) {
                    System.out.println(" Bye. Hope to never see you again!");
                    break;
                }
                else if (input.equals("list")) {
                    System.out.println(" Here is your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }

                }else if (input.startsWith("delete")) {

                    int index = Integer.parseInt(input.split(" ")[1]);
                    Task current = list.remove(index - 1);
                    System.out.println("***");
                    System.out.println(" Sure, I ll remove this task since you are so lazy:");
                    System.out.println("   " + current.toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("***");
                } else if(input.startsWith("todo")) {
                    String description = input.length() > 5 ? input.substring(5).trim() : "";

                    if (description.isEmpty()) {
                        throw new EmptyDescriptionException("The description of a todo cannot be empty.");
                    }

                    list.add(new ToDo(description));
                    System.out.println("***");
                    System.out.println(" You are a buzy man! (woman!) I've added this task:");
                    System.out.println("   " + list.get(list.size() - 1).toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("***");

                } else if (input.startsWith("deadline")){
                    String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[0];
                    if (parts.length < 2 || parts[0].trim().isEmpty()) {
                            throw new EmptyDescriptionException("The description of a deadline cannot be empty.");
                    }
                    Task task = new Deadline(parts[0], parts[1]);
                    list.add(task);
                    System.out.println("***");
                    System.out.println(" Wow deadline, you better rush. I've added this task:");
                    System.out.println("   " + list.get(list.size() - 1).toString());
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("***");

                } else if (input.startsWith("event")) {
                    String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[0];
                    if (parts.length < 3 || parts[0].trim().isEmpty()) {
                        throw new EmptyDescriptionException("The description of an event cannot be empty, and it must include '/from' and '/to' parts.");
                    }
                    Task task = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                    list.add(task);
                    System.out.println("***");
                    System.out.println(" Woah new event, you better prepare well. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + list.size() + " tasks in the list.");
                    System.out.println("***");
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    list.get(index - 1).markAsDone();
                    System.out.println("***");
                    System.out.println(" Nice! I've marked this task as done, good job:");
                    System.out.println("   " + list.get(index - 1).toString());
                    System.out.println("***");

                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]);
                    list.get(index - 1).markAsNotDone();
                    System.out.println("***");
                    System.out.println(" OK, I've marked this task as not done yet, i hope you dont lie about finishing your work anymore:");
                    System.out.println("   " + list.get(index - 1).toString());
                    System.out.println("***");
                } else {
                    throw new UnknownCommandException("Bro have no idea what you mean by \"" + input + "\", please write clearer...");
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
