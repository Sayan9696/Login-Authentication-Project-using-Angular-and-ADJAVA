package controller;
import model.User;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper; 
import jdk.nashorn.internal.ir.debug.JSONWriter;
import jdk.nashorn.internal.parser.JSONParser;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		//response.addHeader("Access-Control-Allow-Origin","*");
		//response.addHeader("Access-Control-Allow-Methods","*");
		PrintWriter out =response.getWriter();

		try{
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(request.getInputStream()));
					String line="";
					String json = "";
					while((line= br.readLine())!=null){
						json = json+line;
					//	System.out.println(json);
					}
					User u1= new ObjectMapper().readValue(json,User.class);
					
					User loggedUser = u1.Get(u1.getUsername());
					if(loggedUser!=null){
						if(loggedUser.getPassword().equals(u1.getPassword())){
							response.setStatus(200);
							out.println("{\"id\":"+loggedUser.getId()+",\"name\":\""+loggedUser.getName()+"\",\"message\" : \"logged in\"}");	
						}
						else{
							response.setStatus(401);
							out.println("{\"message\" : \"password doesnt match\"}");
						}
					}
					else{
						response.setStatus(404);
						out.println("{\"message\" : \"user not exists\"}");
						
					}
		}catch(Exception e){
			response.setStatus(500);
		
			out.println("{\"message\" : \"Internal server error\"}");
			out.println(e);
		} 
	}

}
