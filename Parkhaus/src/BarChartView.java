
public class BarChartView implements ObserverIF{
	
	ParkhausIF model;
	String json;
	
	public BarChartView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.json = this.model.getBarChart();
	}

	@Override
	public void update() {
		this.json = this.model.getBarChart();
	}

	@Override
	public String view() {
		return this.json;
	}

}
