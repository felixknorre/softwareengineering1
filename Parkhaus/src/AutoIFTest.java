import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoIFTest {
	
	AutoIF auto;
	
	@BeforeEach
	void setUp() {
		auto = new Auto("blue");
	}

	@Test
	@DisplayName("Setze die Farbe des Autos")
	void testSetColor() {
		auto.setColor("grey");
		assertEquals("grey", auto.getColor());
	}

	@Test
	@DisplayName("Gebe die Farbe des Autos zurück")
	void testGetColor() {
		assertEquals("blue", auto.getColor());
	}

}
