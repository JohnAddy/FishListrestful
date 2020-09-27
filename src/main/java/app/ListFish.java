package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.utils.SystemProperty;
import futil.Add;
import conn.Connections;
import data.Fish;

@WebServlet(
    name = "ListFish",
    urlPatterns = {"/hello"}
)
public class ListFish extends HttpServlet {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	  doGet(request, response);}
  
  public void doGet (HttpServletRequest request, HttpServletResponse response)
		  throws IOException {
	    response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out=response.getWriter();
	    
	    String breed = request.getParameter("breed");
	    String weight = request.getParameter("weight");
	    String length = request.getParameter("length");
	    String city = request.getParameter("city");
	    String water = request.getParameter("water");
	    
	    Fish fish = new Fish ();
	    fish.setBreed(breed);
	    fish.setWeight(weight);
	    fish.setLength(length);
	    fish.setCity(city);
	    fish.setWater(water);
	    Add.addfish(fish);
//	    out.println("<tr><td>"+fish.getId()+"<td>"+fish.getBreed()+"<td>"+fish.getWeight()+"<td>"+fish.getLength()+"<td>"+fish.getCity()+"<td>"+fish.getWater());

	     
	    ArrayList<Fish> fishlist=new ArrayList<>();
		util.HTML.printStart(out);
	    Connection conn=null;
	    
	    if (SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {  
	    	out.println("Production version");
	    	try {
				conn=Connections.getProductionConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    else {    
	    	out.println("Development version");
			try {
				conn=Connections.getDevConnection();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    try {
	    	if (conn!=null) {
				Statement stmt=conn.createStatement();
//				ResultSet RS=stmt.executeQuery("select * from data");
				ResultSet RS=stmt.executeQuery("select * from fish");
				
				while (RS.next()) {
					Fish f=new Fish();
					f.setId(RS.getInt("id"));
					f.setBreed(RS.getString("breed"));
					f.setWeight(RS.getString("weight"));
					f.setLength(RS.getFloat("length"));
					f.setCity(RS.getString("city"));
					f.setWater(RS.getString("water"));
					fishlist.add(f);
				}
				conn.close();
				util.HTML.printTable(out, fishlist);
	    	}
	    	else {
	    		out.println("No connection to database!");
	    	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		util.HTML.printEnd(out);
  }
}