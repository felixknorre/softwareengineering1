import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkhausControllerIFTest {
	
	AutoIF a1, a2;
	ParkhausIF model;
	ParkhausControllerIF pc;
	ObserverIF view1;
	
	@BeforeEach
	void setup() {
		a1 = new Auto("1", "2323", "_", "_", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "_", "_", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		
		model = new Parkhaus();
		 
		view1 = new ManagerView(model);
		
		List<ObserverIF> l = new ArrayList<ObserverIF>();
		
		l.add(view1);
		
		pc = new ParkhausController(model, l);
		
		
	}


	@Test
	void test_ManagerView() {
		// add Autos
		pc.addAuto(a1);
		pc.addAuto(a2);
		
		assertEquals("0", view1.view());
		
		AutoIF newa1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		AutoIF newa2 = new Auto("2", "345445", "200", "200", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		
		// remove Autos
		pc.removeAuto(newa1);
		pc.removeAuto(newa2);
		
		assertEquals("3", view1.view());
		
	}



}
