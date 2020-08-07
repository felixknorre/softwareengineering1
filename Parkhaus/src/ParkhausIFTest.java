import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkhausIFTest {
	
	ParkhausIF ph;
	
	AutoIF a1, a2;
	
	@BeforeEach
	void setUp() {
		ph = new Parkhaus();
		
		a1 = new Auto("1", "2323", "_", "_", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "_", "_", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		

	}

	@Test
	@DisplayName("Auto ins Parkhaus hinzufügen")
	void testAddAuto() {
		assertEquals(0, ph.getParkhaus().size());
		ph.addAuto(a1);
		assertEquals(1, ph.getParkhaus().size());
		ph.addAuto(a2);
		assertEquals(2, ph.getParkhaus().size());
	}
	
	@Test
	@DisplayName("Auto ins Parkhaus verlassen")
	void testRemoveAuto() {
		
		
	}
	
	@Test
	@DisplayName("Sum")
	void testGetSum() {
		ph.addAuto(a1);
		ph.addAuto(a2);
		a1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		ph.removeAuto(a1);
		assertEquals("100", ph.getSum());
		a2 = new Auto("2", "345445", "_", "_", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		ph.removeAuto(a2);
		assertEquals("300", ph.getSum());
		
	}
	
	@Test
	@DisplayName("AVG")
	void testGetAVG() {
		ph.addAuto(a1);
		ph.addAuto(a2);
		a1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "200", "200", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		ph.removeAuto(a1);
		ph.removeAuto(a2);
		assertEquals("150", ph.getAVG());
	}
	
	@Test
	@DisplayName("Min/Max-Parkhaus-Price")
	void testMinMax() {
		ph.addAuto(a1);
		ph.addAuto(a2);
		a1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "200", "200", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		ph.removeAuto(a1);
		ph.removeAuto(a2);
		assertEquals("150", ph.getMinMax());
	}
	
	@Test
	@DisplayName("Parkhaus ausgeben")
	void testToString() {
		
	}
	
	


}
