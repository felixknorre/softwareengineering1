
public interface ParkeinweiserIF {
	
	public int getParkplatz(String type);
	public void returnParkplatz(int p);
	public void changeSize(int newSize);
	public int getSize();

}
