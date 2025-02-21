package johnny.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
/**
 * Contains a list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of TaskList.
     *
     * @param tasks A list of tasks to be contained in TaskList.
     */
    public TaskList(List<Task> tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>();
        } else {
            this.tasks = tasks;
        }
    }

    public Integer size() {
        return tasks.size();
    }
    public Task get(Integer i) {
        return tasks.get(i);
    }
    public void addTask(Task task) {
        this.tasks.add(task);
    }
    public List<Task> getTasks() {
        return this.tasks;
    }
    public Task remove(int index) {
        return this.tasks.remove(index);
    }
    public Stream<Task> stream() {
        return tasks.stream();
    }
}

