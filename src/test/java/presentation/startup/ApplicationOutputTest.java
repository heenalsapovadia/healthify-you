package presentation.startup;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


class ApplicationOutputTest {

	@Test
	void testGetInstance() {
		ApplicationOutput applicationOutput = ApplicationOutput.getInstance();
		assertTrue(applicationOutput instanceof ApplicationOutput);
	}
}
