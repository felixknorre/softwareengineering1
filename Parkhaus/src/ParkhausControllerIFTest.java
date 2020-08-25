import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkhausControllerIFTest {
	
	AutoIF a1, a2;
	ParkhausIF model;
	ParkhausControllerIF pc;
	ObserverIF view1, view2, view3, view4;
	
	@BeforeEach
	void setup() {
		a1 = new Auto("1", "2323", "_", "_", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "_", "_", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		
		model = new Parkhaus();
		 
		view1 = new ParkhausView(model);
		view2 = new SummenView(model);
		view3 = new AVGView(model);
		view4 = new MinMaxView(model);
		
		List<ObserverIF> l = new ArrayList<ObserverIF>();
		
		l.add(view1);
		l.add(view2);
		l.add(view3);
		l.add(view4);
		
		pc = new ParkhausController(model, l);
		
		
	}


	@Test
	@DisplayName("Test Parkhaus View")
	void test_ParkhausView() {
		// add Autos
		pc.addAuto(a1);
		pc.addAuto(a2);
		
		String res = "1/2323/_/_/dfghidfgh/#color1/2/Frau/1,2/345445/_/_/dfgdgdssdgdghjkkl/#color2/3/any/2";
		
		assertEquals(res, view1.view());

	}
	
	@Test
	@DisplayName("Test Summen View")
	void test_SumView() {
		// add Autos
		pc.addAuto(a1);
		pc.addAuto(a2);
		
		a1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		pc.removeAuto(a1);
		assertEquals("1.00 Euro", view2.view());
		
		a2 = new Auto("2", "345445", "200", "200", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		pc.removeAuto(a2);
		assertEquals("3.00 Euro", view2.view());

	}
	
	@Test
	@DisplayName("Test AVG View")
	void test_AVGView() {
		// add Autos
		pc.addAuto(a1);
		pc.addAuto(a2);
		
		a1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		pc.removeAuto(a1);
		assertEquals("1.00 Euro", view3.view());
		
		a2 = new Auto("2", "345445", "200", "200", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		pc.removeAuto(a2);
		assertEquals("1.50 Euro", view3.view());

	}
	
	@Test
	@DisplayName("Test Min/Max View")
	void test_Min_Max_View() {
		// add Autos
		pc.addAuto(a1);
		pc.addAuto(a2);

		a1 = new Auto("1", "2323", "100", "100", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "200", "200", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		
		pc.removeAuto(a1);
		pc.removeAuto(a2);
		assertEquals("Min: 1.00, Max: 2.00", view4.view());
	
	}




}
