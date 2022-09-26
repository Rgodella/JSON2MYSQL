package com.wipro.Assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {


		
		//TO FETCH THE DATA FROM THE TABLE AND CREATE A JSON FILE
		Fetch fetch=new Fetch();
		fetch.select();
		System.out.println("Completed successfully");	
		
		//	TO INSERT THE JSON INTO TABLE
		Connectivity1 con=new Connectivity1();
		con.insert1();
		System.out.println("Completed");
	}

}
