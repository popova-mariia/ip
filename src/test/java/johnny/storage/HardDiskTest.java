package johnny.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import johnny.task.Task;

/**
 * Tests the HardDisk class.
 * Added header for a commit.
 */
public class HardDiskTest {
    private static final String TEST_FILE_PATH = "./test_johnny.txt";
    private HardDisk hardDisk;

    @BeforeEach
    public void setUp() throws IOException {
        File testFile = new File(TEST_FILE_PATH);
        testFile.createNewFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("todo Read book /done X\n");
            writer.write("deadline Submit report /by 2025-02-15 /done N\n");
            writer.write("event Party /from 2025-03-01 /to 2025-03-02 /done X\n");
        }
        hardDisk = new HardDisk(TEST_FILE_PATH);
    }

    @AfterEach
    public void tearDown() {
        // Delete the test file after each test
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    @Test
    public void loadTasks_validFile_success() {
        List<Task> tasks = hardDisk.loadTasks();
        assertEquals(3, tasks.size(), "Expected 3 tasks to be loaded.");
    }

    @Test
    public void loadTasks_emptyFile_returnsEmptyList() throws IOException {
        // Overwrite file with nothing
        try (FileWriter writer = new FileWriter(TEST_FILE_PATH)) {
            writer.write("");
        }
        List<Task> tasks = hardDisk.loadTasks();
        assertTrue(tasks.isEmpty(), "Expected an empty list when file is empty.");
    }
    @Test
    public void loadTasks_noFile_returnsEmptyList() {
        File nonExistentFile = new File("./non_existent_file.txt");
        HardDisk newHardDisk = new HardDisk(nonExistentFile.getPath());
        List<Task> tasks = newHardDisk.loadTasks();
        assertTrue(tasks.isEmpty(), "Expected an empty list when file does not exist.");
    }
}
