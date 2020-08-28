
public interface PreisberechnerIF {
	public String calculatedPrice(String type, String duration, String nr);
	public void createSubscription();
	public void subscription(String nr);
	public String toString();

}
