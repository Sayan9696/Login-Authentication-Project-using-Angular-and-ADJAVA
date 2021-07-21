package controller;
import model.User;
import java.io.*;
import java.io.PrintWriter;
import java.io.IOException;
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
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		try{
			
			String username= request.getParameter("username");
			User obj= new User().Get(username);
			
			
				
			if(obj!=null)
			{
				response.setStatus(200);
				ObjectMapper om=new ObjectMapper();
				out.print(om.writeValueAsString(obj));
				
			}
			else{
				response.setStatus(404);
				//out.print("No user found");
				out.println("{\"message\" : \"No user found\"}");
			}
			out.flush();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		PrintWriter out =response.getWriter();

		try{
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(request.getInputStream()));
					String line="";
					String json = "";
					while((line= br.readLine())!=null){
						json = json+line;
					
					}
					User u1= new ObjectMapper().readValue(json,User.class);
					u1.Add();
					out.println("{\"message\" : \"user created\"}");
					//out.println(u1.getName());
		}catch(Exception e){
			out.println(e);
		}
		//doGet(request, response);
	}

}
