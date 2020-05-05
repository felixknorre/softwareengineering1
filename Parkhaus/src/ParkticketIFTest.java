import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkticketIFTest {
	
	ParkticketIF p;
	
	@BeforeEach
	void setUp() {
		p = new Parkticket(12234454);
	}

	@Test
	@DisplayName("Gebe die Check-In Zeit zurück")
	void testGetCheckInTime() {
		assertEquals(12234454, p.getCheckInTime());
	}

	@Test
	void testGetCheckOutTime() {
		p.setCheckOutTime(757567665);
		assertEquals(757567665, p.getCheckOutTime());
	}

	@Test
	void testSetCheckOutTime() {
		p.setCheckOutTime(4534534);
		assertEquals(4534534, p.getCheckOutTime());
	}
	
	@Test
	void testSetPrice_9_99() {
		p.setPrice(9.99);
		assertEquals(9.99, p.getPrice());
	}
	
	@Test
	void testGetPrice_0() {
		assertEquals(0, p.getPrice());
	}
	
	

}
