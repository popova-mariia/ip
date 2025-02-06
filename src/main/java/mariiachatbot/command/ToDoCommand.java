package mariiachatbot.command;

import mariiachatbot.task.TaskList;
import mariiachatbot.storage.HardDisk;
import mariiachatbot.ui.Ui;
import mariiachatbot.task.ToDo;

public class ToDoCommand extends Command {
    private ToDo todo;

    public ToDoCommand(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.todo);
        hardDisk.saveTasks(tasks.getTasks());
        ui.showAddTask(this.todo, tasks.size());
    }
}