import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkeinweiserIFTest {
	
	ParkeinweiserIF pew;
	
	@BeforeEach
	void setUp() {
		pew = new Parkeinweiser(15);
		
	}

	@Test
	@DisplayName("Teste Parkplatzabfrage")
	void testGetParkplatz() {
		assertEquals(11, pew.getParkplatz("any"));
		assertEquals(12, pew.getParkplatz("any"));
		pew.returnParkplatz(11);
		assertEquals(13, pew.getParkplatz("any"));
		assertEquals(14, pew.getParkplatz("any"));
		assertEquals(15, pew.getParkplatz("any"));
		assertEquals(11, pew.getParkplatz("any"));
	}
	
	@Test
	@DisplayName("Vergrößere Parkplatzgröße")
	void test_changeSize_bigger() {
		pew.changeSize(20);
		
		for(int i = 11; i < 21; i++) {
			assertEquals(i, pew.getParkplatz("any"));
		}
	}
	
	@Test
	@DisplayName("Verkleinere Parkplatzgröße")
	void test_changeSize_smaller() {
		
		pew.changeSize(13);
	
		
		for(int i = 11; i < 14; i++) {
			assertEquals(i, pew.getParkplatz("any"));
		}
		assertEquals(-1, pew.getParkplatz("any"));
	}
	
	@Test
	@DisplayName("Teste spezieller Parkplätze")
	void test_changeSize_change() {

		assertEquals(1, pew.getParkplatz("Frau"));
		assertEquals(2, pew.getParkplatz("Familie"));
		assertEquals(11, pew.getParkplatz("any"));
		assertEquals(3, pew.getParkplatz("Familie"));
	}
	
	@Test
	@DisplayName("Kein spezieller Parkplatz")
	void test_no_special_parkinglot() {
		for(int i = 0; i < 10; i++) {
			pew.getParkplatz("Frau");
		}
		assertEquals(11, pew.getParkplatz("Frau"));
		
	}
	
	@Test
	@DisplayName("Parkhaus voll")
	void test_no_parkinglots() {
		for(int i = 0; i < 10; i++) {
			pew.getParkplatz("Frau");
		}
		for(int i = 0; i < 10; i++) {
			pew.getParkplatz("any");
		}
		assertEquals(-1, pew.getParkplatz("Familie"));
	}

}
