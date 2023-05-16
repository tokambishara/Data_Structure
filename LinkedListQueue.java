import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface IQueue
{
    /*** Inserts an item at the queue front.*/
    public void enqueue(Object item);
    /*** Removes the object at the queue rear and returnsit.*/
    public Object dequeue();
    /*** Tests if this queue is empty.*/
    public boolean isEmpty();
    /*** Returns the number of elements in the queue*/
    public int size();
}

public class LinkedListQueue implements IQueue {
    public class Node
    {
        int data;
        Node next;
    }
    Node rear = null;
    Node front = null;
    int size = 0;


    public void add(int element)
    {
        Node node = new Node();
        node.data = element;
        if (front == null)
        {
            front = node;
            rear = node;
        }
        else {
            rear.next = node;
            rear = node;
        }
        size += 1;
    }
    public void print()
    {
        Node h = front;
        while (h != null){
            if (h.next != null)
                System.out.print(h.data + ", ");
            else
                System.out.print(h.data);
            h = h.next;
        }
    }

    @Override
    public void enqueue(Object item)
    {
        Node node = new Node();
        node.data = (int)item;
        if (front == null)
        {
            // initialize both front and rear
            front = node;
            rear = node;
        }
        else {
            node.next = front;
            front = node;
        }
        size++;
        print();
    }

    @Override
    public Object dequeue()
    {
        if (isEmpty())
            System.out.println("Error");
        else {
            Node temp = front;
            Node nll = null;
            for (int i = 0; i < size - 1; i++) {
                nll = temp;
                // temp.prev=temp;
                temp = temp.next;
            }
            nll.next = temp.next;
            temp.next = null;
            size --;
            System.out.print("[");
            print();
            System.out.print("]");
        }
        System.exit(0);
        return 0;
    }

    public  boolean isEmpty()
    {
        return rear == null && front == null;
    }

    public int size()
    {
        return size;
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        LinkedListQueue LQ = new LinkedListQueue ();
        Scanner sc = new Scanner(System.in);
        String fun;
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[]  s = sin.split(", " );
        int i = 0 ;
            while (i <= s.length -1)
            {
                LQ.add(Integer.parseInt(s[i]));
                i++;
            }
        fun = sc.nextLine();
        switch (fun){
            case "isEmpty" :
                if(LQ.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;

            case "size" :
                System.out.println(LQ.size());
                break;

            case "enqueue" :
                int el = sc.nextInt();
                //LQ.enqueue(el);
                System.out.print("[");
                //System.out.print(LQ);
                LQ.enqueue(el);
                System.out.print("]");
                break;

            case "dequeue" :
                System.out.print(LQ.dequeue());
                break;
            default:
                System.out.println("Error");

        }

    }
}