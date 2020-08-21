import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkeinweiserIFTest {
	
	ParkeinweiserIF pew;
	
	@BeforeEach
	void setUp() {
		pew = new Parkeinweiser(4);
		
	}

	@Test
	@DisplayName("Teste Parkplatzabfrage")
	void testGetParkplatz() {
		assertEquals(1, pew.getParkplatz());
		assertEquals(2, pew.getParkplatz());
		pew.returnParkplatz(2);
		assertEquals(3, pew.getParkplatz());
		assertEquals(4, pew.getParkplatz());
		assertEquals(2, pew.getParkplatz());
	}
	
	@Test
	@DisplayName("Vergrößere Parkplatzgröße")
	void test_changeSize_bigger() {
		pew.changeSize(6);
		
		for(int i = 1; i < 7; i++) {
			assertEquals(i, pew.getParkplatz());
		}
	}
	
	@Test
	@DisplayName("Verkleinere Parkplatzgröße")
	void test_changeSize_smaller() {
		pew.changeSize(2);
		
		assertEquals(2, pew.getSize());
		
		for(int i = 1; i < 3; i++) {
			assertEquals(i, pew.getParkplatz());
		}
	}
	
	@Test
	@DisplayName("Ändere Größe mehrfach Parkplatzgröße")
	void test_changeSize_change() {
		// no free parkinglots
		pew.getParkplatz();
		pew.getParkplatz();
		pew.getParkplatz();
		pew.getParkplatz();
		
		// change to a smaller size
		pew.changeSize(3);
		pew.returnParkplatz(4);
		assertEquals(-1, pew.getParkplatz());
		
		pew.changeSize(6);
		assertEquals(4, pew.getParkplatz());
		
		
		
	}

}
