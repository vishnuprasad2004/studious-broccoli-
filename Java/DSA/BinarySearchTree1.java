public class BinarySearchTree1 {

    static class Node {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void inOrder(Node root) {
        if(root == null) return;

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    
    // note: there are no duplicates for now
    public static Node insert(Node root, int value) {
        if(root == null) {
            root = new Node(value);
            return root;
        }
        // check where to add the new Node 
        if(root.data > value) {
            root.left = insert(root.left, value);
        } else {
            root.right = insert(root.right, value);
        }
        return root;
    }


    public static boolean search(Node root, int item) {
        if(root == null) {
            return false;
        }

        if(root.data == item) {
            return true;
        } else if(item < root.data) {
            return search(root.left, item);
        } else {
            return search(root.right, item);
        }
    }  



    public static Node delete(Node root, int val) {
        if(root.data > val) {
            root.left = delete(root.left, val);
            
        }else if(root.data < val) {
            root.right = delete(root.right, val);

        } else {
            // got the node ... voila
            
            // case 1: leaf node deletion 
            if(root.left == null && root.right == null) {
                return null;
            }
            // case 2: deletion of node with one child 
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            }

            // case 3: deletion of node with both children *!IMPORTANT!*
            Node IS = findInOrderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);

        }


        return root;
    }


    public static Node findInOrderSuccessor(Node root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }


    public static void printInOrder(Node root, int k1, int k2) {

    }

    public static void main(String[] args) {
        int values[] = {8, 5, 3, 1, 4, 6, 10, 11, 14};

        Node root = null;
        for (int value : values) {
            root = insert(root, value);
        }

        inOrder(root);
        System.out.println();
        root = delete(root, 3);
        inOrder(root);
    }
}
