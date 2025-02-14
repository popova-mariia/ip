package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.TaskList;
import mariiachatbot.task.ToDo;
import mariiachatbot.ui.Ui;
/**
 * The ToDo class represents the command to create a todo task.
 */
public class ToDoCommand extends Command {
    private ToDo todo;

    public ToDoCommand(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.todo);
        hardDisk.saveTasks(tasks.getTasks());
        return ui.showAddTask(this.todo, tasks.size());
    }
}
