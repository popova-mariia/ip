package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.TaskList;
import johnny.task.ToDo;
import johnny.ui.Ui;
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
        hardDisk.logLatestChange("todo " + this.todo.getDescription());
        return ui.showAddTask(this.todo, tasks.size());
    }
}
