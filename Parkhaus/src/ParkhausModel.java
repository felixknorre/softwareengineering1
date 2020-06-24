import java.util.ArrayList;
import java.util.List;

public class ParkhausModel implements ParkhausModelIF{
	
	List<ObserverIF> views; 
	Parkhaus ph;
	
	public ParkhausModel() {
		views = new ArrayList<ObserverIF>();
		ph = new Parkhaus();
	}
	
	@Override
	public void registerObserver(ObserverIF o) {
		views.add(o); // add new view
		
	}
	@Override
	public void removeObserver(ObserverIF o) {
		if(views.contains(o)) { // if view exists
			views.remove(o); // remove view
		}
		
	}
	@Override
	public void notifyObserver() {
		for(ObserverIF o: views) {
			o.update(); // update all views
		}
		
	}
	@Override
	public void addAuto(Auto a) {
		ph.addAuto(a);
		notifyObserver();
		
	}
	@Override
	public void removeAuto(Auto car, String dur, String price) { // remove auto and update duration, price in the history
		ph.removeAuto(car, dur, price);
		notifyObserver();
		
	}
	@Override
	public List<Auto> getParkhausHistory() {
		return ph.history;
	}
	@Override
	public int getBelegung() {
		return ph.checkBelegung();
	}




}
