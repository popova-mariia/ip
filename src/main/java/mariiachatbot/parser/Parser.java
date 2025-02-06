package mariiachatbot.parser;

import java.time.LocalDate;

import mariiachatbot.command.ByeCommand;
import mariiachatbot.command.Command;
import mariiachatbot.command.DeadlineCommand;
import mariiachatbot.command.DeleteCommand;
import mariiachatbot.command.EventCommand;
import mariiachatbot.command.FindCommand;
import mariiachatbot.command.InvalidCommand;
import mariiachatbot.command.ListCommand;
import mariiachatbot.command.MarkCommand;
import mariiachatbot.command.ToDoCommand;
import mariiachatbot.command.UnmarkCommand;
import mariiachatbot.task.Deadline;
import mariiachatbot.task.Event;
import mariiachatbot.task.ToDo;
/**
 * Parser class which interprets input line commands and converts them into Command class.
 */
public class Parser {
    /**
     * Parses the user input string and returns the corresponding Command object.
     * Added something to commit for branch-A-CodingStandard.
     *
     * @param input The user input string to be parsed.
     * @return The corresponding Command object representing the user's action.
     */
    public Command parseCommand(String input) {
        if (input.startsWith("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            String description = input.length() > 5 ? input.substring(5).trim() : "";
            if (!description.isEmpty()) {
                ToDo todo = new ToDo(description);
                return new ToDoCommand(todo);
            } else {
                return new InvalidCommand("This command sounds like a keyspam");
            }
        } else if (input.startsWith("deadline")) {
            String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[0];
            if (parts.length < 2 || parts[0].trim().isEmpty()) {
                return new InvalidCommand("The description of a deadline cannot be empty.");
            }
            if (!parts[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
                return new InvalidCommand("Invalid date format. Please use yyyy-MM-dd.");
            } else {
                LocalDate by = LocalDate.parse(parts[1]);
                Deadline deadline = new Deadline(parts[0], by);
                return new DeadlineCommand(deadline);
            }
        } else if (input.startsWith("event")) {
            String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[0];
            if (parts.length < 3 || parts[0].trim().isEmpty()) {
                return new InvalidCommand("The description of an event cannot be empty, "
                        + "and it must include '/from' and '/to' parts.");
            }
            LocalDate from = LocalDate.parse(parts[1].trim());
            LocalDate to = LocalDate.parse(parts[2].trim());
            Event event = new Event(parts[0].trim(), from, to);
            return new EventCommand(event);
        } else if (input.startsWith("mark ")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new MarkCommand(index);
        } else if (input.startsWith("unmark ")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new UnmarkCommand(index);
        } else if (input.startsWith("delete")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(index);
        } else if (input.startsWith("find")) {
            String keyword = input.length() > 5 ? input.substring(5).trim() : "";
            if (keyword.isEmpty()) {
                return new InvalidCommand("Dk what to search for, try again.");
            }
            return new FindCommand(keyword);
        } else {
            return new InvalidCommand("Bro have no idea what you mean by \"" + input
                    + "\", please write clearer...");
        }
    }
}
