
public class Parkticket implements ParkticketIF{
	
	// unix time format
	int checkInTime;
	int checkOutTime;
	double price;
	
	public Parkticket(int currentTime) {
		this.checkInTime = currentTime;
		this.price = 0;
	}

	@Override
	public int getCheckInTime() {
		return this.checkInTime;
	}


	@Override
	public int getCheckOutTime() {
		// TODO Auto-generated method stub
		return this.checkOutTime;
	}

	@Override
	public void setCheckOutTime(int t) {
		this.checkOutTime = t;	
	}

	@Override
	public double getPrice() {
		return this.price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
		
	}

}
