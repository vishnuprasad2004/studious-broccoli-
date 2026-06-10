import java.util.*;

public class TopologicalSorting {
	public static class Edge {
		int dest;
		int src;

		Edge(int src, int dest) {
			this.src = src;
			this.dest = dest;
		}
	}

	public static void createGraph(ArrayList<Edge> graph[]) {
		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		graph[2].add(new Edge(2, 3));

		graph[3].add(new Edge(3, 1));

		graph[4].add(new Edge(4, 0));
		graph[4].add(new Edge(4, 1));

		graph[5].add(new Edge(5, 0));
		graph[5].add(new Edge(5, 2));
	}

	public static void topologicalSortUtil(ArrayList<Edge>[] graph, boolean[] vis, Stack<Integer> stack, int curr) {
		vis[curr] = true;
		for (Edge e : graph[curr]) {
			if(!vis[e.dest]) {
				topologicalSortUtil(graph, vis, stack, e.dest);
			}
		}
		stack.push(curr);
	}

	public static List<Integer> topologicaSort(ArrayList<Edge>[] graph) {
		List<Integer> ans = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		boolean[] vis = new boolean[graph.length];
		for (int curr=0; curr<graph.length; curr++) {
			if(!vis[curr]) topologicalSortUtil(graph, vis, stack, curr);
		}

		while(!stack.isEmpty()) {
			ans.add(stack.pop());
		}

		return ans;
	}

	public static void main(String[] args) {
		int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V]; // directed graph - only DAGs allowed here
		createGraph(graph);
		System.out.println(topologicaSort(graph));
	}

}
