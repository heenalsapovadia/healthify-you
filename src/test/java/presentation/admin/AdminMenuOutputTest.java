package presentation.admin;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AdminMenuOutputTest {

	@Test
	void testGetInstance() {
		AdminMenuOutput adminMenuOutput = AdminMenuOutput.getInstance();
		assertTrue(adminMenuOutput instanceof AdminMenuOutput);
	}
}
