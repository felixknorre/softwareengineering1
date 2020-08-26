
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parkhaus implements ParkhausIF, Iterable<AutoIF>{
	
	private List<AutoIF> parkplatz;
	private List<AutoIF> history;
	
	private List<ObserverIF> obs;
	
	private int sum;
	private int count;
	private int min, max;
	
    
	
	public Parkhaus() {
		this.parkplatz = new ArrayList<AutoIF>();
		this.history = new ArrayList<AutoIF>();
		this.obs = new ArrayList<ObserverIF>();
		this.count = 0;
		this.sum = 0;
		this.max = 0;
		this.min = 0;
	}

	@Override
	public Iterator<AutoIF> iterator() {
		return new ParkhausIterator(this.parkplatz);
	}



	@Override
	public void addAuto(AutoIF newCar) {
		this.parkplatz.add(newCar);
		// notify all views
		notifyObserver();
	}



	@Override
	public void removeAuto(AutoIF oldcar) {
		String number = oldcar.getNr();
		
		for(int i = 0; i < this.parkplatz.size(); i++ ) {
			AutoIF currAuto = this.parkplatz.get(i);
			if(currAuto.getNr().equals(number)) {
				// remove Auto aus Parkhaus
				this.parkplatz.remove(currAuto);
				// adde Auto to history
				this.history.add(oldcar);
				// set max value
				if(Integer.parseInt(oldcar.getPrice()) > this.max) {
					this.max = Integer.parseInt(oldcar.getPrice());
				}
				// set min value
				if( Integer.parseInt(oldcar.getPrice()) < this.min) {
					this.min = Integer.parseInt(oldcar.getPrice());
				} else if(this.min == 0) {
					this.min = Integer.parseInt(oldcar.getPrice());
				}
				
			}
			
		}
		
		// notify all views
		notifyObserver();
		
		
	}



	@Override
	public List<AutoIF> getParkhaus() {
		return this.parkplatz;
	}
	
	@Override
	public List<AutoIF> getHistory() {
		return this.history;
	}



	@Override
	public Double getSum() {
		if(this.history.isEmpty()) {
			return Double.parseDouble("0");
		} else {
			return Double.parseDouble("" + this.history.stream().map(i -> Integer.parseInt(i.getPrice())).reduce(0, (x,y) -> x + y)) / 100;
		}
	}



	@Override
	public Double getAVG() {
		if(this.history.isEmpty()) {
			return 0.00;
		} else {
			return Double.parseDouble("" + this.getSum()) / (this.history.size());
		}
	}



	@Override
	public Double getMin() {
		return this.min / 100.0;
	}
	@Override
	public Double getMax() {
		return this.max / 100.0;
	}
	
	public String toString() {
		String str = "";
		
		Iterator<AutoIF> iter = this.iterator();
				
		while(iter.hasNext()) {
			AutoIF tmp = iter.next();
			
			if(iter.hasNext()) {
				str += tmp.toString() + ",";
			} else {
				str += tmp.toString();
			}

		}
				
				
		return str;
	}

	@Override
	public void registerObserver(ObserverIF o) {
		// add new view
		this.obs.add(o);
	}

	@Override
	public void removeObserver(ObserverIF o) {
		// remove old view
		this.obs.remove(o);
		
	}

	@Override
	public void notifyObserver() {
		for(ObserverIF o: this.obs) {
			o.update();
		}
		
	}

	@Override
	public String getBarChart() {
		return new BarChartJSONBuilder().build(this.getHistory());
	}

	@Override
	public String getPieChart() {
		return new PieChartJSONBuilder().build(this.getHistory());
	}

}
