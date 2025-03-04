package johnny.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import johnny.command.ByeCommand;
import johnny.command.Command;
import johnny.command.DeadlineCommand;
import johnny.command.DeleteCommand;
import johnny.command.EventCommand;
import johnny.command.FindCommand;
import johnny.command.InvalidCommand;
import johnny.command.ListCommand;
import johnny.command.MarkCommand;
import johnny.command.ToDoCommand;
import johnny.command.UndoCommand;
import johnny.command.UnmarkCommand;
import johnny.task.Deadline;
import johnny.task.Event;
import johnny.task.ToDo;
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
    public static Command parseCommand(String input) {
        assert input != null : "Input command should not be null";
        if (input.startsWith("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            return parseToDoCommand(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return parseEventCommand(input);
        } else if (input.startsWith("mark ")) {
            return parseMarkCommand(input);
        } else if (input.startsWith("unmark ")) {
            return parseUnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("find")) {
            return parseFindCommand(input);
        } else if (input.startsWith("undo")) {
            return new UndoCommand();
        } else {
            return new InvalidCommand("That supposed to be a command? ‘Cause all I see is keyboard smashing...");
        }
    }

    /**
     * Parses input to produce a todo command or invalid command.
     *
     * @param input A String typed by the user.
     * @return A todo command.
     */
    public static Command parseToDoCommand(String input) {
        String description = input.length() > 5 ? input.substring(5).trim() : "";
        if (!description.isEmpty()) {
            ToDo todo = new ToDo(description);
            return new ToDoCommand(todo);
        } else {
            return new InvalidCommand("This command sounds like a keyspam");
        }
    }
    /**
     * Parses input to produce a deadline command or invalid command.
     *
     * @param input A String typed by the user.
     * @return A deadline command.
     */
    public static Command parseDeadlineCommand(String input) {
        String[] parts = input.length() > 9 ? input.substring(9).split(" /by ") : new String[0];
        assert parts.length == 2 : "Deadline command should have a description and a due date";
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            return new InvalidCommand("The description of a deadline cannot be empty.");
        }
        if (!parts[1].matches("\\d{4}-\\d{2}-\\d{2}")) {
            return new InvalidCommand("Invalid date format. Please use yyyy-MM-dd.");
        }
        try {
            LocalDate by = LocalDate.parse(parts[1]);
            Deadline deadline = new Deadline(parts[0], by);
            return new DeadlineCommand(deadline);
        } catch (DateTimeException e) {
            return new InvalidCommand("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
    /**
     * Parses input to produce an event command or invalid command.
     *
     * @param input A String typed by the user.
     * @return An event command.
     */
    public static Command parseEventCommand(String input) {
        String[] parts = input.length() > 6 ? input.substring(6).split(" /from | /to ") : new String[0];
        assert parts.length == 3 : "Event command should have a description, start, and end time";
        if (parts.length < 3 || parts[0].trim().isEmpty()) {
            return new InvalidCommand("The description of an event cannot be empty, "
                    + "and it must include '/from' and '/to' parts.");
        }
        if (!parts[1].matches("\\d{4}-\\d{2}-\\d{2}") || !parts[2].matches("\\d{4}-\\d{2}-\\d{2}")) {
            return new InvalidCommand("Invalid date format. Please use yyyy-MM-dd.");
        }
        try {
            LocalDate from = LocalDate.parse(parts[1].trim());
            LocalDate to = LocalDate.parse(parts[2].trim());
            if (from.isAfter(to)) {
                return new InvalidCommand("Start date is after end date, doesnt make much sense.");
            }
            Event event = new Event(parts[0].trim(), from, to);
            return new EventCommand(event);
        }
        catch (DateTimeException e) {
            return new InvalidCommand("Invalid date format. Please use yyyy-MM-dd.");
        }
    }
    /**
     * Parses input to produce a mark command.
     *
     * @param input A String typed by the user.
     * @return A mark command.
     */
    public static Command parseMarkCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        return new MarkCommand(index);
    }
    /**
     * Parses input to produce an unmark command.
     *
     * @param input A String typed by the user.
     * @return An unmark command.
     */
    public static Command parseUnmarkCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        return new UnmarkCommand(index);
    }
    /**
     * Parses input to produce a delete command.
     *
     * @param input A String typed by the user.
     * @return A delete command.
     */
    public static Command parseDeleteCommand(String input) {
        int index = Integer.parseInt(input.split(" ")[1]);
        return new DeleteCommand(index);
    }
    /**
     * Parses input to produce a find command or invalid command.
     *
     * @param input A String typed by the user.
     * @return A find command or invalid command.
     */
    public static Command parseFindCommand(String input) {
        String keyword = input.length() > 5 ? input.substring(5).trim() : "";
        if (keyword.isEmpty()) {
            return new InvalidCommand("Dk what to search for, try again.");
        }
        return new FindCommand(keyword);
    }
}
