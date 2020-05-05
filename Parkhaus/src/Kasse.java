

public class Kasse implements KasseIF {
	
	final double pricePerHour = 1.2;

	@Override
	public ParkticketIF evaluTicket(ParkticketIF p) {
			p.setCheckOutTime(5);
			int deltaOfTime = p.getCheckOutTime() - p.getCheckInTime();
			p.setPrice(deltaOfTime * pricePerHour);
			
		return p;
	}

}
