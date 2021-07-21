package controller;
import model.Image;
import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * Servlet implementation class ImageController
 */
@WebServlet("/ImageController")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		try{
			
			String userid= request.getParameter("userid");
			int c = Integer.parseInt(userid);
			ArrayList<Image> obj= Image.Get(c);
			
		
				
			if(obj!=null)
			{
				response.setStatus(200);
				ObjectMapper om=new ObjectMapper();
				out.print(om.writeValueAsString(obj));
				
			}
			else{
				response.setStatus(404);
				
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
		doGet(request, response);
	}

}
