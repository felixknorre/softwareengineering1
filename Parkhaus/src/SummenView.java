import java.text.DecimalFormat;

public class SummenView implements ObserverIF{
	
	DecimalFormat f = new DecimalFormat("#0.00");
	
	private ParkhausIF model;
	Double umsatz;
	
	public SummenView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.umsatz = 0.00;
	}

	@Override
	public void update() {
		this.umsatz = this.model.getSum();
		
	}

	@Override
	public String view() {
		return f.format(this.umsatz) + " Euro";
	}

}
