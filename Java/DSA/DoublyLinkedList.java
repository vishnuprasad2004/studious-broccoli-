public class DoublyLinkedList {
    class Node {
        int data;
        Node next;
        Node prev;
        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size;

    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;
        if(tail == null) {
            head = tail = newNode;
            return;
        }        
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    public void print() {
        if(head == null) {
            System.out.println("Empty Linked List");
            return;
        }
        Node temp = head;
        System.out.print("null <->");
        while(temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void removeFirst() {
        if(head == null) {
            System.out.println("Linked List is Empty");
            return;
        }else if(size==1) {
            head = tail = null;
            size = 0;
            return;
        }
        head = head.next;
        head.prev = null;
        size--;
    }

    public void removeLast() {
        if(tail == null) {
            System.out.println("Linked List is Empty");
            return;
        }else if(size==1) {
            head = tail = null;
            size = 0;
            return;
        }
        tail = tail.prev;
        tail.next = null;
        size--;
    }

    // important remember the algo steps
    public void reverse() {
        Node curr = head;
        tail = head; 
        Node prev = null;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        head = prev; // now the head is at the back & tail in front 
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList(); 
        dll.addFirst(5);   
        dll.addFirst(4);   
        dll.addFirst(3);   
        dll.addFirst(2);
        dll.print();   
        dll.reverse();
        System.out.println(tail.data);
        dll.print();
    }
}
