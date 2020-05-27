import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoIFTest {
	
	AutoIF auto;
	
	@BeforeEach
	void setUp() {
		auto = new Auto(1, 1223234234L, "IchbineinHash", "#h3hdhu", "", "");
	}

	@Test
	@DisplayName("toString Methode fuer um das Auto auszugeben")
	void testToString_print_attr() {
		assertEquals("1/1223234234/_/_/IchbineinHash/#h3hdhu", auto.toString());
	}
}
