
public class AddAutoCommand implements CommandIF{
	
	ParkhausControllerIF phc;
	AutoIF a;
	
	public AddAutoCommand(ParkhausControllerIF phc, AutoIF a) {
		this.phc = phc;
		this.a = a;
	}

	@Override
	public void execute() {
		this.phc.addAuto(this.a);
		
	}

	@Override
	public void undo() {
		for(int i = 0; i < this.phc.getModel().getParkhaus().size(); i++) {
			AutoIF tmp = this.phc.getModel().getParkhaus().get(i);
			if(tmp.getNr() == this.a.getNr()) {
				this.phc.getModel().getParkhaus().remove(tmp);
				this.phc.getModel().notifyObserver();
				break;
			}
		}
	}

}
