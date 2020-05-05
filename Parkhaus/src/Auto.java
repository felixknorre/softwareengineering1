
public class Auto implements AutoIF{
	
	static int id;
	String color;
	
	public Auto() {
		++id;
		this.color = "red";	
	}

	public Auto(String c) {
		++id;
		this.color = c;	
	}

	@Override
	public void setColor(String c) {
		this.color = c;
		
	}

	@Override
	public String getColor() {
		return this.color;
	}

}
