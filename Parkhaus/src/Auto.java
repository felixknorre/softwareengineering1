
public class Auto implements AutoIF{
	
	int nr;
	long timer;
	String duration;
	String price;
	String hash;
	String color;
	String category;
	String parkinglot;
	
	
	public Auto(int nr, long timer, String hash, String color, String cate, String p) {
		this.nr = nr;
		this.timer = timer;
		this.hash = hash;
		this.color = color;	
		this.category = cate;
		this.duration = "_";
		this.price = "_";
		this.parkinglot = p;
		
	}


	public String toString() {
		//5/1589113932906/_/_/178b6b9e445210a90fa2e95ef5307ccb/#a762f0/4
		return "" + this.nr + "/" + this.timer + "/" + this.duration + "/" + this.price + "/" + this.hash + "/" + this.color + "/" + this.parkinglot + "/" + this.category + "/" + this.nr;
	}

}
