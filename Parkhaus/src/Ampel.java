
public class Ampel implements AmpelIF {
	
	String color; // red or green
	
	public Ampel() {
		this.color = "red";
	}

	@Override
	public void setColor(String c) {
		if(c == "red"|| c == "green") {
			this.color = c;
		}
		
	}

	@Override
	public String getColor() {
		return this.color;
	}


}
