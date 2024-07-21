public class BinaryTree2 {
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

    // helper method to find the height of a tree
    //TC: O(N)
    public static int height(Node root) {
        if(root == null) return 0;

        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;
    }

    // approach 1
    // find the longest chain of nodes called the diameter
    // TC: O(N^2) :( 
    // we visit each node once and compute its heights[O(N)], so N*N time complexity
    public static int diameter1(Node root) {
        if(root == null) return 0;

        int leftDim = diameter1(root.left);
        int rightDim = diameter1(root.right);
        int lh = height(root.left);
        int rh = height(root.right);
        int selfDim = lh + rh + 1;

        return Math.max(leftDim, Math.max(rightDim, selfDim));
    }


    // approach 2
    // find the longest chain of nodes called the diameter
    // TC: O(N)
    // computing both height and diameter at once using a Info class to store the d & h
    static class Info {
        int d,h;
        Info(int d, int h) {
            this.d = d;
            this.h = h;
        }
    }

    public static Info diameter2(Node root) {
        if(root == null) {
            return new Info(0, 0);
        }

        Info leftInfo = diameter2(root.left);
        Info rightInfo = diameter2(root.right);

        int diam = Math.max(Math.max(leftInfo.d, rightInfo.d), leftInfo.h + rightInfo.h + 1);
        int height = Math.max(leftInfo.h, rightInfo.h) + 1;

        return new Info(diam, height);
    }





    public static void main(String[] args) {
        int nodes[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1};

        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);
        System.out.println(diameter2(root).d);
    }
}