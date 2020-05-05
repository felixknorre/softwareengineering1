import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkhausIFTest {
	
	ParkhausIF ph;
	Auto a1, a2, a3, a4;
	
	@BeforeEach
	void setUp() {
		ph = new Parkhaus(5);
		a1 = new Auto();
		a2 = new Auto();
		a3 = new Auto();
		a4 = new Auto();
	}
	

	@Test
	void testAddAuto() {
		ph.addAuto(a1);
		assertEquals(1, ph.checkBelegung());
	}

	@Test
	void testRemoveAuto() {
		ph.removeAuto(2);
		assertEquals(0, ph.checkBelegung());
	}

	@Test
	@DisplayName("Überprüfe die Parkhausbelegung")
	void testCheckBelegung() {
		assertEquals(0, ph.checkBelegung());
		ph.addAuto(a1);
		assertEquals(1, ph.checkBelegung());
	}

}
