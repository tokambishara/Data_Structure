import java.util.LinkedList;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class PolynomialSolver
{
    static LinkedList A = new LinkedList();
    static LinkedList B = new LinkedList();
    static LinkedList C = new LinkedList();
    static LinkedList R = new LinkedList();
    public static void main(String[] args)
    {


            Scanner sc = new Scanner(System.in);
            while(sc.hasNext())
            {
                String fun = sc.nextLine();
                switch (fun)
                {
                    case "set":
                        char let = sc.nextLine().charAt(0);
                        int [][] ar2d = getCoeff();
                        setPolynomial(let,ar2d);
                        break;
                    case "add":
                        char One = sc.next().charAt(0);
                        char Two = sc.next().charAt(0);
                        int [] array = add(One,Two);
                        prntArray(array);
                        break;
                    case "eval":
                        char let2 = sc.nextLine().charAt(0);
                        int x = sc.nextInt();
                        System.out.println(eval(let2, x));
                        break;
                    case "clear":

                        char let3 = sc.nextLine().charAt(0);
                        if (let3 == 'A' || let3 == 'B' || let3 == 'C') {
                            clearPolynomial(let3);
                        } else {
                            System.out.println("Error");
                            System.exit(0);
                        }
                        break;
                    case "print":
                        char let4 = sc.nextLine().charAt(0);
                        if (let4 == 'A' || let4 == 'B' || let4 == 'C') {
                           System.out.println(print(let4));
                        } else {
                            System.out.println("Error");
                            System.exit(0);
                        }


                }

              //  System.out.println("Hello world!");
            }/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
    public static void prntArray(int [] array )
    {
        String polynomialString = "";
        for(int i = array.length - 1; i >= 0; i--)
        {
            if(i > 1)
            {
                if (array[i] == 1)
                    polynomialString +=  "x^" + i + "+";
                else
                    polynomialString += array[i] + "x^" + i + "+";
            }
            else if(i == 1)
            {
                if (array[i] == 1)
                    polynomialString += "x+";
                else
                    polynomialString += array[i] + "x+";
            }
            else
            {
                polynomialString += array[i];
            }
        }
        System.out.println(polynomialString);
    }

/////////////////// CLEAR ///////////////
    public static void clearPolynomial(char poly)
    {
        switch (poly) {
            case 'A':
                A.clear();
                System.out.println("[]");
                break;
            case 'B':
                B.clear();
                System.out.println("[]");
                break;
            case 'C':
                C.clear();
                System.out.println("[]");
                break;
            default:
                System.out.print("Error");
                break;
        }}
/////////////////// EVAL ///////////////
    public static int eval(char let, int x)
    {
        if(let == 'A')
        {
            int[] arr = new int[A.size()];
            for (int i=0 ; i< A.size();i++)
            { arr[i]=A.indexOf(i);}
            int value= arr[0];
            for ( int i= A.size() - 1 ; i>0 ;i--)
            {
                value = arr[i-1] + x*value;
            }
            return value;
        }
        else if(let == 'B')
        {
            int[] arr = new int[B.size()];
            for (int i=0 ; i< A.size();i++)
            { arr[i]=B.indexOf(i);}
            int value= arr[0];
            for ( int i= B.size() - 1 ; i>0 ;i--)
            {
                value = arr[i-1] + x*value;
            }
            return value;
        }
        else if(let == 'C')
        {
            int[] arr = new int[C.size()];
            for (int i=0 ; i< C.size();i++)
            { arr[i]=C.indexOf(i);}
            int value= arr[0];
            for ( int i= C.size() - 1 ; i>0 ;i--)
            {
                value = arr[i-1] + x*value;
            }
            return value;
        }
        return 0;
    }
////////////////////////////////////////////
    public static int[][] getCoeff()
    {
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[] s = sin.split(",");;
        int[][] coeff = new int[s.length][s.length];
            int j=0;
            for (int i = 0; i < s.length; i++)
            {
                coeff[j][i] = Integer.parseInt(s[i]);
            }
            j=1;
            for (int i = 0; i < s.length; i++)
            {
                coeff[j][i] = s.length - 1;
            }

        return coeff;
    }
/////////////////////// SET ///////////////////////
    public static void setPolynomial(char poly, int[][] terms)
    {
        if (poly == 'A') {
            A.clear();
            for (int i = 0; i < terms.length; i++) {
                A.add(terms[0][i]);
            }
            System.out.println("A=" + A);
        } else if (poly == 'B') {
            B.clear();
            for (int i = 0; i < terms.length; i++) {
                B.add(terms[0][i]);
            }            System.out.println("B=" + B);

        } else if (poly == 'C') {
            C.clear();
            for (int i = 0; i < terms.length; i++) {
                C.add(terms[0][i]);
            }
        }
        System.out.println("SET IS DONE");
    }
    /////////////////////PRINT///////////////
    public static String print(char poly)
    {
        StringBuilder sb = new StringBuilder();
        if (poly == 'A')
        {  for(int i= 0 ; i< A.size()-1;i++)
        {
            if(A.size()-1==1)
            {
                if ((int) A.get(i) == 1)
                    sb.append("x");
                else
                    sb.append(Math.abs((int) A.get(i))).append("x");
            }
            else
            {
                if ((int) A.get(i) == 1)
                    sb.append("x^").append(A.size()-1-i);
                else
                    sb.append(Math.abs((int) A.get(i))).append("x^").append(A.size()-1-i);
            }
            sb.append(getSign((int) A.get(i+1)));
        }
        sb.append(Math.abs(((int)A.get(A.size()-1))));
        return sb.toString();
        }
        else if (poly == 'B')
        {  for(int i= 0 ; i< B.size()-1;i++)
        {
            if(B.size()-1==1)
            {
                if ((int) B.get(i) == 1)
                    sb.append("x");
                else
                sb.append(Math.abs((int)B.get(i))).append("x");
            }
            else
            {
                if ((int) B.get(i) == 1)
                    sb.append("x^").append(B.size()-1-i);
                else
                sb.append(Math.abs(((int)B.get(i)))).append("x^").append(B.size()-1-i);
            }
            sb.append(getSign((int)B.get(i+1)));
        }
            sb.append(Math.abs(((int)B.get(A.size()-1))));
            return sb.toString();
        }
        if (poly == 'C')
        {  for(int i= 0 ; i< C.size()-1;i++)
        {
            if(C.size()-1==1)
            {
                if ((int)C.get(i) == 1)
                    sb.append("x");
               else
                sb.append(Math.abs((int)C.get(i))).append("x");
            }
            else
            {
                if ((int)C.get(i) == 1)
                     sb.append("x^").append(C.size()-1-i);
                else
                sb.append(Math.abs(((int)C.get(i)))).append("x^").append(C.size()-1-i);
            }
            sb.append(getSign((int)C.get(i+1)));
        }
            sb.append(Math.abs(((int)C.get(C.size()-1))));
            return sb.toString();
        }
        return null;
    }
    private static String getSign(double x)
    {
        if(x>=0)
            return "+";
        else
            return "-";
    }


    public float evaluatePolynomial(char poly, float value)
    {

        return 0;
    }

    public static int[] add(char One, char Two) {
        int n, n1, n2, n3;
        if (One == 'A') {
            if (Two == 'B') {
                n = Math.max(A.size() - 1, B.size() - 1);
                // System.out.println("n=" +n);
                int[] cffSum = new int[n + 1];
                for (int i = 0; i <= n; i++) {
                    if (i > A.size() - 1 && i <= B.size() - 1)
                        cffSum[i] =(int) B.get(i);
                    else if (i > B.size() - 1 && i <= A.size() - 1)
                        cffSum[i] = (int) A.get(i);
                    else
                        cffSum[i] = (int)B.get(i) + (int)A.get(i);
                }
                return cffSum;
            } else if (Two == 'C') {
                n1 = Math.max(A.size() - 1, C.size() - 1);
                // System.out.println("n=" +n);
                int[] cffSum = new int[n1 + 1];
                for (int i = 0; i <= n1; i++) {
                    if (i > A.size() - 1 && i <= C.size() - 1)
                        cffSum[i] = (int)C.get(i);
                    else if (i > C.size() - 1 && i <= A.size() - 1)
                        cffSum[i] = (int)A.get(i);
                    else
                        cffSum[i] = (int)C.get(i) + (int) A.get(i);
                }
                return cffSum;
            }
        } else if (One == 'B') {
            if (Two == 'A') {
                n2 = Math.max(A.size() - 1, B.size() - 1);
                // System.out.println("n=" +n);
                int[] cffSum = new int[n2 + 1];
                for (int i = 0; i <= n2; i++) {
                    if (i > A.size() - 1 && i <= B.size() - 1)
                        cffSum[i] =(int) B.get(i);
                    else if (i > B.size() - 1 && i <= A.size() - 1)
                        cffSum[i] = (int) A.get(i);
                    else
                        cffSum[i] = (int) B.get(i) + (int)A.get(i);
                }        return cffSum;

            } else if (Two == 'C') {
                n3 = Math.max(B.size() - 1, C.size() - 1);
                // System.out.println("n=" +n);
                int[] cffSum = new int[n3 + 1];
                for (int i = 0; i <= n3; i++) {
                    if (i > B.size() - 1 && i <= C.size() - 1)
                        cffSum[i] = (int) C.get(i);
                    else if (i > C.size() - 1 && i <= B.size() - 1)
                        cffSum[i] = (int) A.get(i);
                    else
                        cffSum[i] = (int) C.get(i) +(int) B.get(i);
                }
                return cffSum;
            }
        } else if (One == 'C') {
            if (Two == 'B') {
                n = Math.max(B.size() - 1, C.size() - 1);
                // System.out.println("n=" +n);
                int[] cffSum = new int[n + 1];
                for (int i = 0; i <= n; i++) {
                    if (i > B.size() - 1 && i <= C.size() - 1)
                        cffSum[i] = (int) C.get(i);
                    else if (i > C.size() - 1 && i <= B.size() - 1)
                        cffSum[i] =(int) B.get(i);
                    else
                        cffSum[i] =(int) C.get(i) +(int) B.get(i);
                }
                return cffSum;
            } else if (Two == 'A') {
                n1 = Math.max(A.size() - 1, C.size() - 1);
                // System.out.println("n=" +n);
                int[] cffSum = new int[n1 + 1];
                for (int i = 0; i <= n1; i++) {
                    if (i > A.size() - 1 && i <= C.size() - 1)
                        cffSum[i] =(int) C.get(i);
                    else if (i > C.size() - 1 && i <= A.size() - 1)
                        cffSum[i] =  (int)A.get(i);
                    else
                        cffSum[i] =(int) C.get(i) +(int) A.get(i);
                }
                return cffSum;
            }
        }

        return null;
    }
    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }


    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }
    public int degree;
    public int[][] terms;
}
