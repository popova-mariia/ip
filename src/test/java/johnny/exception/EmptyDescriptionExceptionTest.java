package johnny.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the EmptyDescriptionException.
 */
public class EmptyDescriptionExceptionTest {

    @Test
    public void testExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> {
            throw new EmptyDescriptionException("Task description is required!");
        });
    }
}
