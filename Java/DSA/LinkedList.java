import java.util.*;

public class LinkedList {
    
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

    // public int recSearch(int key) {
    //     if()
    // }



    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.addFirst(2);
        ll.addFirst(1);
        ll.addLast(3);
        ll.addLast(4);
        ll.addMiddle(2 , 9);
        ll.printLL();
        System.out.println(ll.size);
        ll.removeFirst();
        ll.removeLast();
        ll.printLL();
        System.out.println(ll.itrSearch(9));
              

        
    }

}
