/**
 * The main class for the MariiaChatbot program.
 * 
 * To use my bot, run the program and use the following supported programs:
 * - `bye`: Exits the program and saves tasks to the hard disk.
 * - `list`: lists all current tasks.
 * - `todo <description>`: Adds a ToDo task.
 * - `deadline <description> /by <date>`: Adds a Deadline task.
 * - `event <description> /from <start> /to <end>`: Adds an Event task.
 * - `mark <index>`: Marks task as done. Index must be within boundaries.
 * - `unmark <index>`: Marks task as not done.
 * - `delete <index>`: Deletes the task at specified index.
 */
public class MariiaChatbot {
    private HardDisk hardDisk;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public MariiaChatbot(String filePath) {
        this.hardDisk = new HardDisk(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.tasks = new TaskList(hardDisk.loadTasks());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayWelcomeMessage();
        boolean isExit = false;
        while (!isExit && ui.hasNextLine()) {
            try {
                String input = ui.readNextLine();
                Command command = parser.parseCommand(input);
                command.execute(tasks, ui, hardDisk);
                isExit = command.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
        }
    }
    /**
     * Marks the entry point for the MariiaChatbot program.
     * Initializes the task list, loads tasks from disk, and interacts with the user via the console.
     * Executes bye, list, todo, deadline, event, mark, unmark and delete commands read from console.
     * Saves tasks to disk when the user quits the program.
     *
     * @param args Command-line arguments (not used).
     */
     public static void main(String[] args) {
        new MariiaChatbot("./data/mariia.txt").run();
    }
}
       