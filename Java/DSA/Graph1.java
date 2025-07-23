import java.util.*;

public class Graph1 {
	static class Edge {
		int dest;
		int src;
		int wt;
		Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
        }
		Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
            this.wt =  wt;
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

    public static void dfs(ArrayList<Edge> graph[], int curr, boolean vis[]) {
        System.out.print(curr + " ");
        vis[curr] = true;

        for(int i=0; i<graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!vis[e.dest]) {
                dfs(graph, e.dest, vis);
            }
        }

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
        createGraph(graph);
        System.out.println("BFS Traversal:");
        bfs(graph, 0);
        System.out.println("\nDFS Traversal:");
        dfs(graph, 0, new boolean[V]);

        
    }
}