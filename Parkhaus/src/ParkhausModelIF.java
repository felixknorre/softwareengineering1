import java.util.List;

public interface ParkhausModelIF extends ObservableIF{
	
	public void addAuto(Auto a);
	public void removeAuto(Auto car, String dur, String price);
	public List<Auto> getParkhausHistory();
	public int getBelegung();

}
