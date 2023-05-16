import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IStack {
  
  /*** Removes the element at the top of stack and returnsthat element.
  * @return top of stack element, or through exception if empty
  */
  
  public Object pop();
  
  /*** Get the element at the top of stack without removing it from stack.
  * @return top of stack element, or through exception if empty
  */
  
  public Object peek();
  
  /*** Pushes an item onto the top of this stack.
  * @param object to insert*
  */
  
  public void push(Object element);
  
  /*** Tests if this stack is empty
  * @return true if stack empty
  */
  public boolean isEmpty();
  
  public int size();
}


public class MyStack implements IStack {
    class Node {
        Object data;
        Node next;
    }
    Node top;
    
    public Object pop() {
        if(top == null)
            throw new RuntimeException("Error");
        else {
        Node x = top;
        top = x.next;
        return x.data;
        }
    }
    
    public Object peek() {
        if(top==null)
            throw new RuntimeException("Error");
        else
            return top.data;
        
    }
    
    public void push(Object element) {
        Node node=new Node();
        node.data=element;
        if (top==null) {
            top=node;
        }
        else {
            node.next=top;
            top=node;
        }
    }
    
    public boolean isEmpty() {
        if(top==null)
            return true;
        else
            return false;
    }
    
    public int size() {
        int count=0;
        Node x=top;
        while(x!=null) {
            count++;
            x=x.next;
        }
        return count;
    }
    
    public void display() {
        Node x=top;
        if(top==null) {
            System.out.printf("[]");
        }
        else {
            System.out.printf("[");
            while(x.next!= null) {
                System.out.printf("%d, ",x.data);
                x=x.next;
            }
            System.out.printf("%d",x.data);
            System.out.printf("]");
        }
    }
    
    //@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        MyStack stack=new MyStack();
        try {
            Scanner input=new Scanner(System.in);
            String s = input.nextLine().replaceAll("\\[|\\]", "");
            String[] sin=s.split(", ");
            if(sin.length==1&&sin[0].isEmpty()) {
            }
            else {
                for(int i=sin.length-1;i>=0;i--) {
                    stack.push(Integer.parseInt(sin[i]));
                }
            }
        
            String instruction=input.nextLine();
            if(instruction.equals("push")) {
                int x = input.nextInt();
                stack.push(x);
                stack.display();
            }
            else if(instruction.equals("peek")) {
                int x = (int) stack.peek();
                System.out.print(x );
            }
            else if(instruction.equals("pop")) {
                stack.pop();
                stack.display();
            }
            else if(instruction.equals("size")) {
                int x = stack.size();
                System.out.print(x);
            }
            else if(instruction.equals("isEmpty")) {
                boolean x = stack.isEmpty();
                if(x)
                    System.out.print("True");
                else
                    System.out.print("False");
            
            }
            else {
                System.out.print("Error");
            }
        }
        catch(Exception e) {
            System.out.print("Error");
        }  
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}