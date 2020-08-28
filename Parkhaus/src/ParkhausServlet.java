

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.text.View;

/**
 * Servlet implementation class ParkhausServlet
 */
@WebServlet("/Parkhaus")
public class ParkhausServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParkhausServlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		ParkhausCallerIF parkhausCaller;
		PreisberechnerIF pb;
		
		
		switch (request.getParameter("cmd")) {
		case "config":
			System.out.println("load Parkhaus Config...");
			out.println(getParkhausConfig().toString());
			break;
		case "cars":
			System.out.println("load cars...");
			out.println(getParkhausView().view());
			break;
		case "Summe":
			out.println(getSummenView().view());
			break;
		case "AVG":
			out.println(getAVGView().view());
			break;
		case "Min/Max-Price":
			out.println(getMinMaxView().view());
			break;
		case "Chart":
			System.out.println(getBarChartView().view());
			out.println(getBarChartView().view());
			//out.println("{\n" + " \"data\": [\n" + " {\n" + " \"x\": [\n" + " \"Car_1\",\n" + " \"Car_2\",\n" + " \"Car_3\"\n" + " ],\n" + " \"y\": [\n" + " 20,\n" + " 14,\n" + " 23\n" + " ],\n" + " \"type\": \"bar\"\n" + " }\n" + " ]\n" + "}");
			break;
		case "FamilyChart":
			System.out.println(getPieChartView().view());
			out.println(getPieChartView().view());
			
			break;
		case "Undo":
			System.out.println("Undo");
			parkhausCaller = getParkhausCaller();
			parkhausCaller.undo();
			setParkhausCaller(parkhausCaller);
			out.println("Laden Sie die Seite neu...");
			break;
		case "Redo":
			System.out.println("Redo");
			parkhausCaller = getParkhausCaller();
			parkhausCaller.redo();
			setParkhausCaller(parkhausCaller);
			out.println("Laden Sie die Seite neu...");
			break;
		case "Abo":
			System.out.println("Abo...");
			pb = getPreisberechner();
			pb.createSubscription();
			setPreisberechner(pb);
			out.println("Abo abschliessen...");
			break;
		case "Parkhauspreise":
			System.out.println("Preise...");
			pb = getPreisberechner();
			out.println(pb.toString());
			break;
		default:
			System.out.println("Not defined get command: " + request.getParameter("cmd"));
			break;
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// tmp config var
		ParkhausConfigIF p;
		ParkeinweiserIF pew;
		ParkhausControllerIF phc;
		ParkhausCallerIF pCaller;
		CommandIF command;
		PreisberechnerIF pb;
		
		
		// get request body
		String body = getBody(request);
		System.out.println(body);
		
		String[] requestParam = body.split(",");
		

		switch (requestParam[0]) {
		case "enter":
			//System.out.println("enter...");
			
			// get Parkplatz
			pew = getParkeinweiser();
			String parkplatz = "" + pew.getParkplatz();
			setParkeinweiser(pew);
			// park Auto in Parkhaus
			phc = getParkhausController();
			
			// create abo
			pb = getPreisberechner();
			pb.subscription(requestParam[1]);
			setPreisberechner(pb);
			
			// command
			pCaller = getParkhausCaller();
			command = new AddAutoCommand(phc, new Auto(requestParam[1], requestParam[2], requestParam[3], requestParam[4], requestParam[5], requestParam[6], parkplatz, requestParam[8]));
			pCaller.saveNewCommand(command);
			pCaller.execute();
			setParkhausCaller(pCaller);
			
			//phc.addAuto(new Auto(requestParam[1], requestParam[2], requestParam[3], requestParam[4], requestParam[5], requestParam[6], parkplatz, requestParam[8]));
			// safe changed Model in ServletConext
			setParkhaus(phc.getModel());
			// respone to client the parkplatz number
			int length = parkplatz.length();
			response.setContentLength(length);
			response.getOutputStream().write(parkplatz.getBytes());
			break;
		case "leave":
			System.out.println("leave...");
			// remove Auto 
			
			// calc price
			pb = getPreisberechner();
			String price = pb.calculatedPrice(requestParam[8], requestParam[3], requestParam[1]);
			
			// get controller
			phc = getParkhausController();
			
			// command
			pCaller = getParkhausCaller();
			command = new RemoveAutoCommand(phc, new Auto(requestParam[1], requestParam[2], requestParam[3], price, requestParam[5], requestParam[6], requestParam[7], requestParam[8]));
			pCaller.saveNewCommand(command);
			pCaller.execute();
			setParkhausCaller(pCaller);
			//phc.removeAuto(new Auto(requestParam[1], requestParam[2], requestParam[3], requestParam[4], requestParam[5], requestParam[6], requestParam[7], requestParam[8]));
			setParkhaus(phc.getModel());
			// return Parkplatz
			pew = getParkeinweiser();
			pew.returnParkplatz(Integer.parseInt(requestParam[7]));
			setParkeinweiser(pew);
			break;
		case "change_max":
			System.out.println("change_max...");
			// update ParkhausConfig
			p = getParkhausConfig();
			p.setMaxAutos((String)requestParam[2]);
			setParkhausConfig(p);
			// update Parkeinweiser
			pew = getParkeinweiser();
			pew.changeSize(Integer.parseInt(requestParam[2]));
			setParkeinweiser(pew);
			break;
		case "change_open_from":
			System.out.println("change_open_from...");
			p = getParkhausConfig();
			p.setOpen((String)requestParam[2]);
			setParkhausConfig(p);
			break;
		case "change_open_to":
			System.out.println("change_open_to...");
			p = getParkhausConfig();
			p.setClose((String)requestParam[2]);
			setParkhausConfig(p);
			break;
		default:
			System.out.println("Not defined post command...");
			break;
		}
	}
	
	
	// -------------------------------------
	// -- Help-Mothodes Servlet Context
	// -------------------------------------
	
	// get Servlet Context
	private ServletContext getApplication() {
		return getServletConfig().getServletContext();
	}
	
	// ---------------------------------------------------
	// get Parkhaus Config
	private ParkhausConfigIF getParkhausConfig() {
		ParkhausConfigIF p;
		ServletContext application = getApplication();
		p = (ParkhausConfigIF)application.getAttribute("config");
		
		if(p == null) {
			// erzeuge neue Config
			p = new ParkhausConfig("10", "5", "23", "100", "10");
			// speichere config in servlet context
			application.setAttribute("config", p);
		}
		
		return p;	
	}
	
	// set Parkhaus Config
	private void setParkhausConfig(ParkhausConfigIF p) {
		ServletContext application = getApplication();
		application.setAttribute("config", p);
	}
	// ---------------------------------------------------
		//get Parkeinweiser
		private ParkeinweiserIF getParkeinweiser() {
			ParkeinweiserIF pew;
			
			ServletContext application = getApplication();
			pew = (ParkeinweiserIF)(application.getAttribute("parkeinweiser"));
			
			if(pew == null) {
				pew = new Parkeinweiser(Integer.parseInt(getParkhausConfig().getMaxAuto()));
			}
			
			return pew;
		}
		
		// set Parkeinweiser
		private void setParkeinweiser(ParkeinweiserIF pew) {
			ServletContext application = getApplication();
			application.setAttribute("parkeinweiser", pew);
		}
	// ---------------------------------------------------
		
	// ---------------------------------------------------
	
	// get Parkhaus
	private ParkhausIF getParkhaus() {
		ParkhausIF ph;
		
		ServletContext application = getApplication();
		ph = (ParkhausIF)(application.getAttribute("parkhaus"));
		
		if(ph == null) {
			ph = new Parkhaus();
			application.setAttribute("parkhaus", ph);
		}
		
		return ph;
	}
	// set Parkhaus
	private void setParkhaus(ParkhausIF ph) {
		ServletContext application = getApplication();
		application.setAttribute("parkhaus", ph);
	}
	
	private ParkhausView getParkhausView() {
		ParkhausView phv;
		
		ServletContext application = getApplication();
		phv = (ParkhausView)(application.getAttribute("parkhausview"));
		
		if(phv == null) {
			phv = new ParkhausView(getParkhaus());
			application.setAttribute("parkhausview", phv);
		}
		
		return phv;
		
	}
	
	private SummenView getSummenView() {
		SummenView phv;
		
		ServletContext application = getApplication();
		phv = (SummenView)(application.getAttribute("summenview"));
		
		if(phv == null) {
			phv = new SummenView(getParkhaus());
			application.setAttribute("summenview", phv);
		}
		
		return phv;
		
	}
	
	private AVGView getAVGView() {
		AVGView phv;
		
		ServletContext application = getApplication();
		phv = (AVGView)(application.getAttribute("avgview"));
		
		if(phv == null) {
			phv = new AVGView(getParkhaus());
			application.setAttribute("avgview", phv);
		}
		
		return phv;
		
	}
	
	private MinMaxView getMinMaxView() {
		MinMaxView phv;
		
		ServletContext application = getApplication();
		phv = (MinMaxView)(application.getAttribute("minmaxview"));
		
		if(phv == null) {
			phv = new MinMaxView(getParkhaus());
			application.setAttribute("minmaxview", phv);
		}
		
		return phv;
		
	}
	
	private BarChartView getBarChartView() {
		BarChartView phv;
		
		ServletContext application = getApplication();
		phv = (BarChartView)(application.getAttribute("barchartview"));
		
		if(phv == null) {
			phv = new BarChartView(getParkhaus());
			application.setAttribute("barchartview", phv);
		}
		
		return phv;
	}
	
	private PieChartView getPieChartView() {
		PieChartView phv;
		
		ServletContext application = getApplication();
		phv = (PieChartView)(application.getAttribute("piechartview"));
		
		if(phv == null) {
			phv = new PieChartView(getParkhaus());
			application.setAttribute("piechartview", phv);
		}
		
		return phv;
	}
	
	private ParkhausControllerIF getParkhausController() {
		ParkhausControllerIF pc;
		
		ServletContext application = getApplication();
		pc = (ParkhausControllerIF)(application.getAttribute("parkhauscontroller"));
		
		if(pc == null) {
			List<ObserverIF> views = new ArrayList<ObserverIF>();
			views.add(getParkhausView());
			views.add(getSummenView());
			views.add(getAVGView());
			views.add(getMinMaxView());
			views.add(getBarChartView());
			views.add(getPieChartView());
			
			pc = new ParkhausController(getParkhaus(), views);
			
		}
		
		return pc;
	}
	
	// ParkhausCaller
	
	private ParkhausCallerIF getParkhausCaller() {
		ParkhausCallerIF pc;
		
		ServletContext application = getApplication();
		pc = (ParkhausCallerIF)(application.getAttribute("parkhauscaller"));
		
		if(pc == null) {
			pc = new ParkhausCaller();
			application.setAttribute("parkhauscaller", pc);
		}
		
		return pc;
	}
	
	private void setParkhausCaller(ParkhausCallerIF pc) {
		ServletContext application = getApplication();
		application.setAttribute("parkhauscaller", pc);
	}
	
	private PreisberechnerIF getPreisberechner() {
		PreisberechnerIF pb;
			
		ServletContext application = getApplication();
		pb = (PreisberechnerIF)(application.getAttribute("preisberechner"));
			
		if(pb == null) {
			pb = new Preisberechner(2, 1, 1);
			application.setAttribute("preisberechner", pb);
		}
			
		return pb;
	}
	
	private void setPreisberechner(PreisberechnerIF pb) {
		ServletContext application = getApplication();
		application.setAttribute("preisberechner", pb);
	}
	
	
	
	
	
	
	

	
	// -------------------------------------
	// -- get body of request --
	// -------------------------------------
	
	private static String getBody(HttpServletRequest request) throws IOException{
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        }finally {
            if(bufferedReader != null){
                bufferedReader.close();

                }
            }
        return stringBuilder.toString();


    }

}
