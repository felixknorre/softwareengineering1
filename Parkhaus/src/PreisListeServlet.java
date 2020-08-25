

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PreisListeServlet
 */
@WebServlet("/Preisliste")
public class PreisListeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreisListeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// returns Preisliste 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String result = "<style>\n" + 
				"	h1 {\n" + 
				"    text-align:center;\n" + 
				"	}\n" + 
				"	\n" + 
				"table, th, td {" +
				"	  border: 1px solid black;" +
				"	  border-collapse: collapse;" +
				"     text-align: left;" +
				"	}" + 
				"a:link, a:visited {" +
				"	  background-color: black;" +
				"	  color: white;" + 
				"	  padding: 14px 25px;" + 
				"	  text-align: center;" +
				"	  text-decoration: none;" +
				"	  display: inline-block;" +
				"	}" +
				"	a:hover, a:active {" +
				"	  background-color: grey;" +
				"	}" +
				".info {"+
				"text-align:center;" +
				"}"+
				".pagelinks {" +
				"	text-align: center;" +
				"	}" +
				"	.pagelinks a{" +
				"     display: inline-block;" +
				"     margin-left: 3px;" +
				" 	}" +
				"	</style>";
		
		result += "<h1>Preisliste</h1>";
		result += "<hr>";
		// dev: http://localhost:8080/Parkhaus/
		// deploy: http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/
		result += "<div class=\"pagelinks\"> <a href=\"http://localhost:8080/Parkhaus/\">Parkhaus</a> \n";
		// dev: http://localhost:8080/Parkhaus/Preisliste
		// deploy: http://sepp-test.inf.h-brs.de:8080/mk_se1_ss20_team3/Preisliste
		result += "<a href=\"http://localhost:8080/Parkhaus/Preisliste\">Preisliste</a> </div>\n";
		result += "<hr>";
		
		// get ServletContext
		ServletContext application = getServletConfig().getServletContext();
		
		HistoryTableView htv = (HistoryTableView)(application.getAttribute("historytableview"));
		
		if(htv == null) {
			// get Parkhaus
			ParkhausIF ph;
			ph = (ParkhausIF)(application.getAttribute("parkhaus"));
			if(ph == null) {
				ph = new Parkhaus();
			}
			
			htv = new HistoryTableView(ph);
			application.setAttribute("historytableview", htv);
		}
		
		
		
		result += htv.view();
		
		out.println(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	// -------------------------------------------------
	// -- Help-Mothodes Servlet Context & HTML Table
	// -------------------------------------------------
	
	private String createHTMLTable() {
		String htmlString = "<table style=\"width:100%\"> ";
		
		ServletContext application = getServletConfig().getServletContext();
		
		ParkhausIF ph;
		ph = (ParkhausIF)(application.getAttribute("parkhaus"));
		if(ph == null) {
			ph = new Parkhaus();
		}
		
		
		
		if(ph.getHistory().isEmpty()) {
			htmlString += "<p class=\"info\">Bis jetzt hat kein Kunde bezahlt...</p>";
		} else {
			htmlString += "<tr> <th>Nr.</th> <th>Kategorie</th> <th>Dauer</th> <th>Preis</th> </tr>";
			for(AutoIF a : ph.getHistory()) {
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
