import java.text.DecimalFormat;

public class AVGView implements ObserverIF{
	
	DecimalFormat f = new DecimalFormat("#0.00");
	
	private ParkhausIF model;
	Double avg;
	
	public AVGView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.avg = 0.0;
	}

	@Override
	public void update() {
		this.avg = this.model.getAVG();
		
	}

	@Override
	public String view() {
		return f.format(this.avg) + " Euro";
	}

}
