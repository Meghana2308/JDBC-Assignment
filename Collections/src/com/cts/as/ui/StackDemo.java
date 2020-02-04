package com.cts.as.ui;


import java.util.Stack;

public class StackDemo {

	public static void main(String[] args) {
		 Stack<String> cinema = new Stack<>();

	       
		 cinema .push("bahubali");
		 cinema.push("A aa");
		 cinema.push("sankranthi");
		
	        System.out.println("Stack => " +  cinema);
	        System.out.println();

	       
	        String gameAtTop =  cinema.pop();  
	        System.out.println("Stack.pop() => " + gameAtTop);
	        System.out.println("Current Stack => " +  cinema);
	        System.out.println();

	       
	        gameAtTop =  cinema.peek();
	        System.out.println("Stack.peek() => " + gameAtTop);
	        System.out.println("Current Stack => " +  cinema);

	}

}
