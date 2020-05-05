
public class Parkhaus implements ParkhausIF{
	
	Auto[] parkplatz;
	int i;
	
	public Parkhaus(int i) {
		parkplatz = new Auto[i];
		i = 0;
	}

	@Override
	public void addAuto(Auto a) {
		if(i < parkplatz.length) {
			parkplatz[i] = a;
			i++;
		}
		
	}

	@Override
	public void removeAuto(int c) {
		
		if(c < i) {
			parkplatz[i] = null;
		}
		
		
	}

	@Override
	public int checkBelegung() {
		// TODO Auto-generated method stub
		return i;
	}

}
