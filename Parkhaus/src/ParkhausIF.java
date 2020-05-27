import java.util.List;

public interface ParkhausIF {
	public void addAuto(Auto newCar);
	public void removeAuto(Auto car, String dur, String price);
	public int checkBelegung();
	public List<Auto> getParkhaus();
	public String toString();
	public List<Auto> getHistory();
}
