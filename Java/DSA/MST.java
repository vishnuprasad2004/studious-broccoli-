import java.util.ArrayList;
import java.util.PriorityQueue;

public class MST {

	public static class Edge implements Comparable<Edge> {
		int dest;
		int src;
		int wt;
		Edge(int src, int dest, int wt) {
            this.src = src;
            this.dest = dest;
			this.wt = wt;
        }

        @Override
        public int compareTo(Edge e) {
            return this.wt - e.wt;
        }
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

	static class Pair implements Comparable<Pair> {
		int node;
		int cost;
		Pair(int n, int cost) {
			this.cost = cost;
			this.node = n;
		}

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
		
	}

	// prim's algorithm 
	public static int prims(ArrayList<Edge> graph[], int src) {
		int minCost = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		boolean vis[] = new boolean[graph.length];

		pq.add(new Pair(src, 0));

		while(!pq.isEmpty()) {
			Pair curr = pq.remove();

			if (!vis[curr.node]) {
				vis[curr.node] = true;
				minCost += curr.cost;

				for (Edge edge : graph[curr.node]) {
					pq.add(new Pair(edge.dest, edge.wt));
				}
			}
		}

		return minCost;
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
		System.out.println(prims(graph, 0)); // 9
	}
}
