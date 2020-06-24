import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MVCTest {
	
	ParkhausModelIF ph_model;
	ObserverIF mitarbeiterV;
	ObserverIF managerV;
	ParkhausControllerIF ph_cont;
	
	@BeforeEach
	void setUp() {
		ph_model = new ParkhausModel();
		mitarbeiterV = new MitarbeiterView(ph_model);
		managerV = new MangagerView(ph_model);
		
		// set up controller
		List<ObserverIF> views = new ArrayList<ObserverIF>();
		views.add(mitarbeiterV);
		views.add(managerV);
		ph_cont = new ParkhausController(ph_model, views);
	}

	@Test
	@DisplayName("Teste Mitarbeiter View")
	void test_mitarbeiter_view() {
		
		//  create cars
		Auto a1 =  new Auto(1, 10000, "1fsdhfhjsf", "ghgghghd", "any", "1");
		Auto a2 =  new Auto(2, 10000, "2sdffgr", "dfsfsdf", "any", "2");
		Auto a3 =  new Auto(3, 10000, "3fghfhtjhj", "uikdefd", "any", "3");
		
		// add cars to model and test if the view was updated
		
		assertEquals("0", mitarbeiterV.view());
		
		ph_cont.addAuto(a1);
		
		assertEquals("1", mitarbeiterV.view());
		
		ph_cont.addAuto(a2);
		
		assertEquals("2", mitarbeiterV.view());
		
		ph_cont.addAuto(a3);
		
		assertEquals("3", mitarbeiterV.view());
		
	}
	
	@Test
	@DisplayName("Teste Manager View")
	void test_manager_view() {
	//  create cars
			Auto a1 =  new Auto(1, 10000, "1fsdhfhjsf", "ghgghghd", "any", "1");
			Auto a2 =  new Auto(2, 10000, "2sdffgr", "dfsfsdf", "any", "2");
			Auto a3 =  new Auto(3, 10000, "3fghfhtjhj", "uikdefd", "any", "3");
			
			// add all cars to the model
			ph_cont.addAuto(a1);
			ph_cont.addAuto(a2);
			ph_cont.addAuto(a3);
			
			// umsatz is still 0.0
			assertEquals("Umsatz: 0", managerV.view());
			
			// remove the first car, price 100 cent
			ph_cont.removeAuto(a1,"100", "100");
			
			assertEquals("Umsatz: 100", managerV.view());
			
			// remove the other cars
			ph_cont.removeAuto(a2,"100", "200");
			ph_cont.removeAuto(a3,"100", "300");
			
			assertEquals("Umsatz: 600", managerV.view());
		
	}

}
