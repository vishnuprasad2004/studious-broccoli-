import java.util.*;

public class Graph1 {

    /*
     *     === HOW TO STORE A GRAPH IN MEMORY ===
     *    EXAMPLE:
     *      1 -- 3
     *     /    | \
     *    0     |  5 -- 6
     *     \    | /
     *      2 -- 4
     * 
     *     Adjacency List (Array of Arraylists):
     *     0 -> [(0,1), (0,2)]
     *     1 -> [(1,0), (1,3)]
     *     2 -> [(2,0), (2,4)]
     *     3 -> [(3,1), (3,4), (3,5)]
     *     4 -> [(4,2), (4,3), (4,5)]
     *     5 -> [(5,3), (5,4), (5,6)]
     *     6 -> [(6,5)]
     *    
     *     Adjacency Matrix:
     *        0, 1, 2, 3, 4, 5, 6
     *     0 [0, 1, 1, 0, 0, 0, 0]
     *     1 [1, 0, 0, 1, 0, 0, 0]
     *     2 [1, 0, 0, 0, 1, 0, 0]
     *     3 [0, 1, 0, 0, 1, 1, 0]
     *     4 [0, 0, 1, 1, 0, 1, 0]
     *     5 [0, 0, 0, 1, 1, 0, 1]
     *     6 [0, 0, 0, 0, 0, 1, 0]
     * 
     *     NOTE: Here, The graph will be stored using Array of Arraylists of Edges
     * 
     *   
     */

	public static class Edge {
		int dest;
		int src;
		Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
	}
    
    public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1));
        graph[0].add(new Edge(0, 2));

        graph[1].add(new Edge(1, 0));
        graph[1].add(new Edge(1, 3));
        
        graph[2].add(new Edge(2, 0));
        graph[2].add(new Edge(2, 4));
        
        graph[3].add(new Edge(3, 1));
        graph[3].add(new Edge(3, 4));
        graph[3].add(new Edge(3, 5));
        
        graph[4].add(new Edge(4, 2));
        graph[4].add(new Edge(4, 3));
        graph[4].add(new Edge(4, 5));
        
        graph[5].add(new Edge(5, 3));
        graph[5].add(new Edge(5, 4));
        graph[5].add(new Edge(5, 6));

        graph[6].add(new Edge(6, 5));
    }

    public static void bfs(ArrayList<Edge> graph[], int src) { // very similar to level order traversal in trees
        Queue<Integer> q = new LinkedList<>();
        boolean vis[] = new boolean[graph.length];
        
        q.add(src); // add the starting node
        while(!q.isEmpty()) {
            int curr = q.remove();

            if(!vis[curr]) {
                System.out.print(curr + " ");
                vis[curr] = true;
                for(int i=0; i<graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
            }
        }

    }   

    public static void dfs(ArrayList<Edge> graph[]) {
        boolean vis[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if(!vis[i]) {
                dfsUtil(graph, i, vis);
            }
        }
    }

    public static void dfsUtil(ArrayList<Edge> graph[], int curr, boolean vis[]) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                dfsUtil(graph, e.dest, vis);
            }
        }
    }

    public static boolean hasPath(ArrayList<Edge> graph[], int src, int dest, boolean vis[]) {
        if(src == dest) return true;
        vis[src] = true;
        for (int i=0; i<graph[src].size(); i++) {
            Edge e = graph[src].get(i);
            if(!vis[e.dest] && hasPath(graph, e.dest, dest, vis)) {
                return true;
            }
        }
        return false;
    }

    public static void allPaths(ArrayList<Edge> graph[], int src, int dest, String path) {
        if(src == dest) {
            System.out.println(path + dest);
         
            return;
        }
        for (Edge e : graph[src]) {
            allPaths(graph, e.dest, dest, path + src);
        }
    }


    /**
     * 
     *  We have a matrix where each cell contains either a ‘0’ or a ‘1’, and any cell containing a 1 is
        called a filled cell. Two cells are said to be connected if they are adjacent to each other
        horizontally, vertically, or diagonally. If one or more filled cells are also connected, they form a
        region. find the size of the largest region.
     */
    // function to find the area of an island
    public static int findMaxIslandUtil(int grid[][], boolean vis[][], int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || vis[i][j] || grid[i][j] == 0) {
            return 0;
        }

        vis[i][j] = true;
        int area = 1 + findMaxIslandUtil(grid, vis, i+1, j);
        area += findMaxIslandUtil(grid, vis, i-1, j);
        area += findMaxIslandUtil(grid, vis, i, j+1);
        area += findMaxIslandUtil(grid, vis, i, j-1);
        area += findMaxIslandUtil(grid, vis, i+1, j+1);
        area += findMaxIslandUtil(grid, vis, i-1, j-1);
        area += findMaxIslandUtil(grid, vis, i-1, j+1);
        area += findMaxIslandUtil(grid, vis, i+1, j-1);
        return area;
    }
    

    public static int findMaxIsland(int grid[][]) {
        int m = grid.length;
        int n = grid[0].length;

        boolean vis[][] = new boolean[m][n];
        int maxArea = 0;

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) {
                    int currArea = findMaxIslandUtil(grid, vis, i, j);
                    maxArea = Math.max(maxArea, currArea);
                }
            }
        }

        return maxArea;
    }
    

    public static void main(String[] args) {
        /**
           1 -- 3
          /    | \
         0     |  5 -- 6
          \    | /
           2 -- 4
         
         */
        int V = 7;
        ArrayList<Edge> graph[] = new ArrayList[V];
        // createGraph(graph);
        // System.out.println("BFS Traversal:");
        // bfs(graph, 0);
        // System.out.println("\nDFS Traversal:");
        // dfs(graph);
        // System.out.println("\nHas Path (Y/N) for 0 -> 6: " + hasPath(graph, 0, 6, new boolean[V])); 
        int m[][] = { {0, 0, 1, 1, 0}, {0, 0, 1, 1, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 1} };
        System.out.println(findMaxIsland(m));
    }
}