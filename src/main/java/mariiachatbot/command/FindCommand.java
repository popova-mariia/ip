package mariiachatbot.command;

import java.util.List;
import java.util.stream.Collectors;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.Task;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;

/**
 * Represents a command to find tasks with a given keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());
        return ui.showFindResults(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
