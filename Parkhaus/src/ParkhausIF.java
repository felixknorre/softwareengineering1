

public interface ParkhausIF {
	public void addAuto(Auto a);
	public void removeAuto(int i);
	public int checkBelegung();
	public Auto[] getParkhaus();
	public String toString();
}
