import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Parkhaus implements ParkhausIF, Iterable<AutoIF>{
	
	private List<AutoIF> parkplatz;
	
	private int sum;
	private int count;
	private int min, max;
	
	
	
	public Parkhaus() {
		parkplatz = new ArrayList<AutoIF>();
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
	}



	@Override
	public void removeAuto(AutoIF oldcar) {
		String number = oldcar.getNr();
		for(AutoIF auto : this.parkplatz) {
			if(auto.getNr().equals(number)) {
				this.parkplatz.remove(auto);
				this.sum += Integer.parseInt(oldcar.getPrice());
				this.count++;
				if(Integer.parseInt(auto.getPrice()) > this.max) {
					this.max = Integer.parseInt(auto.getPrice());
				}
				if( Integer.parseInt(auto.getPrice()) < this.min) {
					this.min = Integer.parseInt(auto.getPrice());
				}
				
			}
		}
		
	}



	@Override
	public List<AutoIF> getParkhaus() {
		return this.parkplatz;
	}



	@Override
	public String getSum() {
		return "" + this.sum;
	}



	@Override
	public String getAVG() {
		
		return "" + (this.sum / this.count);
	}



	@Override
	public String getMinMax() {
		return "Min: " + this.min + ", Max: " + this.max;
	}
	
	public String toString() {
		String str = "";
				
				for(int i = 0; i < this.parkplatz.size(); i++) {
					if(i == 0) {
						str += this.parkplatz.get(i).toString();
					} else {
						str += "," + this.parkplatz.get(i).toString();
					}
				}
				
		return str;
	}

}
