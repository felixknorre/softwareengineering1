import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmpelIFTest {
	
	AmpelIF a;
	
	@BeforeEach
	void setUp() {
		a = new Ampel();
	}
	
	@Test
	@DisplayName("Gibt die Frabe der Ampel zurück")
	void testGetColor_get_red() {
		assertEquals("red", a.getColor());
	}

	@Test
	@DisplayName("Setze Farbe auf Grün")
	void testSetColor_setColor_green() {
		a.setColor("green");
		assertEquals("green",a.getColor());
	}
	
	@Test
	@DisplayName("Setze Farbe auf Rot")
	void testSetColor_setColor_red() {
		a.setColor("red");
		assertEquals("red",a.getColor());
	}
	
	@Test
	@DisplayName("Setze Farbe auf Blau")
	void testSetColor_setColor_blue() {
		a.setColor("blue");
		assertEquals("red",a.getColor());
	}

}
