import java.util.ArrayList;
import java.util.PriorityQueue;

public class ShortestPathFinding {
	public static class Edge {
        @SuppressWarnings("unused")
		int dest;
        @SuppressWarnings("unused")
		int src;
        @SuppressWarnings("unused")
		int wt;
		Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
			this.wt = wt;
        }
	}
	public static class Pair implements Comparable<Pair> {
		int n;
		int path;
		public Pair(int n, int path) {
			this.n = n;
			this.path = path;
		}

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
	}

	public static void dijkstra(ArrayList<Edge> graph[], int src) { // O(V+ E.log(V))
		int dist[] = new int[graph.length];
		for (int i = 0; i < dist.length; i++) {
			// + infinity
			if(i != src) dist[i] = Integer.MAX_VALUE; 
		}

		boolean vis[] = new boolean[graph.length];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(src, 0));

		// loop -> modified BFS
		while(!pq.isEmpty()) {
			Pair curr = pq.remove();
			if (!vis[curr.n]) {
				vis[curr.n] = true;
				// neigbhours - bfs
				for (int i = 0; i < graph[curr.n].size(); i++) {
					Edge e = graph[curr.n].get(i);
					int u = e.src;
					int v = e.dest;
					int wt = e.wt;

					if (dist[u] + wt < dist[v]) {
						dist[v] = dist[u] + wt;
						pq.add(new Pair(v, dist[v]));
					}
				}
			}

		}

		System.out.println("The shortest distances using dijkstra's algorithm: ");
		for (int i = 0; i < dist.length; i++) {
			System.out.println(i +  " => " + dist[i]);
		}

		/**
		 * Why BFS-like behavior is preferred over DFS-like behavior in Dijkstra's:
			Guaranteed Shortest Path (for unweighted graphs):
			BFS naturally finds the shortest path in unweighted graphs because it explores the graph level by level, ensuring that the first time a node is reached, it is via the path with the fewest edges. Dijkstra's algorithm generalizes this by always exploring the unvisited node with the smallest known distance from the source, guaranteeing the shortest path even with weights.
			Exploration Strategy:
			BFS: explores all neighbors at the current "level" before moving to the next level. This systematic expansion from the source is crucial for finding shortest paths.
			DFS: explores as deeply as possible along one path before backtracking. This can lead to finding very long paths to a destination before discovering a shorter one, making it unsuitable for shortest path problems in general graphs.
			Dijkstra's as a Weighted BFS:
			When all edge weights are equal, Dijkstra's algorithm effectively behaves identically to BFS. The use of a priority queue in Dijkstra's allows it to prioritize visiting nodes based on their accumulated path cost, which is the key difference that enables it to work with weighted graphs where BFS alone would fail.
		 */


	}

	public static void createGraph(ArrayList<Edge> graph[]) {
        for(int i=0; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
		
		graph[0].add(new Edge(0, 1, 2));
		graph[0].add(new Edge(0, 2, 4));
		
		graph[1].add(new Edge(1, 3, 7));
		graph[1].add(new Edge(1, 2, 1));

		graph[2].add(new Edge(2, 4, 3));
		
		graph[3].add(new Edge(3, 5, 1));
		
		graph[4].add(new Edge(4, 3, 2));
		graph[4].add(new Edge(4, 5, 5));
	
	}


	public static void main(String[] args) {
		// arrows to make directed graphs ↓→←↑↖↗↘↙

			/**    (7)
		       1 ----→ 3
		(2) ↗ |       ↑ ↘ (1)
		   0  |(1) (2) |   5
		(4) ↘ ↓       | ↗ (5)
			   2 ----→ 4
				(3)
			*/

		int V = 6;
		ArrayList<Edge> graph[] = new ArrayList[V];
		createGraph(graph);
		dijkstra(graph, 0);
		
	}
}