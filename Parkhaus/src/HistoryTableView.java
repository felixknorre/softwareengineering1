import java.util.ArrayList;
import java.util.List;

public class HistoryTableView implements ObserverIF{
	
	ParkhausIF model;
	List<AutoIF> history;
	
	public HistoryTableView(ParkhausIF ph) {
		this.model = ph;
		this.model.registerObserver(this);
		this.history = this.model.getHistory();
	}

	@Override
	public void update() {
		this.history = this.model.getHistory();
		
	}

	@Override
	public String view() {
		String htmlString = "";
		// build html table
		if(this.history.isEmpty()) {
			htmlString += "<p class=\"info\">Bis jetzt hat kein Kunde bezahlt...</p>";
		} else {
			htmlString += "<table style=\"width:100%\"> ";
			
			for(AutoIF a: this.history) {
				htmlString += "<tr> <td>" + a.getNr() + "</td>" + "<td>" + a.getCategory() + "</td>" + "<td>" + convertToTimeFormat(a.getDuration()) + "</td>" + "<td>" + (Double.parseDouble(a.getPrice()) / 100) + "</td> </tr>";
			}
			
			htmlString += "</table>";
		}
		
		return htmlString;
	}
	
	private String convertToTimeFormat(String ms) {
		long seconds = Long.parseLong(ms) / 1000;
		
		int hours = (int)seconds / 3600;
		seconds = (int)seconds % 3600;
		int minutes = (int)seconds / 60;
		seconds = seconds % 60;
		
		return "Stunden: " + hours + ", Minuten: " + minutes + ", Sekunden: " + seconds;
	}

}
