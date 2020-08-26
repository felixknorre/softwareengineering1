import java.util.List;

public interface ParkhausIF extends ObservableIF{
	
	public void addAuto(AutoIF newCar);
	public void removeAuto(AutoIF oldcar);
	public List<AutoIF> getParkhaus();
	public List<AutoIF> getHistory();
	public Double getSum();
	public Double getAVG();
	public Double getMin();
	public Double getMax();
	public String getBarChart();
	public String getPieChart();
	public String toString();
}
