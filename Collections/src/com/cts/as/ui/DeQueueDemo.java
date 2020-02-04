package com.cts.as.ui;



import java.util.Deque;
import java.util.LinkedList;

public class DeQueueDemo {

	public static void main(String[] args) {
		
		
		Deque<String> q = new LinkedList<String>();
		    
		q.add("pushpa");
	     q.add("rekha");
	      q.add("nandhini");
	     q.add("gayi"); 
	      
	      System.out.println("Elements present in Queue:"+q);
	      System.out.println("Removed element is: "+q.remove());
	      System.out.println("Head is : "+q.element());
	      System.out.println("peek() is : "+q.peek());
	      System.out.println("poll() is : "+q.poll());
	      System.out.println("Elements in Queue are :"+q);

	}

}
