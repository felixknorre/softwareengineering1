import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkhausIFTest {
	
	ParkhausIF ph;
	
	Auto a1, a2;
	
	@BeforeEach
	void setUp() {
		ph = new Parkhaus();
		
		a1 = new Auto(1, 123456L, "Hajsjdhdgf", "#4287f5", "any", "1");
		a2 = new Auto(2, 32456788, "lldkdtHHGfd", "#e0f542", "any", "2");
	}

	@Test
	@DisplayName("Auto ins Parkhaus hinzufügen")
	void testAddAuto() {
		assertEquals(0, ph.checkBelegung());
		ph.addAuto(a1);
		assertEquals(1, ph.checkBelegung());
		assertEquals("1/123456/_/_/Hajsjdhdgf/#4287f5/1/any/1", ph.toString());
		
	}

	@Test
	@DisplayName("Entferne Auto aus dem Parkhaus")
	void testRemoveAuto() {
		ph.addAuto(a1);
		ph.addAuto(a2);
		assertEquals("1/123456/_/_/Hajsjdhdgf/#4287f5/1/any/1,2/32456788/_/_/lldkdtHHGfd/#e0f542/2/any/2", ph.toString());
		ph.removeAuto(a1, "2", "3");
		assertEquals("2/32456788/_/_/lldkdtHHGfd/#e0f542/2/any/2", ph.toString());
	}

	@Test
	@DisplayName("Gibt die Anzahl der Autos im Parkhaus zurück")
	void testCheckBelegung() {
		assertEquals(0, ph.checkBelegung());
		ph.addAuto(a1);
		assertEquals(1, ph.checkBelegung());
		ph.addAuto(a2);
		assertEquals(2, ph.checkBelegung());
	}


	@Test
	void testToString() {
		assertEquals("", ph.toString());
		ph.addAuto(a1);
		assertEquals("1/123456/_/_/Hajsjdhdgf/#4287f5/1/any/1", ph.toString());
		ph.addAuto(a2);
		assertEquals("1/123456/_/_/Hajsjdhdgf/#4287f5/1/any/1,2/32456788/_/_/lldkdtHHGfd/#e0f542/2/any/2", ph.toString());
	}
	
	@Test
	@DisplayName("Checkout set")
	void testSetCeckoutData() {
		ph.addAuto(a1);
		assertEquals("1/123456/_/_/Hajsjdhdgf/#4287f5/1/any/1", ph.toString());
		ph.removeAuto(a1, "2", "3");
		Auto testAuto  = ph.getHistory().get(0);
		assertEquals("2", testAuto.duration);
		assertEquals("3", testAuto.price);
		assertEquals("1/123456/3/2/Hajsjdhdgf/#4287f5/1/any/1", testAuto.toString());
		
	}

}
