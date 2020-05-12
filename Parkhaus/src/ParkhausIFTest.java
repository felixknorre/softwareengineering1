import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkhausIFTest {
	
	ParkhausIF ph;
	Auto a1, a2, a3, a4;
	
	@BeforeEach
	void setUp() {
		ph = new Parkhaus(10);
		a1 = new Auto(1, 1223234234L, "IchbineinHash", "#fcb956", 3);
		a2 = new Auto(2, 1223234234L, "IchbineinHash", "#fcb956", 2);
		a3 = new Auto(3, 1223234234L, "IchbineinHash", "#fcb956", 5);
		a4 = new Auto(4, 1223234234L, "IchbineinHash", "#fcb956", 8);
	}
	

	@Test
	void testAddAuto() {
		ph.addAuto(a1);
		Auto[] autos = ph.getParkhaus();
		assertSame(a1, autos[a1.parkinglot - 1]);
	}

	@Test
	void testRemoveAuto() {
		ph.addAuto(a2);
		Auto[] autos = ph.getParkhaus();
		assertSame(a2, autos[a2.parkinglot - 1]);
		ph.removeAuto(2);
		assertNull(autos[a2.parkinglot - 1]);
	}

	@Test
	@DisplayName("Überprüfe die Parkhausbelegung")
	void testCheckBelegung() {
		assertEquals(0, ph.checkBelegung());
		ph.addAuto(a1);
		assertEquals(1, ph.checkBelegung());
		ph.addAuto(a2);
		ph.addAuto(a3);
		ph.addAuto(a4);
		assertEquals(4, ph.checkBelegung());
	}
	
	@Test
	@DisplayName("Printe gesamtes Parkhaus")
	void testToString_print_parkhaus() {
		ph.addAuto(a1);
		ph.addAuto(a2);
		//1, 1223234234L, "IchbineinHash", "#fcb956", 3
		//2, 1223234234L, "IchbineinHash", "#fcb956", 2
		assertEquals("2/1223234234/_/_/IchbineinHash/#fcb956/2,1/1223234234/_/_/IchbineinHash/#fcb956/3", ph.toString());

	}

}
