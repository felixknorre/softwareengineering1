import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkhausConfigIFTest {
	
	ParkhausConfigIF pc;
	
	@BeforeEach
	void setUp() {
		pc = new ParkhausConfig("10", "6", "24", "100", "10");
		
	}

	@Test
	@DisplayName("Teste toString Methode")
	void testToString() {
		assertEquals("10,6,24,100,10", pc.toString());
	}
	
	@Test
	@DisplayName("Teste set-Methoden")
	void test_set_methodes() {
		
		pc.setMaxAutos("11");
		pc.setOpen("7");
		pc.setClose("22");
		pc.setVerzögerung("200");
		pc.setPreisFaktor("30");
		
		assertEquals("11,7,22,200,30", pc.toString());
	}

}
