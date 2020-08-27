
public class RemoveAutoCommand implements CommandIF{
	
	ParkhausControllerIF phc;
	AutoIF a;
	String price, duration;
	
	public RemoveAutoCommand(ParkhausControllerIF phc, AutoIF a) {
		this.phc = phc;
		this.a = a;
		this.price = a.getPrice();
		this.duration = a.getDuration();
		
	}

	@Override
	public void execute() {
		this.a.setPrice(this.price);
		this.a.setDuration(this.duration);
		this.phc.removeAuto(this.a);
		
	}

	@Override
	public void undo() {
		// remove from history

		for(int i = 0; i < this.phc.getModel().getHistory().size(); i++) {
			AutoIF tmp = this.phc.getModel().getHistory().get(i);
			if(tmp.getNr() == this.a.getNr()) {
				this.phc.getModel().getHistory().remove(tmp);
				this.phc.getModel().notifyObserver();
				
				// re-add car to Parkhaus
				AutoIF tmpAuto = this.a;
				tmpAuto.setDuration("_");
				tmpAuto.setPrice("_");
				this.phc.addAuto(this.a);
			
			}
		}
		
		
		
		
	}

}
