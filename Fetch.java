package com.wipro.Assignment;

import java.io.FileWriter;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//METHOD TO RETRIEVE THE JSON FROM THE TABLE
public class Fetch {
	public void select() {
		try {
			JSONObject jsonObject = new JSONObject();
			JSONArray array = new JSONArray();
			//CODE TO ESTABLISH THE CONNECTION
			String url="jdbc:mysql://localhost:3306/wipro";
	        String username="root";
	        String pwd="Zinda$1234";
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con=DriverManager.getConnection(url,username,pwd);
	        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
	    	    ResultSet.CONCUR_READ_ONLY);
	        ResultSet rs=stmt.executeQuery("Select * from newEmployee");
	        String curr_name;
	        JSONObject record = new JSONObject();
	        JSONArray recordArray = new JSONArray();
	        while(rs.next()) {
	        	JSONObject temprecord = new JSONObject();
	        	JSONArray idarray = new JSONArray();
	        	for (int i = 0; i < 3; i++) {
	        		idarray.add(rs.getInt("E_id"));
	        		rs.next();
	        	}
			rs.previous();
			System.out.println(idarray + "arr");
			//CONVERT ARRAY TO STRING 
			String s=idarray.toString();
			s=s.substring(1, s.length()-1);
			temprecord.put("E_id", s);
			temprecord.put("E_name", rs.getString("E_name"));
			temprecord.put("E_age", rs.getInt("E_age"));
			recordArray.add(temprecord);
	    	
	    }
	    System.out.println("recordArray : " + recordArray);
	    jsonObject.put("Employees_data", recordArray);
	    FileWriter file = new FileWriter("src/output4.json");
	    //WRITE JSON OBJECT TO FILE
        file.write(jsonObject.toJSONString());
        file.close();
	    
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("NULL");
		}
	}

}
