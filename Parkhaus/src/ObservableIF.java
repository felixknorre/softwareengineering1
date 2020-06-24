
public interface ObservableIF {
	
	public void registerObserver(ObserverIF o); // add view
	public void removeObserver(ObserverIF o); // remove view
	public void notifyObserver(); // notify all views

}
