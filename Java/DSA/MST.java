import java.util.ArrayList;
import java.util.Collections;
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
	public static int prims(ArrayList<Edge> graph[], int src) { // O(V+ E.log(V))
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


	// required to implement kruskal's -> union-find data structure
	public static int V = 6;
	public static int par[];
	public static int rank[];

	public static void init() {
		par = new int[V];
		rank = new int[V];
		for(int i=0; i<V; i++) {
			par[i] = i;
		}
	}

	public static int find(int x) {
		if(par[x] == x) return x;
		else return find(par[x]);
	}

	public static void union(int a, int b) {
		int parA = find(a);
		int parB = find(b);
		if(rank[parA] == rank[parB]) {
			par[parA] = parB;
			rank[parB]++;
		} else if (rank[parA] < rank[parB]) {
			par[parA] = parB;
		} else {
			par[parB] = parA;
		}
	}

	// kruskal's algorithm -> uses unionfind to detect cycles and create a MST 
	public static int kruskals(ArrayList<Edge> edges) { // O(V + E.log(E))
		init();
		Collections.sort(edges); // IMPORTANT -> for minimum cost MST
		int minCost = 0;
		int count = 0; // count of edges used to create the MST

		for(int i=0; count < V-1; i++) {
			Edge e = edges.get(i);
			int parSrc = find(e.src);
			int parDest = find(e.dest);
			if (parSrc != parDest) {
				union(e.src, e.dest);
				minCost += e.wt;
				count++;
			}
			// else continue since same parents, so cycle will be created
		}

		return minCost;
	}

	// this is just a util function to convert an Adjacency List (Array of ArrayLists) to a list of Edges
	public static ArrayList<Edge> convertGraph(ArrayList<Edge> graph[]) {
		ArrayList<Edge> edges = new ArrayList<>();
		for (ArrayList<Edge> list: graph) {
			for (Edge e : list) {
				edges.add(e);
			}
			
		}
		return edges;
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
		
		ArrayList<Edge> graph[] = new ArrayList[V];
		createGraph(graph);
		System.out.println(prims(graph, 0)); // 9

		// kruskal's algo
		ArrayList<Edge> edges = convertGraph(graph);
		System.out.println(kruskals(edges));
	}
}
