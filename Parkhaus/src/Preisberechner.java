import java.util.ArrayList;
import java.util.List;

public class Preisberechner implements PreisberechnerIF{
	
	// Preise
	int priceAny;
	int priceFrau;
	int priceFamilie;
	
	// Abo Liste
	List<String> abos;
	boolean createAbo;
	
	
	public Preisberechner(int any, int frau, int family) {
		this.priceAny = any;
		this.priceFamilie = family;
		this.priceFrau = frau;
		this.abos = new ArrayList<String>();
		this.createAbo = false;
	}
	

	@Override
	public String calculatedPrice(String type, String d, String nr) {
		// parse duration
		int duration = Integer.parseInt(d);
		double price = 0.00;
		
		if(!this.abos.contains(nr)) {
			switch (type) {
			case "Frau":
				price = (duration / 1000) * this.priceFrau;
				break;
			case "Familie":
				price = (duration / 1000) * this.priceFamilie;
				break;
			default:
				price = (duration / 1000) * this.priceAny;
				break;
			}
		}
		
		return "" + price;
	}


	@Override
	public void createSubscription() {
		this.createAbo = true;
		
	}


	@Override
	public void subscription(String nr) {
		if(this.createAbo) {
			this.abos.add(nr);
			this.createAbo = false;
		}
	}
	
	@Override
	public String toString() {
		return "Frauen: " + this.priceFrau + " Euro, Familie: " + this.priceFamilie + " Euro, Andere: " + this.priceAny + " Euro (pro Sekunde)";
	}

}
