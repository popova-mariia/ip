package johnny.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the UnknownCommandException.
 */
public class UnknownCommandExceptionTest {

    @Test
    public void testExceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> {
            throw new UnknownCommandException("Unknown command entered!");
        });
    }
}
