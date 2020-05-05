import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KasseIFTest {
	
	KasseIF k;
	ParkticketIF p;
	
	@BeforeEach
	void setUp() {
		k = new Kasse();
		p = new Parkticket(2);
	}

	@Test
	@DisplayName("Check-Out")
	void testEvaluTicket() {
		p = k.evaluTicket(p);
		
		assertEquals(5, p.getCheckOutTime());
		assertEquals(3.6, p.getPrice(),  0.001);
	}

}
