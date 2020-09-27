package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.google.appengine.api.utils.SystemProperty;
import java.sql.Statement;

import conn.Connections;
import data.Fish;

@Path("/fishservice")

public class FishService {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/addfish")
	@Consumes("application/x-www-form-urlencoded")

	public Fish addFish(@FormParam("breed") String breed, @FormParam("weight") float weight,
			@FormParam("length") float length, @FormParam("city") String city, @FormParam("water") String water) {
		Fish fish = new Fish();
		fish.setBreed(breed);
		fish.setWeight(weight);
		fish.setLength(length);
		fish.setCity(city);
		fish.setWater(water);
		String sql = "insert into fish(breed, weight, length, city, water) values(?,?,?,?,?)";

		Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fish.getBreed());
			// pstmt.setString(1, breed);
			pstmt.setFloat(2, fish.getWeight());
			// pstmt.setFloat(2, weight);
			pstmt.setFloat(3, fish.getLength());
			// pstmt.setFloat(2, length);
			pstmt.setString(4, fish.getCity());
			// pstmt.setString(1, city);
			pstmt.setString(5, fish.getWater());
			// pstmt.setString(1, water);

			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fish;
	}

	/*
	 * This method reveives values breed and weight from an html form which sends a
	 * POST type request.
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Consumes(MediaType.APPLICATION_JSON) // Method receives object as a JSON string
	@Path("/addjsonfish")
	public Fish receiveJsonFish(Fish fish) {
		String sql = "insert into fish(breed, weight, length, city, water) values(?,?,?,?,?)";

		Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fish.getBreed());
			pstmt.setFloat(2, fish.getWeight());
			pstmt.setFloat(3, fish.getLength());
			pstmt.setString(4, fish.getCity());
			pstmt.setString(5, fish.getWater());

			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//					e.printStackTrace();
			}
		}

		fish.setBreed(fish.getBreed() + " modified");
		return fish;
	}

//		@DELETE
	@GET
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
//		@Consumes(MediaType.APPLICATION_JSON)//Method receives object as a JSON string
	@Path("/deletefish/{p1}")
	public Fish deleteFish(@PathParam("p1") int id) {
		String sql = "delete from fish where id=?";

		Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Fish f = new Fish();
		f.setId(100);
		f.setBreed("FishBreed");
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//					e.printStackTrace();
			}
		}

		return f;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON) // Method returns object as a JSON string
	@Path("/getAll")
	public ArrayList<Fish> getAllFish() {
		String sql = "select * from fish";
		ResultSet RS = null;
		ArrayList<Fish> list = new ArrayList<>();
		Connection conn = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				conn = Connections.getProductionConnection();
			} else {
				conn = Connections.getDevConnection();
			}
			Statement stmt;
			stmt = conn.createStatement();
			RS = stmt.executeQuery(sql);
			while (RS.next()) {
				Fish f = new Fish();
				f.setId(RS.getInt("id"));
				f.setBreed(RS.getString("breed"));
				f.setWeight(RS.getString("weight"));
				f.setLength(RS.getString("length"));
				f.setCity(RS.getString("city"));
				f.setWater(RS.getString("water"));
				
				list.add(f);
			}
				conn.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
