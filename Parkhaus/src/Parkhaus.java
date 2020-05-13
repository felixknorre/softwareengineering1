import java.util.ArrayList;
import java.util.List;

public class Parkhaus implements ParkhausIF{
	
	List<Auto> parkplatz;
	
	public Parkhaus() {
		parkplatz = new ArrayList<Auto>();
	}

	@Override
	public void addAuto(Auto newCar) {
		this.parkplatz.add(newCar);

	}

	@Override
	public void removeAuto(Auto car) {
		String hash = car.hash;
		for( int i = 0; i < this.parkplatz.size(); i++) {
			String currHash = this.parkplatz.get(i).hash;
			if(currHash.equals(hash)) {
				this.parkplatz.remove(i);
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
