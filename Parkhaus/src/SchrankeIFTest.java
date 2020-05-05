import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SchrankeIFTest {
	
	SchrankeIF s;
	
	@BeforeEach
	void setUp() {
		s = new Schranke();
	}
	

	@Test
	@DisplayName("Öffne Schranke")
	void testOpen() {
		s.open();
		assertEquals("open", s.getStatus());
	}
	
	@Test
	@DisplayName("Schließe Schranke")
	void testClose() {
		s.close();
		assertEquals("close", s.getStatus());
	}
	

}
