package com.cts.jmd.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StatementDemo {

	public static void main(String[] args) {
		try (
				Scanner scan = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr","root","welcome");
			) {
			Statement st = con.createStatement();
			
			String jobId, jobTitle, minSalary, maxSalary;
			
			System.out.print("Enter job id : ");
			jobId = scan.next();
			System.out.print("Enter job title : ");
			jobTitle = scan.next();
			System.out.println("Enter min. salary : ");
			minSalary = scan.next();
			System.out.println("Enter max. salary : ");
			maxSalary = scan.next();
			
			String sql = 
			"insert into jobs values (" + "'" + jobId + "'" + "," +"'" + jobTitle + "'" + "," + minSalary + "," + maxSalary + ")";
			
			
			st.executeUpdate(sql);

			System.out.println("Row Inserted.");
		} catch (SQLException e) {
			System.err.println("Could not insert");
			e.printStackTrace();
		} 
	}

}