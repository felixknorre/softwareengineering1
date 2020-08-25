import java.util.List;

public class ParkhausController implements ParkhausControllerIF{
	
	List<ObserverIF> views;
	ParkhausIF model;
	
	public ParkhausController(ParkhausIF model, List<ObserverIF> views) {
		this.model = model;
		this.views = views;
	}
	

	@Override
	public void addAuto(AutoIF a) {
		model.addAuto(a);
		
	}

	@Override
	public void removeAuto(AutoIF car) {
		model.removeAuto(car);
		
	}

}
