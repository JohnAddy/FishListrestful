package futil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.appengine.api.utils.SystemProperty;


import conn.Connections;
import data.Fish;

public class Add {

  public static void addfish (Fish fish){
	  String sql = "insert into fish(breed, weight, length, city, water) values(?,?,?,?,?)";
	  Connection conn=null;
	 try {
		 if(SystemProperty.environment.value() ==SystemProperty.Environment.Value.Production) {
			 conn = Connections.getProductionConnection();
	 }else {
		 conn = Connections.getDevConnection();
	 }
		 }catch (Exception e) {
		 //TODO Auto-generated catch block
		 e.printStackTrace();
	 }
	 PreparedStatement pstmt;
	 try {
		 pstmt = conn.prepareStatement(sql);
		 pstmt.setString(1,fish.getBreed());
		 pstmt.setFloat(2,fish.getWeight());
		 pstmt.setFloat(3, fish.getLength());
		 pstmt.setString(4,fish.getCity());
		 pstmt.setString(5,fish.getWater());
		 
		 pstmt.execute();
		 }catch (SQLException e) {
			 e.printStackTrace ();
		 }
	 
   }
  
}