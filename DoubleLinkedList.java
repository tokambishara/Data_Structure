interface ILinkedList {
    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all of the elements from this list.
     */
    public void clear();
    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();
    /**
     * @param fromIndex
     * @param toIndex
     * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
     */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
     * @param o
     * @return true if this list contains an element with the same value as the specified element.
     */
    public boolean contains(Object o);
}

public class DoubleLinkedList implements ILinkedList{

    public class SNode{
        Object data;
        SNode next;
        SNode prev;
    }
    SNode head = null;
    SNode tail = null;
    int size = 0;
    public void add(Object element){
        if(isEmpty()){
            SNode tmp = new SNode();
            tmp.data = (int) element ;
            //head.prev=null;
            tail = head;
            head = tmp;
            tail = tmp;
            // size++;
        }
        else{
            SNode tmp = new SNode();
            tmp.data = (int) element;
            tail.next = tmp;
            tail = tmp;
            // size++;
        }
    }
    public void print(){
        SNode h = head ;
        while (h != null){
            if (h.next != null)
                System.out.print(h.data + ", ");
            else
                System.out.print(h.data);
            h.prev=h;
            h = h.next;
        }
    }
    public void clear(){
        head = null;
        tail= null;
        size = 0;
        print();
    }
    public boolean contains(Object element){
        SNode h = head;
        while (h != null && !isEmpty()){
            if (h.data == element){
                return true;
            }
            h.prev=h;
            h= h.next;
        }
        return false;
    }
    public boolean isEmpty(){
        if(head == null) {return true;}
        else{return false;}
    }
    public Object get(int index){
        SNode ptr=head;
        for(int i=0 ; i< index ; i++)
        {
            ptr.prev=ptr;
            ptr=ptr.next;
        }
        return ptr.data;
    }

    public int size(){
        int count = 0;
        if (isEmpty()) {
            count = 0;
        } else {
            SNode current = head;
            while (current != null) {
                count++;
                current.prev=current;
                current = current.next;
            }
        }
        return count;
    }

    public void remove(int index){
        SNode temp = head;
        SNode nll = null;
        if (index == 0)
        {
            if(size()==1){
                System.out.print("");
            }
            else{
                head = head.next;
                head.prev=null;
                //head.next=null;
                print();}
        }
        else{
            for (int i=0;i<index; i++){
                nll = temp;
                temp.prev=temp;
                temp = temp.next;
            }
            nll.next = temp.next;
            nll.prev = temp.prev;
            temp.next = null;
            print();}
    }
    public void insertAtStart(Object data)
    {
        SNode node = new SNode();
        node.data = data;
        node.prev = null;
        node.next = head;
        head = node;
    }
    public void add(int index, Object data){
        SNode node = new SNode();
        node.data = data;
        node.next = null;
        if (index > size()){
            System.out.print("Error");
        }

        if(index==0)
        {
            insertAtStart(data);
        }
        else{
            SNode n = head;
            for(int i=0;i<index-1;i++)
            {
                n.prev=n;
                n = n.next;
            }
            node.next = n.next;
            n.next = node;
        }
    }

    public DoubleLinkedList sublist(int fromIndex, int toIndex){
        SNode current = head;
        SNode t = null;
        DoubleLinkedList listout = new DoubleLinkedList();
        int count = 0;
        if (head == null)
        {
            System.out.println("Error");
            System.exit(0);
        }
        while (count < fromIndex) {
            current.prev=current;
            current = current.next;
            count++;
        }
        while (count <= toIndex) {
            t = current;
            t.data = current.data;
            listout.add(t.data);
            current.prev=current;
            current = current.next;
            count++;
        }

        return listout;
    }


    public void set(int index, Object element){
        SNode h = head;
        int count = 0;
        if (isEmpty()) {
            System.out.print("Error");
        } else if (index > size() - 1) {
            System.out.print("Error");
        } else {
            while (count < index) {
                h.prev=h;
                h = h.next;
                count++;
            }
            h.data = (int) element;
        }

    }


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        DoubleLinkedList LL = new DoubleLinkedList ();
        Scanner sc = new Scanner(System.in);
        String fun;
        String sin = sc.nextLine().replaceAll("\\[|\\]", "");
        String[]  s = sin.split(", " );
        int i = 0;
        if (s.length == 1 && s[0].isEmpty() ) {
            LL.clear();
        }
        else{
            while (i <= s.length - 1){
                LL.add(Integer.parseInt(s[i]));
                i++;
            }
        }
        fun = sc.nextLine();
        switch (fun){
            case "add" :
                int el0 = sc.nextInt();

                System.out.print("[");
                // for(i = 0; i < s.length; ++i) {
                //     System.out.print(s[i]);
                //     if(i != s.length - 1)
                //         System.out.print(", ");
                // }
                LL.add(el0);
                LL.print();
                System.out.print("]");
                break;
            case "contains":
                int el1 = sc.nextInt();
                if(LL.contains(el1))
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "isEmpty" :
                if(LL.isEmpty())
                    System.out.println("True");
                else
                    System.out.println("False");
                break;
            case "clear" :
                System.out.print("[");
                LL.clear();
                System.out.print("]");
                break;
            case "get" :
                int index = sc.nextInt();
                if(LL.isEmpty()||index >LL.size()- 1||index<0)
                    System.out.println("Error");
                else
                    System.out.print(LL.get(index));
                break;
            case "remove" :
                int index1 = sc.nextInt();
                if (index1 >= LL.size() || index1<0 || LL.isEmpty())
                    System.out.println("Error");
                else{
                    System.out.print("[");
                    LL.remove(index1);
                    System.out.print("]");
                }
                break;
            case "size" :
                System.out.println(LL.size());
                break;
            case "addToIndex" :
                int index2 = sc.nextInt();
                Object el2 = sc.nextInt();
                if (index2>LL.size()-1||index2<0){
                    System.out.print("Error");
                    break;
                }
                System.out.print("[");
                LL.add(index2,el2);
                LL.print();
                System.out.print("]");
                break;
            case "set" :
                int index3 = sc.nextInt();
                int el3 = sc.nextInt();
                if (index3>LL.size()-1||index3<0){
                    System.out.print("Error");
                    break;
                }
                System.out.print("[");
                LL.set(index3,el3);
                LL.print();
                System.out.print("]");
                break;
            case "sublist" :
                int in=sc.nextInt();
                int it=sc.nextInt();
                DoubleLinkedList sub = new DoubleLinkedList();
                if(LL.isEmpty()||in>LL.size()- 1 || it>LL.size()- 1||in>it||in<0||it<0 )
                {System.out.print("Error");
                    break;}
                else{
                    sub = LL.sublist(in,it);
                    System.out.print("[");
                    sub.print();
                    System.out.print("]");
                    break;}

        }
    }
}