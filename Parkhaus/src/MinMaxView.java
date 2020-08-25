import java.text.DecimalFormat;

public class MinMaxView implements ObserverIF{
	
	DecimalFormat f = new DecimalFormat("#0.00");
	
	private ParkhausIF model;
	Double min, max;
	
	public MinMaxView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.min = 0.00;
		this.max = 0.00;
	}

	@Override
	public void update() {
		this.min = this.model.getMin();
		this.max = this.model.getMax();
	}

	@Override
	public String view() {
		return "Min: " + f.format(this.min) + ", Max: " + f.format(this.max);
	}

}
