import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkhausCallerIFTest {
	
	// caller
	ParkhausCallerIF pc;
	// command
	CommandIF addCom, addCom2, removeCom, removeCom2;
	
	
	// mvc
	ParkhausControllerIF phc;
	ParkhausIF ph;
	ObserverIF pView;
	
	// cars
	AutoIF a1, a2;
	
	
	String res1, res2;
	
	@BeforeEach
	void setUp() {
		// mvc setup
		ph = new Parkhaus();
		pView = new ParkhausView(ph);
		List<ObserverIF> l = new ArrayList<ObserverIF>();
		l.add(pView);
		phc = new ParkhausController(ph, l);
		
		// car setup
		a1 = new Auto("1", "2323", "_", "_", "dfghidfgh", "#color1", "2", "Frau");
		a2 = new Auto("2", "345445", "_", "_", "dfgdgdssdgdghjkkl", "#color2", "3", "any");
		
		
		// caller setup
		pc = new ParkhausCaller();
		
		addCom = new AddAutoCommand(phc, a1);
		addCom2 = new AddAutoCommand(phc, a2);
		removeCom = new RemoveAutoCommand(phc, a1);
		removeCom2 = new RemoveAutoCommand(phc, a2);
		
		res1 = "1/2323/_/_/dfghidfgh/#color1/2/Frau/1";
		res2 = "1/2323/_/_/dfghidfgh/#color1/2/Frau/1,2/345445/_/_/dfgdgdssdgdghjkkl/#color2/3/any/2";
			
	}
	

	@Test
	void testExecute() {
		addCom = new AddAutoCommand(phc, a1);
		pc.saveNewCommand(addCom);
		pc.execute();
		
		assertEquals(res1, pView.view());
		addCom2 = new AddAutoCommand(phc, a2);
		pc.saveNewCommand(addCom2);
		pc.execute();
		
		assertEquals(res2, pView.view());
	}

	@Test
	void test_Add_undo_redo() {
		
		pc.saveNewCommand(addCom);
		pc.execute();
		
		pc.saveNewCommand(addCom2);
		pc.execute();
		
		assertEquals(res2, pView.view());
		assertEquals(2, phc.getModel().getParkhaus().size());
		pc.undo();
		assertEquals(1, phc.getModel().getParkhaus().size());
		assertEquals(res1, pView.view());
		pc.redo();
		assertEquals(res2, pView.view());
	}

	@Test
	void testRemove() {
		pc.saveNewCommand(addCom);
		pc.execute();
		pc.saveNewCommand(addCom2);
		pc.execute();
		assertEquals(res2, pView.view());
		pc.saveNewCommand(removeCom2);
		pc.execute();
		assertEquals(res1, pView.view());
		assertEquals(1, phc.getModel().getHistory().size());
		pc.undo();
		assertEquals(res2, pView.view());
		
	}

}
