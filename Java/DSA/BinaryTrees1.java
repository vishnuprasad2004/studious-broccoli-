import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees1 {

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

    // node, left, right
    public static void preOrder(Node root) {
        if(root == null)    return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // left, node, right
    public static void inOrder(Node root) {
        if(root == null)    return;
        preOrder(root.left);
        System.out.print(root.data + " ");
        preOrder(root.right);
    }

    // left, right, node
    public static void postOrder(Node root) {
        if(root == null)    return;
        preOrder(root.left);
        preOrder(root.right);
        System.out.print(root.data + " ");
    }

    // displaying all nodes according to level in the tree
    public static void levelOrder(Node root) {
        if(root == null) {
            return;
        }

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            Node curr = q.remove();
            if(curr == null) {
                System.out.println();
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(curr.data + " ");
                if(curr.left != null) {
                    q.add(curr.left);
                }
                if(curr.right != null) {
                    q.add(curr.right);
                }
            }
        }    
    }

    // finding the height of the tree
    public static int height(Node root) {
        if(root == null) return 0;

        int lheight = height(root.left);
        int rheight = height(root.right);
        return Math.max(lheight, rheight) + 1;
    }


    // static int count = 0;
    public static int countNodes(Node root) {
        if(root == null) return 0;

        int lcount = countNodes(root.left);
        int rcount = countNodes(root.right);
        return lcount + rcount + 1;
    }



    
    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
        System.out.println();
        levelOrder(root);
        System.out.println("Height: " + height(root));
        countNodes(root);
        System.out.println("Count: " + countNodes(root));

    }
}
