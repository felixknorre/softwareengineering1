

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/Parkhaus")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//split several parameters
		String[] requestParam = request.getQueryString().split("&");
		//split first param
		String[] cmd = requestParam[0].split("=");
		//split second param into key and value
		String[] name = requestParam[1].split("=");
		
		//check wich Parkhaus is selected
		if("name".equals(name[0]) && "P1".equals(name[1])) {
			
			//check for config req
			if("config".equals(cmd[1])) {
				System.out.println("config selected");
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
	
				//set config
				// MAX, Opening, Closing, traffic light delay, price 
				out.println("10,5,22,100,10"); 
			}
			
			//check for cars req
			if("cars".equals(cmd[1])) {
				System.out.println("cars selected");
				
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
	
				//demo data
				// Nr/Timer/Duration/Price/Hash/Color
				// 5/1589113932906/_/_/178b6b9e445210a90fa2e95ef5307ccb/#a762f0/4

				
				//car attr separated with /
				// cars separated with ,
				out.println(""); 
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = getBody(request);
		System.out.println(body);
		
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
