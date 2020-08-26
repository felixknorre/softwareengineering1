
public class PieChartView implements ObserverIF{
	
	ParkhausIF model;
	String json;
	
	public PieChartView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.json = this.model.getPieChart();
	}

	@Override
	public void update() {
		this.json = this.model.getPieChart();
	}

	@Override
	public String view() {
		return this.json;
	}

}
