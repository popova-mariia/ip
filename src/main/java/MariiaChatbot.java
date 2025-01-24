import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MariiaChatbot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> list = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm MariiaChatbot, your good buddy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else if (input.startsWith("mark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                list.get(index - 1).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println("   " + list.get(index - 1).toString());
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.split(" ")[1]);
                list.get(index - 1).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println("   " + list.get(index - 1).toString());
                System.out.println("____________________________________________________________");
            } else {
                list.add(new Task(input));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
            }
        }
        
        scanner.close();


        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
