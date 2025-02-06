public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, HardDisk hardDisk);
    public boolean isExit() {
        return false;
    }
}
