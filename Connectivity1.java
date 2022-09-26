package com.wipro.Assignment;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mysql.cj.xdevapi.JsonArray;

//METHOD TO TRANSFER THE JSON TO TABLE USING JDBC
public class Connectivity1 {
	public void insert1() {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/output4.json"));
			System.out.println("jsonObject : " + jsonObject);
			JSONArray jsonArray = (JSONArray) jsonObject.get("Employees_data");
			//CODE TO ESTABLISH THE CONNECTION
			String url="jdbc:mysql://localhost:3306/wipro";
	        String username="root";
	        String pwd="Zinda$1234";
	        Class.forName("com.mysql.jdbc.Driver");
	        Connection con=DriverManager.getConnection(url,username,pwd);
	        PreparedStatement pstmt = con.prepareStatement("INSERT INTO newemployee values (?, ?, ? )");
	    
	        //CONVERT EACH RECORD AS OBJECT
	    for(Object object : jsonArray) {
	    	
	    	//TYPE CASE THE OBJECT TO JSONOBJECT
            JSONObject record = (JSONObject) object;
            System.out.println(record.get("E_id"));
            String s=(String)record.get("E_id");
          String[] atr=s.split(",");
          JSONArray arr=new JSONArray();
   
          System.out.println(atr);
          for(int i=0;i<atr.length;i++) {
        	 // System.out.println(atr[i]);
        	  arr.add(atr[i]);
          }
          System.out.println(arr);
          for(int i=0;i<arr.size();i++) {
            	int id=Integer.parseInt(String.valueOf(arr.get(i)));
            	String first_name = (String) record.get("E_name");
            	int age = Integer.parseInt((String.valueOf(record.get("E_age"))));
                   	pstmt.setInt(1, id);
                   	pstmt.setString(2, first_name);
                   	pstmt.setInt(3, age);
                   	pstmt.executeUpdate();
                   	}
            System.out.println("Records inserted.....");
	      } 
		}catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      } catch (Exception e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	}
	}

}
