
public class LinkedList2 {
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
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int data) { //O(1)
        Node newNode = new Node(data);
        size++;
        if(head==null) {
            head = tail = newNode;
        }
        tail.next = newNode;
        tail = newNode;
    }

    public void printLL() {
        if(head == null) {
            System.out.println("Empty Linked List");
            return;
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

    // Floyd's Cycle finding Algorithm
    public static boolean hasCycle() {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                return true;
            }
        }
        return false;
    }
    
    public static void removeCycle() {
        Node slow = head;
        Node fast = head;
        boolean cycle = false;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                cycle = true;
                break;
            }
        }
        if(!cycle) {
            return;
        }
        // remove cycle 
        Node prev = fast; // or null 
        slow = head;
        while(slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        prev.next = null;
        
    }

    public static Node findMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static Node mergeSort(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        Node mid = findMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        Node sortedLeft = mergeSort(head);
        Node sortedRight = mergeSort(rightHead);
        return merge(sortedLeft,sortedRight);   
    }

    private static Node merge(Node head1, Node head2) {
        Node mergedLL = new Node(-1);
        Node temp = mergedLL;

        while(head1 != null && head2 != null) {
            if(head1.data <= head2.data) {
                temp.next = head1;
                head1 = head1.next; 
            }else {
                temp.next = head2;
                head2 = head2.next;
            }
            temp = temp.next;
        }

        while(head1 != null) {
            temp.next = head1;
            head1 = head1.next;
            temp = temp.next;
        }

        while(head2 != null) {
            temp.next = head2;
            head2 = head2.next;
            temp = temp.next;
        }
        return mergedLL.next;
    }

    public void swap(int num1, int num2) {
        if(num1 == num2) {
            return;
        }
        Node a=head;
        Node b=head;
        Node aprev = null;
        Node bprev = null;
        while(a != null && a.data != num1) {
            aprev = a;
            a = a.next;
        }
        while(b != null && b.data != num2) {
            bprev = b;
            b = b.next;
        }
        if(a == null || b == null) {
            return;
        }
        if(aprev!=null) {
            aprev.next = b;
        }else {
            head = b;
        }
        if(bprev!=null) {
            bprev.next = a;
        }else {
            head = a;
        }
        
        Node temp = a.next;
        a.next = b.next;
        b.next = temp;
    }

    public void sortEvenNOdd() {
        Node evenNodes = null;
        Node oddNodesH = null;
        Node oddNodes = null;
        Node temp = head;
    
        while(temp != null) {
            if(temp.data % 2 == 0) {
                if(evenNodes == null) {
                    evenNodes = temp;
                }else {
                    evenNodes.next = temp;
                    evenNodes = temp;
                }
            }else {
                if(oddNodes == null) {
                    oddNodes = oddNodesH = temp;
                }else {
                    oddNodes.next = temp;
                    oddNodes = temp;
                }
            }
            temp = temp.next;
        }
        evenNodes.next = oddNodesH;
        head = evenNodes; 
    }

    public static void main(String[] args) {
        // head = new Node(1);
        // head.next = new Node(2);
        // head.next.next = new Node(3);
        // head.next.next.next = new Node(4);
        // head.next.next.next.next = head.next;
        // // System.out.println(hasCycle());// true        
        // // removeCycle();
        // // printLL();

        // // JCF LinkedList
        // LinkedList<Integer> ll = new LinkedList<>();
        // ll.addFirst(1);
        // ll.addFirst(2);
        // ll.addLast(3);
        // System.out.println(ll); 
        LinkedList2 ll = new LinkedList2();
        ll.addFirst(7);
        ll.addFirst(34);
        ll.addFirst(10);
        ll.addFirst(13);
        ll.addFirst(14);
        ll.addFirst(39);
        ll.printLL();
        ll.swap(13, 14);
        ll.printLL();
        ll.sortEvenNOdd();
        ll.printLL();
        head = mergeSort(head);
        ll.printLL();

    }
}
