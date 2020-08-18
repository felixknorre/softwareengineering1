import java.util.List;

public interface ParkhausIF {
	
	public void addAuto(AutoIF newCar);
	public void removeAuto(AutoIF oldcar);
	public List<AutoIF> getParkhaus();
	public List<AutoIF> getHistory();
	public String getSum();
	public String getAVG();
	public String getMinMax();
	public String toString();
}
