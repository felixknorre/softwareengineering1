
public class Parkhaus implements ParkhausIF{
	
	Auto[] parkplatz;
	int i;
	
	public Parkhaus(int i) {
		this.parkplatz = new Auto[i];
		this.i = 0;
	}

	@Override
	public void addAuto(Auto a) {
			if(i < parkplatz.length) {
				parkplatz[a.parkinglot - 1] = a;
				i++;
			}
	}

	@Override
	public void removeAuto(int i) {
		if(i < parkplatz.length) {
			parkplatz[i - 1] = null;
			i--;
		}
	}

	@Override
	public int checkBelegung() {
		// TODO Auto-generated method stub
		return this.i;
	}

	@Override
	public Auto[] getParkhaus() {
		// TODO Auto-generated method stub
		return this.parkplatz;
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < parkplatz.length; i++) {
			if(parkplatz[i] != null) {
				if(str.length() == 0) {
					str += parkplatz[i].toString();
				} else {
					str +=  "," + parkplatz[i].toString() ;
				}
			}
		}
		
		return str;
	}

}
