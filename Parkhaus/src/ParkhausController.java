import java.util.List;

public class ParkhausController implements ParkhausControllerIF{
	
	List<ObserverIF> views;
	ParkhausModelIF model;
	
	public ParkhausController(ParkhausModelIF model, List<ObserverIF> views) {
		this.model = model;
		this.views = views;
	}
	

	@Override
	public void addAuto(Auto a) {
		model.addAuto(a);
		
	}

	@Override
	public void removeAuto(Auto car, String dur, String price) {
		model.removeAuto(car, dur, price);
		
	}

}
