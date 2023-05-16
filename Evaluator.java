import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Stack {
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
}

interface IExpressionEvaluator {
  
/**
* Takes a symbolic/numeric infix expression as input and converts it to
* postfix notation. There is no assumption on spaces between terms or the
* length of the term (e.g., two digits symbolic or numeric term)
*
* @param expression infix expression
* @return postfix expression
*/
  
public String infixToPostfix(String expression);
  
  
/**
* Evaluate a postfix numeric expression, with a single space separator
* @param expression postfix expression
* @return the expression evaluated value
*/
  
public int evaluate(String expression);

}


public class Evaluator implements IExpressionEvaluator {
    
    static int a;
    static int b;
    static int c;
    static int s;
    static String x;
    public String infixToPostfix(String expression) {
        Stack s = new Stack();
        String out = "";
        for(int i=0;i<expression.length();i++) {
            if(expression.charAt(i)=='(') {
                s.push(expression.charAt(i));
            }
            else if(expression.charAt(i) == ')' ) {
                while((char)(s.peek()) != '(') {
                    out += (char)(s.pop());
                }
                s.pop();
            }
            else if (expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='/'||expression.charAt(i)=='*'||expression.charAt(i)=='^') {
                if(expression.charAt(i+1)=='-'&& expression.charAt(i)=='-') {
                    if(s.isEmpty()) {
                        s.push(expression.charAt(i));
                    }
                    else {
                        while(!(s.isEmpty()) && piriority(expression.charAt(i))<=piriority((char) s.peek())) {        
                                out += (char)(s.pop());
                        }
                        s.push(expression.charAt(i));
                    }    
                }
                else {
                    if(s.isEmpty()) {
                        s.push(expression.charAt(i));
                    }
                    else {
                        while(!(s.isEmpty()) && piriority(expression.charAt(i))<=piriority((char) s.peek())) {        
                                out += (char)(s.pop());
                        }
                        s.push(expression.charAt(i));
                    }
                }
            }
            else if(expression.charAt(i)==' ') {
                continue;
            }
            else {
                out += expression.charAt(i);
            }
        }
        while(!(s.isEmpty())) {
            if((char)(s.peek())=='(')
                throw new RuntimeException("Error");
            out += s.pop();
        }
        return out;
    }
    public int piriority(char a) {
        if(a=='+'||a=='-')
            return 1;
        else if(a=='*'||a=='/')
            return 2;
        else if(a=='^')
            return 3;
        else
            return 0;
    }
    int operation(char z,int y,int x) {
        int result = 0;
        int operand1 = y,operand2 = x;          
            if(z == '+')
                result = operand2+operand1;
            else if(z == '*')
                result = operand2*operand1;
            else if(z == '-')
                result = operand2-operand1;
            else if(z == '/')
                result = operand2/operand1;
            else if(z == '^')
                result=(int)Math.pow(operand2, operand1) ;
            else
                throw new RuntimeException("Error");
        return result;
        
    }
    public int evaluate(String expression) {
        if(expression.length()==1) {
            if(expression.charAt(0)=='a')
                s=a;
            if(expression.charAt(0)=='b')
                s=b;
            if(expression.charAt(0)=='c')
                s=c;
            return s;
        }
        else {
            Stack list=new Stack();
            for(int i=0;i<expression.length();i++) {
                if(expression.charAt(i)=='+'||expression.charAt(i)=='-'||expression.charAt(i)=='*'||expression.charAt(i)=='/'||expression.charAt(i)=='^') {
                    int x1= (int) list.pop();
                    if(expression.charAt(i)=='-'&&list.top==null) {
                        if(x1==a)
                            s=-a;
                        else if(x1==b)
                            s=-b;
                        else if(x1==c)
                            s=-c;
                        else if(x1==s)
                            s=-s;
                        list.push(s);
                    }
                    else {
                    int x2=(int) list.pop();
                    s=operation(expression.charAt(i),x1,x2);
                    list.push(s);
                    }
                }
                else {
                    if(expression.charAt(i)=='a')
                        list.push(a);
                    else if(expression.charAt(i)=='b')
                        list.push(b);
                    else if(expression.charAt(i)=='c')
                        list.push(c);
                }    
            }
                return s;
        }
    }
    public String minusRemove(String str) {
        String s="";
        for(int i=0;i<str.length();i++) {
            if(str.charAt(i)=='-' && str.charAt(i+1)=='-') {
                i++;
                s+="+";
            }else if(str.charAt(i)=='+'&&(str.charAt(i+1)=='-'&&str.charAt(i+2)=='-')) {
                i=i+2;
                s+="+";
            }else if(str.charAt(i)=='-'&&(str.charAt(i+1)=='-'&&str.charAt(i+2)=='-')) {
                i=i+2;
                s+="-";
            }
            else if(str.charAt(i)=='*'&&(str.charAt(i+1)=='-'&&str.charAt(i+2)=='-')) {
                i=i+2;
                s+="*";
            }else if(str.charAt(i)=='/'&&(str.charAt(i+1)=='-'&&str.charAt(i+2)=='-')) {
                i=i+2;
                s+="/";
            }else if(str.charAt(i)=='^'&&(str.charAt(i+1)=='-'&&str.charAt(i+2)=='-')) {
                i=i+2;
                s+="^";
            }
            else
                s+=str.charAt(i);
        }
        if(s.charAt(0)=='+') {
            s=s.substring(1);
        }
        
        return s;
    }
  
    public static void main(String[] args) {
        try{
                Scanner input = new Scanner(System.in);
                Evaluator eval = new Evaluator();
                String convertion = input.nextLine();
                String u = eval.minusRemove(convertion);
                String as = input.nextLine();
                String bs = input.nextLine();
                String cs = input.nextLine();
                x = eval.infixToPostfix(u);
                for(int i=0; i<convertion.length(); i++) {
                    if(convertion.charAt(i)=='='||convertion.charAt(0)=='+') {
                        System.out.println("Error");
                        return ;
                    }
                    if((convertion.charAt(i)=='a'||convertion.charAt(i)=='b'||convertion.charAt(i)=='c')&&i<convertion.length()-1) {
                        if(convertion.charAt(i+1)=='a'||convertion.charAt(i+1)=='b'||convertion.charAt(i+1)=='c') {
                            System.out.println("Error");
                            return ;
                        }
                    }
                }
                String ax="";
                String bx="";
                String cx="";
                if(as.charAt(0)!='a'||bs.charAt(0)!='b'||cs.charAt(0)!='c'||as.charAt(1)!='='||bs.charAt(1)!='='||cs.charAt(1)!='=') {
                    System.out.println("Error");
                    return ;
                }
                
                for(int i=2; i<as.length(); i++) {
                    ax += as.charAt(i);
                }
                a = Integer.parseInt(ax);
                for(int i=2; i<bs.length(); i++) {
                    bx += bs.charAt(i);
                }
                b = Integer.parseInt(bx);
                for(int i=2; i<cs.length(); i++) {
                    cx += cs.charAt(i);
                }
                c = Integer.parseInt(cx);        
                int out = eval.evaluate(x);
                System.out.println(x);
                System.out.println(out);
            }
            catch(Exception e) {
                System.out.println("Error");
            }
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
    }
}