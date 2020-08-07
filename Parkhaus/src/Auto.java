
public class Auto implements AutoIF{
	
	private String nr;
	private String timer;
	private String duration;
	private String price;
	private String hash;
	private String color;
	private String category;
	private String parkinglot;
	
	
	public Auto(String nr, String timer, String duration, String price, String hash, String color, String p, String cate) {
		this.nr = nr;
		this.timer = timer;
		this.duration = duration;
		this.price = price;
		this.hash = hash;
		this.color = color;	
		this.category = cate;
		this.parkinglot = p;
	}


	public String toString() {
		return "" + this.nr + "/" + this.timer + "/" + this.duration + "/" + this.price + "/" + this.hash + "/" + this.color + "/" + this.parkinglot + "/" + this.category + "/" + this.nr;
	}


	@Override
	public String getNr() {
		return this.nr;
	}


	@Override
	public String getTimer() {
		return this.timer;
	}


	@Override
	public String getDuration() {
		return this.duration;
	}


	@Override
	public String getPrice() {
		return this.price;
	}

	@Override
	public String getHash() {
		return this.hash;
	}


	@Override
	public String getColor() {
		return this.color;
	}


	@Override
	public String getCategory() {
		return this.category;
	}


	@Override
	public String getParkinglot() {
		return this.parkinglot;
	}


	@Override
	public void setNr(String s) {
		this.nr = s;
	}


	@Override
	public void setTimer(String s) {
		this.timer = s;
	}


	@Override
	public void setDuration(String s) {
		this.duration = s;
	}


	@Override
	public void setPrice(String s) {
		this.price = s;
	}


	@Override
	public void setHash(String s) {
		this.hash = s;
	}


	@Override
	public void setColor(String s) {
		this.color = s;
	}


	@Override
	public void setCategory(String s) {
		this.category = s;
	}


	@Override
	public void setParkinglot(String s) {
		this.parkinglot = s;
	}

}
