

public class ManagerView implements ObserverIF{
	
	private ParkhausIF model;
	int umsatz;
	
	public ManagerView(ParkhausIF ph) {
		this.model = ph;
		model.registerObserver(this);
		this.umsatz = 0;
	}

	@Override
	public void update() {
		this.umsatz = this.model.getHistory().stream().map(i -> Integer.parseInt(i.getPrice())).reduce(0, (x,y) -> x + y);
		
	}

	@Override
	public String view() {
		return "" + (this.umsatz / 100);
	}

}
