import java.util.*;

public class BinaryTree3 {
    static class Node  {
        int data;
        Node left;
        Node right;
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;
        public Node buildTree(int nodes[]) {
            idx++;
            if(nodes[idx] == -1) return null;

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }
    }

    public static boolean getPath(Node root, int item, ArrayList<Node> list) {
        if(root == null) return false;
        list.add(root);

        if(root.data == item) {
            return true;
        }

        boolean foundLeft = getPath(root.left, item, list);
        boolean foundRight = getPath(root.right, item, list);

        if(foundLeft || foundRight) return true;

        list.remove(list.size()-1);
        return false;
    }

    /*
    * Lowest common Ancestor: find the root node common to n1 and n2 nodes
    *      1
    *     / \
    *    2   3
    *   / \ / \
    *  4  5 6  7
    *  ex: n1 = 4, n2 = 5; lca = 2
    *  ex: n1 = 4, n2 = 7; lca = 1
    */

    // approach 1

    public static int lca(Node root, int n1, int n2) {
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        getPath(root, n1, path1);    
        getPath(root, n2, path2);   
        
        Node lca = root;
        for(int i=0;i<path1.size() && i< path2.size(); i++) {
            if(path1.get(i) == path2.get(i)) lca = path1.get(i);
            else break; 
        }


        return lca.data;
    }


    public static Node lca2(Node root, int n1, int n2) {
        if(root == null || root.data == n1 || root.data == n2) {
            return root;
        }
        Node leftLca = lca2(root.left, n1, n2);
        Node rightLca = lca2(root.right, n1, n2);

        if(leftLca == null) {
            return rightLca;
        }
        if(rightLca == null) {
            return leftLca;
        }

        return root;
    }

    // Delete Nodes And Return Forest
    // private static boolean toDelete[] = new boolean[];
    public static List<Node> delNodes(Node root, int to_delete[]) {
        
        ArrayList<Node> forest = new ArrayList<>();



        return forest;
    }



    public static void main(String[] args) {
        // int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        // BinaryTree tree = new BinaryTree();
        // Node root = tree.buildTree(nodes);    
        /*
         *      1
         *     / \
         *    2   3
         *   / \ / \
         *  4  5 6  7
         * 
         */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        int n1 = 4; int n2 = 5;
        System.out.println("Least Common Ancestor: " + lca2(root, n1, n2).data);
    }    
}
