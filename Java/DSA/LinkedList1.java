
public class LinkedList1 {
    
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;
     
    public void addFirst(int data) { //O(1)
        //step 1 = create new node
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }

        //step 2 = newNode ka next -> head
        newNode.next = head;
        //step 3 = head is  the newNode
        head = newNode;
    }

    public void addLast(int data) { //O(1)
        //step 1 = Create new node
        Node newNode = new Node(data);
        size++;
        if(head==null) {
            head = tail = newNode;
        }

        //step 2 = previous tail ka next -> newNode
        tail.next = newNode;
        //step 3 = newNode will be the new tail
        tail = newNode;
    }

    public void addMiddle(int idx,int data) {
        if(idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i = 0;
        while (i < idx-1) {
            temp = temp.next;
            i++;
        }
        newNode.next =  temp.next;
        temp.next = newNode;

    }

    public void printLL() {
        if(head == null) {
            System.out.println("Empty Linked List");
        }
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public int removeFirst() {
        if(size==0) {
            System.out.println("Linked List is Empty");
            return Integer.MIN_VALUE;
        }else if(size==1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        size--;
        return val;
    }

    public int removeLast() {
        if(size==0) {
            System.out.println("Linked List is Empty");
            return Integer.MIN_VALUE;
        }else if(size==1) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        Node prev = head;
        for(int i=0;i<size-2;i++) {
            prev = prev.next;
        }
        size--;
        int val = prev.next.data;
        prev.next = null;
        tail = prev;
        return val;
    }

    public int itrSearch(int key) {
        Node n = head;
        for(int i=0;i<size;i++) {
            if(n.data == key) {
                return i;
            }
            n = n.next;
        }
        return -1;
    }

    public int helper(Node head, int key) {
        if(head==null) {
            return -1;
        }
        if(head.data == key) {
            return 0;
        }
        int idx = helper(head.next,key);
        if(idx == -1) {
            return -1;
        }
        return idx + 1;
    }

    public int recSearch(int key) {
        return helper(head,key);
    }

    public void deleteNthfromEnd(int n) {
        // calculate size
        int sz = 0;
        Node temp = head;
        while(temp != null) {
            temp = temp.next;
            sz++;
        }
        if(n == sz) {
            head = head.next;
            return;
        }
        int i = 1;
        Node prev = head;
        while(i < sz-n) {
            prev = prev.next;
            i++;
        }
        prev.next = prev.next.next;
    }  

    // Slow-Fast Approach <= !!!IMPORTANT
    public Node findMiddle(Node head) { // helper
        Node hare = head;
        Node turtle = head;
        while(hare != null && hare.next !=null) {
            turtle = turtle.next;  // +1
            hare = hare.next.next; // +2
        }
        return turtle;
    }

    public boolean isPalindrome() {
        if(head == null || head.next == null) {
            return true;
        }
        //find mid
        Node mid = findMiddle(head);
        // reverse 2nd half
        Node prev = null;
        Node curr = mid;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node right = prev; // right half head
        Node left = head;
        // check left half and right half
        while(right != null) {
            if(left.data != right.data) {
                return false;    
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    public static void main(String[] args) {
        LinkedList1 ll = new LinkedList1();
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(3);
        // ll.addLast(4);
        // ll.addMiddle(2 , 9);
        // ll.printLL();
        // System.out.println(ll.itrSearch(9));
        // System.out.println(ll.recSearch(9));
        ll.addFirst(2);
        ll.addFirst(1);
        ll.addFirst(1);
        ll.addFirst(2);
        System.out.println(ll.isPalindrome());

        
    }

}
