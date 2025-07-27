import java.util.ArrayList;

/**
 * Cycle Detection in Graphs
 * Undirected Graphs: DFS, BFS, DSU (Disjoint Set Union)
 * Directed Graphs: DFS, BFS, Topological Sort (Kahns Algorithm)
 */

public class CylicGraphs {
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

	public static boolean detectCycleDfs(ArrayList<Edge> graph[]) {
		boolean vis[] = new boolean[graph.length];
		for (int i = 0; i < vis.length; i++) {
			if(!vis[i]) {
				if(detectCycleDfsUtil(graph,vis,i,-1)) return true;
			}
		}
		return false;
	}

	public static boolean detectCycleDfsUtil(ArrayList<Edge> graph[], boolean vis[], int src, int parent) {
		// vist the node first
		vis[src] = true;

		// for neighbours
		for (int i = 0; i < graph[src].size(); i++) {
			Edge e = graph[src].get(i);
			// case 3
			if(!vis[e.dest]) {
				if(detectCycleDfsUtil(graph, vis, e.dest, src)) {
					return true;
				}
			}
			// case 1
			if (vis[e.dest] && parent != e.dest) {
				return true;
			}
			// case 2 -> do nothing -> continue
		
		}

		return false;
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
		System.out.println(detectCycleDfs(graph));
	}
}
