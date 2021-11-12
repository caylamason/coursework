/*
Cayla Mason
edoras acct cssc0973
CS 310
Shawn Healey
TTh section

Lab 2
Due 2019-10-21
 */

import java.util.LinkedList;

public class MyQueue {

   private LinkedList<Integer> list = new LinkedList<>();

   public MyQueue(){}

   public int size() {
       return list.size();
   }

   public boolean isEmpty() {
       return list.isEmpty();
   }

   public void enqueue(Integer element) {
       list.addLast(element);
   }

   public int dequeue () {
       return list.poll();
   }

   public int peek() {
       return list.peek();
   }

   //I don't want to use this because it will change all values in the queue, rather than in blocks of 6 nodes
   public void normalize(){

       int tare = list.peekFirst();
       for (int i = 0; i < list.size(); i++){
           list.set(i, (list.get(i) - tare));
       }
   }



}
