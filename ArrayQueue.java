import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue
{

    public void enqueue(int item);
    public int dequeue();
    public boolean isEmpty();
    public int size();

}


public class ArrayQueue implements IQueue
{
    static private int[] queueArray;
    static private int front , rear;
    static private int currentSize;

    static Node head; // head of list
    static Node tail;
    public int count =0 ;

    private static class Node
    {
        int data;
        Node next;

        Node(int data)
        {
            this.data = data;
            next = null ;
        }
    }

    public ArrayQueue()
    {
        this.queueArray = new int[1000];
        front = rear = -1;
        currentSize = 0;
    }

    public static void printQueue(int arr[])
    {
        System.out.print("[");

        int i;
        for (i = front; i < rear; i++)
        {
            System.out.printf(" %d <-- ", queueArray[i]);
        }
        System.out.print("]");
        return;
    }


    public void enqueue(int element)
    {
        int x=size()+1;
        int[] enq = new int[x];
        enq[0]=element;
        for(int i = 0; i <size(); i++){
            enq[i+1]=queueArray[i];
        }
        System.out.print("[");
        for(int i =0 ; i<x;i++){
            if(i==x-1){
                System.out.print(enq[i]);
            }
            else{
                System.out.print(enq[i]+", ");}

        }
        System.out.print("]");
    }

    public boolean isEmpty()
    {
        Boolean empty;
        if(currentSize != 0)
        {
            empty = true ;
            System.out.print("True");
        }

        else
        {
            empty = false ;
            System.out.print("False");
        }
        return(empty);
    }
    public void show(){
        for (int i=0; i<size();i++){
            System.out.print(queueArray[i] + " " );
        }
    }

    public int dequeue()
    {
        if (size()==0)
            System.out.print("Error");
        else
        {

        int x=size()-1;
        int[] enq = new int[x];
        for(int i = 0; i <size()-1; i++){
            enq[i]=queueArray[i];
        }
        System.out.print("[");
        for(int i =0 ; i<x;i++){
            if(i==x-1){
                System.out.print(enq[i]);
            }
            else{
                System.out.print(enq[i]+", ");}
        }
        System.out.print("]");
        }
        return 0;

    }


    public int size()
    {
        int size = queueArray.length ;

        return(size);
    }



    public static void main(String[] args)
    {
        ArrayQueue queue = new ArrayQueue() ;
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;

        int[] arr = new int[s.length];

        if (s.length == 1 && s[0].isEmpty())
        {
            arr = new int[]{};
        }
        else
        {
            for(int i = 0; i < s.length; ++i)
                arr[i] = Integer.parseInt(s[i]);
        }

        queueArray = arr ;
        String process = sc.nextLine();

        switch(process)
        {
            case "enqueue":
                int elem = sc.nextInt();
                try{
                    queue.enqueue(elem);
                    // queue.show();
                }
                catch(RuntimeException e)
                {
                    System.out.print("Error");
                }

                break;

            case "isEmpty":
                queue.isEmpty();
                break;

            case "size":
                System.out.print(queue.size());
                break;

            case "dequeue":
                queue.dequeue();
                break;

            default:
                System.out.print("Error");
        }
    }
}