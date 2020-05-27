import java.util.ArrayList;
import java.util.List;

public class Parkhaus implements ParkhausIF{
	
	List<Auto> parkplatz;
	List<Auto> history;
	
	public Parkhaus() {
		parkplatz = new ArrayList<Auto>();
		history = new ArrayList<Auto>();
	}

	@Override
	public void addAuto(Auto newCar) {
		this.parkplatz.add(newCar);
		this.history.add(newCar);

	}

	@Override
	public void removeAuto(Auto car, String dur, String price) {
		//String hash = car.hash;
		int number = car.nr;
		for( int i = 0; i < this.parkplatz.size(); i++) {
			//String currHash = this.parkplatz.get(i).hash;
			int currNR = this.parkplatz.get(i).nr;
			if(currNR == number) {
				this.parkplatz.remove(i);
			}
		}
		int size = this.history.size();
		for(int j = 0; j < size; j++) {
			if(number == this.history.get(j).nr) {
				this.history.get(j).duration = dur;
				this.history.get(j).price = price;
			}
		}

	}

	@Override
	public int checkBelegung() {
		// TODO Auto-generated method stub
		return this.parkplatz.size();
	}

	@Override
	public List<Auto> getParkhaus() {
		// TODO Auto-generated method stub
		return this.parkplatz;
	}
	
	public List<Auto> getHistory(){
		return this.history;
	}
	
	public void setCeckoutData(String hash, String duration, String price) {
		for(Auto a : history) {
			if(a.hash.equals(hash)) {
				a.duration = duration;
				a.price = price;
			}
		}
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
	
	public String toStringHistory() {
		String str = "";
		
		for(int i = 0; i < this.history.size(); i++) {
			if(i == 0) {
				str += this.history.get(i).toString();
			} else {
				str += "," + this.history.get(i).toString();
			}
		}
		
		return str;
	}

}
