package com.cts.as.ui;


import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
        PriorityQueue<String> pQueue = new PriorityQueue<String>(); 
  
        
        pQueue.add("software"); 
        pQueue.add("hardware"); 
        pQueue.add("combination"); 
  
        
        System.out.println("Head value using peek function:"+ pQueue.peek()); 
  
       
        System.out.println("The queue elements:"); 
        Iterator<String> itr = pQueue.iterator(); 
        while (itr.hasNext()) 
            System.out.println(itr.next()); 
  
       
        pQueue.poll(); 
        System.out.println("After removing an element"
                           + "with poll function:"); 
        Iterator<String> itr2 = pQueue.iterator(); 
        while (itr2.hasNext()) 
            System.out.println(itr2.next()); 
  
        
        pQueue.remove("Java"); 
        System.out.println("after removing Java with"
                           + " remove function:"); 
        Iterator<String> itr3 = pQueue.iterator(); 
        while (itr3.hasNext()) 
            System.out.println(itr3.next()); 
  
        
        boolean b = pQueue.contains("C"); 
        System.out.println("Priority queue contains C "
                           + "or not?: " + b); 
  
       
        Object[] arr = pQueue.toArray(); 
        System.out.println("Value in array: "); 
        for (int i = 0; i < arr.length; i++) 
            System.out.println("Value: " + arr[i].toString()); 
    }

}


