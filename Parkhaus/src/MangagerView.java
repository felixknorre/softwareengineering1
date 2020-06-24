

public class MangagerView implements ObserverIF{
	
	private ParkhausModelIF model;
	int umsatz;
	
	public MangagerView(ParkhausModelIF model) {
		this.model = model;
		model.registerObserver(this);
		this.umsatz = 0;
	}

	@Override
	public void update() {
		this.umsatz = this.model.getParkhausHistory().stream()
		.filter(y -> !(y.duration.equals("_") && y.price.equals("_")))
		.map(y -> Integer.parseInt(y.price))
		.reduce(0, (x,y) -> x + y);
		
	}

	@Override
	public String view() {
		return "Umsatz: " + this.umsatz;
	}

}
