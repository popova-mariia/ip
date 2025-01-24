import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MariiaChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println(" Hello! I'm MariiaChatbot");
        System.out.println(" What can I do for you?");
        System.out.println("***");

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to never see you again!");
                break;
            }
            else if (input.equals("list")) {
                System.out.println(" Here is your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }

            } else if(input.startsWith("todo")) {
                String description = input.substring(5).trim();
                list.add(new ToDo(description));
                System.out.println("***");
                System.out.println(" You are a buzy man! (woman!) I've added this task:");
                System.out.println("   " + list.get(list.size() - 1).toString());
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
                System.out.println("***");

            } else if (input.startsWith("deadline")){
                String description = input.split(" /")[0].substring(9).trim();
                String by = input.split(" /")[1].substring(3).trim();
                list.add(new Deadline(description, by));
                System.out.println("***");
                System.out.println(" Wow deadline, you better rush. I've added this task:");
                System.out.println("   " + list.get(list.size() - 1).toString());
                System.out.println(" Now you have " + list.size() + " tasks in the list.");
                System.out.println("***");

            } else if (input.startsWith("event")){
                String description = input.split(" /")[0].substring(6).trim();
                String from = input.split(" /")[1].substring(5).trim();
                String to = input.split(" /")[2].substring(3).trim();
                list.add(new Event(description, from, to));
                System.out.println("***");
                System.out.println(" Wow you have an event, you better prepare well. I've added this task:");
                System.out.println("   " + list.get(list.size() - 1).toString());
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
                System.out.println("***");
                System.out.println(" dk what you talkng about");
                System.out.println("***");
            }
        }
        
        scanner.close();
    }
}
