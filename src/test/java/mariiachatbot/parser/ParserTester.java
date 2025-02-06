package mariiachatbot.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import mariiachatbot.command.ByeCommand;
import mariiachatbot.command.Command;
import mariiachatbot.command.DeadlineCommand;
import mariiachatbot.command.DeleteCommand;
import mariiachatbot.command.EventCommand;
import mariiachatbot.command.InvalidCommand;
import mariiachatbot.command.ListCommand;
import mariiachatbot.command.MarkCommand;
import mariiachatbot.command.ToDoCommand;
import mariiachatbot.command.UnmarkCommand;

/**
 * Tests the Parser class.
 */
public class ParserTester {
    private final Parser parser = new Parser();
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_byeCommand_success() {
        Command command = parser.parseCommand("bye");
        assertTrue(command instanceof ByeCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_listCommand_success() {
        Command command = parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_todoCommand_success() {
        Command command = parser.parseCommand("todo Read book");
        assertTrue(command instanceof ToDoCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_deadlineCommand_success() {
        Command command = parser.parseCommand("deadline Assignment /by 2025-02-10");
        assertTrue(command instanceof DeadlineCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_eventCommand_success() {
        Command command = parser.parseCommand("event Party /from 2025-02-15 /to 2025-02-16");
        assertTrue(command instanceof EventCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_markCommand_success() {
        Command command = parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_unmarkCommand_success() {
        Command command = parser.parseCommand("unmark 1");
        assertTrue(command instanceof UnmarkCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_deleteCommand_success() {
        Command command = parser.parseCommand("delete 1");
        assertTrue(command instanceof DeleteCommand);
    }
    /**
     * Testing parser.
     */
    @Test
    public void parseCommand_invalidCommand_failure() {
        Command command = parser.parseCommand("random gibberish");
        assertTrue(command instanceof InvalidCommand);
    }
}
