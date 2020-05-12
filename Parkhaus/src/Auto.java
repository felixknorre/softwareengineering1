
public class Auto implements AutoIF{
	
	int nr;
	long timer;
	String hash;
	String color;
	int parkinglot;
	
	public Auto(int nr, long timer, String hash, String color, int parkinglot) {
		this.nr = nr;
		this.timer = timer;
		this.hash = hash;
		this.color = color;	
		this.parkinglot = parkinglot;
	}


	public String toString() {
		//5/1589113932906/_/_/178b6b9e445210a90fa2e95ef5307ccb/#a762f0/4
		return "" + this.nr + "/" + this.timer + "/_/_/" + this.hash + "/" + this.color + "/" + this.parkinglot ;
	}

}
