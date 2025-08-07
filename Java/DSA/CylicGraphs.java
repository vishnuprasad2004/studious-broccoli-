import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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


	// Undirected Graphs
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
			// case 3: if you didn't visit the neighbour yet, then visit it
			if(!vis[e.dest]) {
				if(detectCycleDfsUtil(graph, vis, e.dest, src)) {
					return true;
				}
			}
			// case 1: if neighour (visited) is not the parent and already visited, then it must have been visited before -> true cycle exists
			if (vis[e.dest] && parent != e.dest) {
				return true;
			}
			// case 2 -> if neighbour (visited) is parent then, do nothing -> continue
		
		}

		return false;
	}


	/**
	 * Bipartite Graph -> is a graph whose vertices can be divided into 2 independent sets, u and v such that every edge (u,v) either connects a vertex from U to V or a vertex from V to U. No edge should connect 2 nodes which are in the same set.
	 	NOTE: If graph doen't have cycles then by default they are bipartite.
			Acyclic graphs -> True
			Even graphs -> True
			Odd graphs -> False
	 */
	public static boolean isBipartite(ArrayList<Edge> graph[]) { // O(V+E)
		Queue<Integer> q = new LinkedList<>();
		// default (unvisited) -> 0, color 1 -> 1, color 2 -> 2
		int color[] = new int[graph.length];
		
		
		// we did this for disconnected graphs with components
		for(int i=0;i<graph.length; i++) {
			if(color[i] == 0) {

				// BFS logic
				q.add(i);
				color[i] = 1;
				while(!q.isEmpty()) {
					int curr = q.remove();
					for (int j = 0; j < graph[curr].size(); j++) {
						Edge e = graph[curr].get(j);
						// case 1: if neighbour doesn't have any color yet  
						if(color[e.dest] == 0) {
							int nextColor = color[curr] == 1 ? 2 : 1;
							color[e.dest] = nextColor;
							q.add(e.dest);
						}		
						// case 2: if neighbour color is same as curr color -> return false
						else if (color[e.dest] == color[curr]) {
							return false; // Not Bipartite
						}			
						// case 3: if neighbour is of different color, then do nothing, continue
					}
		
				}
			}
		}

		return true;
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
		// System.out.println(detectCycleDfs(graph));
		System.out.println(isBipartite(graph)); // ans: false
	}
}
