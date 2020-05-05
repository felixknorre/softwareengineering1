
public class Schranke implements SchrankeIF {
	
	String state;
	
	public Schranke() {
		this.state = "close";
	}

	@Override
	public void open() {
		this.state = "open";
		
	}

	@Override
	public void close() {
		this.state = "close";
		
	}

	@Override
	public String getStatus() {
		return this.state;
	}


}
