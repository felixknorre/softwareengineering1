import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoIFTest {
	
	AutoIF auto;
	
	@BeforeEach
	void setUp() {
		auto = new Auto("1", "100", "12223", "340", "sfhgdkgkdg", "#color", "5", "frau");
	}

	@Test
	@DisplayName("toString Methode fuer um das Auto auszugeben")
	void testToString_print_attr() {
		assertEquals("1/100/12223/340/sfhgdkgkdg/#color/5/frau/1", auto.toString());
	}
	
	@Test
	@DisplayName("Teste set-Methoden für ein Auro")
	void testSet() {
		auto.setNr("2");
		auto.setTimer("200");
		auto.setDuration("12");
		auto.setPrice("440");
		auto.setHash("dfdghfgh");
		auto.setColor("#colorcolor");
		auto.setParkinglot("4");
		auto.setCategory("any");
		
		assertEquals("2/200/12/440/dfdghfgh/#colorcolor/4/any/2", auto.toString());
	}
}
