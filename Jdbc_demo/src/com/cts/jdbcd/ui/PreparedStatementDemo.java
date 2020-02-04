package com.cts.jdbcd.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo {

	public static void main(String[] args) {
		try (
				Scanner scan = new Scanner(System.in);
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr","root","welcome");
			) {
		
		
			String jobId, jobTitle, minSalary, maxSalary;
			
			System.out.print("Enter job id : ");
			jobId = scan.next();
			System.out.print("Enter job title : ");
			jobTitle = scan.next();
			System.out.println("Enter min. salary : ");
			minSalary = scan.next();
			System.out.println("Enter max. salary : ");
			maxSalary = scan.next();
			
			String sql = "insert into jobs values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, jobId);
			ps.setString(2, jobTitle);
			ps.setString(3, minSalary);
			ps.setString(4, maxSalary);
			ps.executeUpdate();

			System.out.println("Row Inserted.");
		} catch (SQLException e) {
			System.err.println("Could not insert");
			e.printStackTrace();
		} 
	}

}