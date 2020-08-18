

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ChartJSONBuilder cjb = new ChartJSONBuilder();
		
		
		switch (request.getParameter("cmd")) {
		case "config":
			System.out.println("load Parkhaus Config...");
			out.println(getParkhausConfig().toString());
			break;
		case "cars":
			out.println(getParkhaus().toString());
			break;
		case "Summe":
			out.println((Double.parseDouble(getParkhaus().getSum()) / 100));
			break;
		case "AVG":
			out.println(getParkhaus().getAVG());
			break;
		case "Min/Max-Price":
			out.println(getParkhaus().getMinMax());
			break;
		case "Chart":
			String test = cjb.buildJSON(getParkhaus(), "bar");
			System.out.println(test);
			out.println(test);
			//out.println("{\n" + " \"data\": [\n" + " {\n" + " \"x\": [\n" + " \"Car_1\",\n" + " \"Car_2\",\n" + " \"Car_3\"\n" + " ],\n" + " \"y\": [\n" + " 20,\n" + " 14,\n" + " 23\n" + " ],\n" + " \"type\": \"bar\"\n" + " }\n" + " ]\n" + "}");
			break;
		case "FamilyChart":
			String test2 = cjb.buildJSON(getParkhaus(), "pie");
			System.out.println(test2);
			out.println(test2);
			
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
		ParkhausIF ph;
		
		// get request body
		String body = getBody(request);
		System.out.println(body);
		
		String[] requestParam = body.split(",");
		

		switch (requestParam[0]) {
		case "enter":
			System.out.println("enter...");
			ph = getParkhaus();
			ph.addAuto(new Auto(requestParam[1], requestParam[2], requestParam[3], requestParam[4], requestParam[5], requestParam[6], requestParam[7], requestParam[8]));
			setParkhaus(ph);
			break;
		case "leave":
			System.out.println("leave...");
			ph = getParkhaus();
			ph.removeAuto(new Auto(requestParam[1], requestParam[2], requestParam[3], requestParam[4], requestParam[5], requestParam[6], requestParam[7], requestParam[8]));
			setParkhaus(ph);
			break;
		case "change_Max":
			System.out.println("change_Max...");
			p = getParkhausConfig();
			p.setMaxAutos((String)requestParam[2]);
			setParkhausConfig(p);
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
	
	// get Parkhaus
	private ParkhausIF getParkhaus() {
		ParkhausIF ph;
		
		ServletContext application = getApplication();
		ph = (ParkhausIF)(application.getAttribute("parkhaus"));
		
		if(ph == null) {
			ph = new Parkhaus();
		}
		
		return ph;
	}
	// set Parkhaus
	private void setParkhaus(ParkhausIF ph) {
		ServletContext application = getApplication();
		application.setAttribute("parkhaus", ph);
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
