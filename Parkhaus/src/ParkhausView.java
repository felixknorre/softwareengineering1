
public class ParkhausView implements ObserverIF{
	
	ParkhausIF model;
	String autos;
	
	public ParkhausView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.autos = "";
	}

	@Override
	public void update() {
		// get all Autos in Parkhaus in String format
		this.autos = this.model.toString();
	}

	@Override
	public String view() {
		return this.autos;
	}

}
