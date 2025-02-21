package johnny.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import johnny.task.Task;
import johnny.task.TaskList;
import johnny.task.ToDo;

/**
 * Tests the Ui class.
 */
public class UiTest {
    private Ui ui;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        taskList = new TaskList();
    }

    @Test
    public void testDisplayWelcomeMessage() {
        String expected = "You're talking to Johnny now.\n"
                + "Not here to waste time—just tell me what you need.\n";
        assertEquals(expected, ui.displayWelcomeMessage());
    }

    @Test
    public void testShowGoodbyeMessage() {
        assertEquals("Alright, I'm out. Try not to mess things up while I'm gone.", ui.showGoodbyeMessage());
    }

    @Test
    public void testShowTaskList_emptyList() {
        String expected = "Here’s what you've got:\nNothing. Either you're done, or you've done nothing.\n";
        assertEquals(expected, ui.showTaskList(taskList));
    }

    @Test
    public void testShowTaskList_withTasks() {
        taskList.addTask(new ToDo("read book"));
        taskList.addTask(new ToDo("submit cs2107 quiz"));

        String expected = "Here’s what you've got:\n"
                + "1.[T][ ] read book\n"
                + "2.[T][ ] submit cs2107 quiz\n";

        assertEquals(expected, ui.showTaskList(taskList));
    }

    @Test
    public void testShowAddTask() {
        Task task = new ToDo("read book");
        String expected = "Another task? You sure you can handle this?\n"
                + "Added: [T][ ] read book\n That makes 1 tasks. Better get moving.\n";
        assertEquals(expected, ui.showAddTask(task, 1));
    }

    @Test
    public void testShowMarkTask() {
        Task task = new ToDo("read book");
        task.markAsDone();
        String expected = "Finally, some progress. Task completed:\n   [T][X] read book\n";
        assertEquals(expected, ui.showMarkTask(task));
    }

    @Test
    public void testShowUnmarkTask() {
        Task task = new ToDo("read book");
        task.markAsNotDone();
        String expected = " Changed your mind? Alright.\n Task is back to incomplete:\n   [T][ ] read book\n";
        assertEquals(expected, ui.showUnmarkTask(task));
    }

    @Test
    public void testShowDeleteTask() {
        Task task = new ToDo("read book");
        String expected = " Task scrapped:\n   [T][ ] read book\n You're down to 0 tasks in the list.\n";
        assertEquals(expected, ui.showDeleteTask(task, 0));
    }

    @Test
    public void testShowFindResults_noMatches() {
        List<Task> emptyList = new ArrayList<>();
        String expected = "Here’s what I found:\n No matches. Guess you're searching for ghosts.\n";
        assertEquals(expected, ui.showFindResults(emptyList));
    }

    @Test
    public void testShowFindResults_withMatches() {
        List<Task> foundTasks = new ArrayList<>();
        foundTasks.add(new ToDo("read book"));
        foundTasks.add(new ToDo("submit cs2107 quiz"));

        String expected = "Here’s what I found:\n"
                + "1.[T][ ] read book\n"
                + "2.[T][ ] submit cs2107 quiz\n";
        assertEquals(expected, ui.showFindResults(foundTasks));
    }

    @Test
    public void testShowUndoneTask() {
        Task task = new ToDo("read book");
        String expected = "Backpedaling already? Fine, I'll undo it.\nTask: read book[T][ ] read book\n";
        assertEquals(expected, ui.showUndoneTask(task));
    }

    @Test
    public void testShowUndoMarkTask() {
        Task task = new ToDo("read book");
        String expected = "Undid: Marked task as not done.\nread book";
        assertEquals(expected, ui.showUndoMarkTask(task));
    }

    @Test
    public void testShowCannotUndoTask() {
        assertEquals("Cannot undo this action.", ui.showCannotUndoTask());
    }

    @Test
    public void testShowError() {
        assertEquals("Something went wrong.\n", ui.showError("Something went wrong."));
    }

    @Test
    public void testShowLoadingError() {
        String expected = "***\n There was an error loading your tasks.\n Starting with an empty task list.\n***\n";
        assertEquals(expected, ui.showLoadingError());
    }
}
