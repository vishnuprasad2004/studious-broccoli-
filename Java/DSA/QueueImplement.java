public class QueueImplement {

    static class LinearQueue {
        static int Q[];
        static int size;
        static int R;

        LinearQueue(int n) {
            size = n;
            Q = new int[size];
            R = -1;
        }

        public static boolean isEmpty() {
            return R == -1;
        }

        public static void enqueue(int data) {
            if(R == size-1) {
                System.out.println("Queue is Full");
                return;
            }
            Q[++R] = data;
        }

        public static int dequeue() {
            if(isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            int F = Q[0];
            for (int i=0; i<Q.length; i++) {
                Q[i] = Q[i+1];
            }
            return F;
        }
        
        public static int peek() {
            if(isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return Q[0];
        }
        public static void display() {
            for(int i=0;i<=R;i++) {
                System.out.print(Q[i]+ " ");
            }
            System.out.println();
        }
    }

    static class CircularQueue {
        static int Q[];
        static int size;
        static int F;
        static int R;

        CircularQueue(int n) {
            size = n;
            Q = new int[size];
            R = F = -1;
        }

        public static boolean isEmpty() {
            return R == -1 && F == -1;
        }

        public static boolean isFull() {
            return (R+1)%size == F;
        }

        public static void enqueue(int data) {
            if(isFull()) {
                System.out.println("Queue is Full");
                return;
            }
            if(F == -1) {
                F =0;
            }
            R = (R+1)%size;          
            Q[R] = data;
        }

        public static int dequeue() {
            if(isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            int popped = Q[F];
            if(F == R) {
                F = R = -1;
            }else{
                F = (F+1)%size;
            }
            return popped;
        }
        
        public static int peek() {
            if(isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            return Q[F];
        }
        public static void display() {
            for(int i=F;i!=R;i=(i+1)%size) {
                System.out.print(Q[i]+ " ");
            }
            System.out.print(Q[R]+" \n");
        }
    }

    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedQueue {
        static Node Head = null;
        static Node Tail = null;

        public static boolean isEmpty() {
            return Head == null && Tail == null;
        }

        public static void enqueue(int data) {
            Node newNode = new Node(data);
            if(Head == null) {
                Head = Tail = newNode;
                return;
            }
            Tail.next = newNode;
            Tail = newNode;
        }

        public static int dequeue() {
            if(isEmpty()) {
                System.out.println("Queue is Empty");
                return -1;
            }
            int front = Head.data;
            if(Tail == Head) {
                Tail = Head = null;
            } else {
                Head = Head.next;
            }
            return front;
        }

        public static void display() {
            Node temp = Head;
            while(temp != null) {
                System.out.print(temp.data+" ");
                temp = temp.next;
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        // LinkedQueue q = new LinkedQueue();

    }

}