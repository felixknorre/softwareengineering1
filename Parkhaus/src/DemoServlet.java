

import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/Demo")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//split several parameters
		String[] requestParam = request.getQueryString().split("&");
		
		//split first param
		String[] cmd = requestParam[0].split("=");
		
		//if there is a second param, split into key and value
		String[] name = new String[2];
		if(requestParam.length > 1) {
			
			name = requestParam[1].split("=");
		}
		
		//check wich Parkhaus is selected
		if("name".equals(name[0]) && "P1".equals(name[1])) {
			
			//check for config req
			if("config".equals(cmd[1])) {
				System.out.println("config selected");
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
	
				//set config
				// MAX, Opening, Closing, traffic light delay, price 
				out.println("10,0,24,100,10"); 
			}
			
			//check for cars req
			if("cars".equals(cmd[1])) {
				System.out.println("cars selected");
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
	
				//demo data
				// Nr/Timer/Duration/Price/Hash/Color
				// 5/1589113932906/_/_/178b6b9e445210a90fa2e95ef5307ccb/#a762f0/4
				

				
				Parkhaus ph = getPersistentParkhaus();
				String cars = ph.toString();
				
				//car attr separated with /
				// cars separated with ,
				System.out.println(cars);
				out.println(cars); 
			}
		}
		
		if("sum".equals(cmd[1])) {
			// return sum of turnover
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("sum = " + getPersistentSum() / 100 ); // divide by 100 cent to euro
		}
		
		if("avg".equals(cmd[1])) {
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			Float sum = getPersistentSum();
			Float duration = getPersistentDuration();
			Float count = getPersistentCount();
			
			
			if(count == 0) {
				out.println("avgSum = 0.0, avgDuration = Stunden: 0, Minuten: 0, Sekunden: 0");
			} else {
				Float avgSum = sum / count / 100;
				long avgDuration = (long)(duration / count);
				
				//format
				String formatSumString = String.format("%.02f", avgSum);
				
				
				//format Millisec into hours, minutes, seconds
				long seconds = avgDuration / 1000; //milli sec to sec
				int hours = (int)seconds / 3600;
				seconds = (int)seconds % 3600;
				
				int minutes = (int)seconds / 60;
				seconds = seconds % 60;
				
				String avgDurationTime = "Stunden: " + hours + ", Minuten: " + minutes + ", Sekunden: " + seconds;
				
				out.println("avgSum = " + formatSumString + ", avgDuration = " + avgDurationTime);
			}
			
			
		}
		
		if("chart".equals(cmd[1])) {
			System.out.println("chart");	
			response.setContentType("text/plain"); 
			PrintWriter out = response.getWriter();
			
			Parkhaus ph = getPersistentParkhaus();
			List<Auto> hist = ph.history;
			int size = hist.size();
			
			String preString = "{\n" + " \"data\": [\n" + " {\n" + " \"x\": [\n";
			String xValueString = "";
			String inString = "],\n" + " \"y\": [\n";
			String yValueString = "";
			String postString = " ],\n" + " \"type\": \"bar\"\n" + " }\n" + " ]\n" + "}";
			
			// create x value string
			for(int i = 0; i < size; i++) {
				if(i == (size - 1)) {
					xValueString += "\"Car_" + nrToArray(hist)[i] + "\"\n";
					yValueString += durationToArray(hist)[i] + "\n";
				} else {
					xValueString += "\"Car_" + nrToArray(hist)[i] + "\"" + ",\n";
					yValueString += durationToArray(hist)[i] + ",\n";
				}
				
			}
			
			out.println(preString + xValueString + inString + yValueString + postString);
			//System.out.println(preString + xValueString + inString + yValueString + postString);
		}
		
		if("familyChart".equals(cmd[1])) {
			System.out.println("famliyChart");	
			
			response.setContentType("text/plain"); 
			
			Parkhaus ph = getPersistentParkhaus();
			List<Auto> hist = ph.history;
			List<String> categories = hist.stream().map(i -> i.category).distinct().collect(Collectors.toList());
			
			List<Integer> count = (List<Integer>) categories.stream()
					.map(i -> hist.stream().filter(d -> d.category.equals(i))
					.collect(Collectors.toList()).size()).collect(Collectors.toList());
			
			int[] categoryCount = count.stream().mapToInt(i->i).toArray();
			
			//String[] categoryCount = (String[]) count.stream().map(i-> String.valueOf(i)).toArray();
			
			String[] countStringArrayStrings = new String[categoryCount.length];
			
			for(int i = 0; i < categoryCount.length; i++) {
				countStringArrayStrings[i] = String.valueOf(categoryCount[i]);
			}
			
			
			String [] categoryStringArrayStrings = new String[categories.size()];
			categories.toArray(categoryStringArrayStrings);
			
			String preString = "{\n" + " \"data\": [\n" + " {\n" + " \"labels\": [\n";
			String labelString = "";
			String inString = "],\n" + " \"values\": [\n";
			String valueString = "";
			String postString = " ],\n" + " \"type\": \"pie\"\n" + " }\n" + " ]\n" + "}";
			
			for(int k = 0;k < categoryStringArrayStrings.length; k++){
				
				if(k == (categoryStringArrayStrings.length - 1)) {
					labelString += " \""+categoryStringArrayStrings[k]+"\"\n";
					valueString += categoryCount[k] +"\n";
					
				} else {
					
					labelString += " \""+categoryStringArrayStrings[k]+"\",\n";
					valueString += categoryCount[k] +",\n";
				}
			}
			
			String resultString = preString + labelString + inString + valueString + postString ;
			
			ChartJSONBuilder cjb  = new ChartJSONBuilder(categoryStringArrayStrings, countStringArrayStrings, "pie");
			
			String res = cjb.buildJSON();
			System.out.println(res);
			PrintWriter out = response.getWriter();
			//out.println(resultString);
			out.println(resultString);
			
			//System.out.println("{\n" + " \"data\": [\n" + " {\n" + " \"labels\": [\n" + " \"any\",\n" + " \"Familie\",\n" + " \"Frau\"\n" + " ],\n" + " \"values\": [\n" + categoryToArray(hist)[0] +",\n" + categoryToArray(hist)[1] + ",\n" + categoryToArray(hist)[2] +"\n" + " ],\n" + " \"type\": \"pie\"\n" + " }\n" + " ]\n" + "}");
		}
		
		if("Min/MaxPrice".equals(cmd[1])) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			Float minPrice = getPersistentMinPrice();
			Float maxPrice = getPersistentMaxPrice();
			
			out.println("min =" + minPrice / 100 + ", max = " + maxPrice / 100); 
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = getBody(request);
		System.out.println(body);
		
		String[] requestParam = body.split(",");
		
		if("enter".equals(requestParam[0])) {
			Auto newAuto = new Auto(Integer.parseInt(requestParam[1]) , Long.parseLong(requestParam[2]), requestParam[5], requestParam[6], requestParam[8], requestParam[7]);
			Parkhaus ph = getPersistentParkhaus();
			ph.addAuto(newAuto);
			getApplication().setAttribute("parkhaus", ph);
			//System.out.println(ph.toStringHistory());
			System.out.println("Auto entered");
		}
		
		// car leaves parkhaus
		if("leave".equals(requestParam[0])) {
			//System.out.println("leave");
			Auto altesAuto = new Auto(Integer.parseInt(requestParam[1]) , Long.parseLong(requestParam[2]), requestParam[5], requestParam[6], requestParam[8], requestParam[7]);
			Parkhaus ph = getPersistentParkhaus();
			//System.out.println("Altes Auto: "+ altesAuto.toString());
			//System.out.println(ph.toStringHistory());
			ph.removeAuto(altesAuto, requestParam[3], requestParam[4]);
			//System.out.println("history: " + ph.toStringHistory());
			//System.out.println("parkhaus: " + ph.toString());
			
			getApplication().setAttribute("parkhaus", ph);
			
			
			// set sum, duratioin and count
			Float sum = getPersistentSum();
			Float duration = getPersistentDuration();
			Float count = getPersistentCount();
			Float minPrice = getPersistentMinPrice();
			Float maxPrice = getPersistentMaxPrice();
					
			Float price =  Float.parseFloat(requestParam[4]);
			Float newDuration = Float.parseFloat(requestParam[3]);
			
			if(price > maxPrice) {
				maxPrice = price;
			}
			if(minPrice == 0.0) {
				minPrice = price;
			} else {
				if(price < minPrice) {
					minPrice = price;
				}
			}
			
			sum += price;
			duration += newDuration;
			count += 1;
					
			getApplication().setAttribute("sum", sum);
			getApplication().setAttribute("duration", duration);
			getApplication().setAttribute("count", count);
			getApplication().setAttribute("minPrice", minPrice);
			getApplication().setAttribute("maxPrice", maxPrice);
		}
		
		
		
	}
	
	private ServletContext getApplication() {
		return getServletConfig().getServletContext();
	}
	
	
	private Float getPersistentSum() {
		Float sum;
		
		ServletContext application = getApplication();
		sum = (Float)(application.getAttribute("sum"));
		
		if(sum == null) {
			sum = 0.0f;
		} 
		
		return sum;
		
	}
	
	private Float getPersistentDuration() {
		Float duration;
		
		ServletContext application = getApplication();
		duration = (Float)(application.getAttribute("duration"));
		
		if(duration == null) {
			duration = 0.0f;
		} 
		
		return duration;
	}
	
	private Float getPersistentCount() {
		Float count;
		
		ServletContext application = getApplication();
		count = (Float)(application.getAttribute("count"));
		
		if(count == null) {
			count = 0.0f;
		} 
		
		return count;
	}
	
	private Float getPersistentMinPrice() {
		Float min;
		
		ServletContext application = getApplication();
		min = (Float)(application.getAttribute("minPrice"));
		
		if(min == null) {
			min = 0.0f;
		} 
		
		return min;
	}
	
	private Float getPersistentMaxPrice() {
		Float max;
		
		ServletContext application = getApplication();
		max = (Float)(application.getAttribute("maxPrice"));
		
		if(max == null) {
			max = 0.0f;
		} 
		
		return max;
	}
	
	private Parkhaus getPersistentParkhaus() {
		Parkhaus ph;
			
		ServletContext application = getApplication();
		ph = (Parkhaus)(application.getAttribute("parkhaus"));
		
		if(ph == null) {
			ph = new Parkhaus();
		}
		
		return ph;
	}
	
	private List<Auto> cars(){
		List<Auto> hist;
		Parkhaus ph;
		
		ServletContext application = getApplication();
		ph = (Parkhaus)(application.getAttribute("parkhaus"));
		hist = ph.history;
		
		return hist;
	}
	
	public String[] nrToArray(List<Auto> hist) {
		int size = hist.size();
		String nrArray[] = new String[size];
		for(int i = 0; i < size; i++) {
			nrArray[i] = Integer.toString(hist.get(i).nr);
		}
		return nrArray;
	}
	
	public String[] durationToArray(List<Auto> hist) {
		int size = hist.size();
		String durationArray[] = new String[size];
		for(int i = 0; i < size; i++) {
			if("_".equals(hist.get(i).duration)) {
				durationArray[i] = "0";
			} else {
				durationArray[i] = hist.get(i).duration;
			}
		}
		return durationArray;
	}
	

	
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
