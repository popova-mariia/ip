public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        ui.showGoodbyeMessage();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
