
public class MitarbeiterView implements ObserverIF{
	
	private ParkhausModelIF model;
	int belegtePlaetze;
	
	public MitarbeiterView(ParkhausModelIF model) {
		this.model = model;
		model.registerObserver(this);
		this.belegtePlaetze = 0;
	}

	@Override
	public void update() {
		this.belegtePlaetze = this.model.getBelegung();
	}

	@Override
	public String view() {
		return "" + belegtePlaetze;
	}

}
