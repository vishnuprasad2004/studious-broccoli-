import java.util.*;

public class CheapestFlights {

    public static class Edge {
        int src;
        int dest;
        int cost;

        public Edge(int src, int dest, int cost) {
            this.cost = cost;
            this.dest = dest;
            this.src = src;
        }
    }
    
    public static class Info {
        int n;
        int cost;
        int stops;
        public Info(int n, int cost, int stop) {
            this.n = n; 
            this.cost = cost; 
            this.stops = stop;
        }
    } 

    public static void createGraph(int flights[][], int n, ArrayList<Edge> graph[]) {
        for (int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int[] flight : flights) {
            Edge e = new Edge(flight[0], flight[1], flight[2]);
            graph[flight[0]].add(e);
        }
    }

    public static int cheapestFlight(int n, int flights[][], int src, int dest, int k) {
        ArrayList<Edge> graph[] = new ArrayList[n];
        createGraph(flights, n, graph);

        int dist[] = new int[n];
        for (int i = 0; i < n; i++) {
            if(i != src) dist[i] = Integer.MAX_VALUE;
        }

        Queue<Info> q = new LinkedList<>();
        q.add(new Info(src, 0, 0));

        while(!q.isEmpty()) {
            Info curr = q.remove();
            if(curr.stops > k) break;

            for(int i=0;i<graph[curr.n].size();i++) {
                Edge e = graph[curr.n].get(i);
                // int u = e.src;
                int v = e.dest;
                int wt = e.cost;

                if (curr.cost + wt < dist[v] && curr.stops <= k) {
                    dist[v] = curr.cost + wt;
                    q.add(new Info(v, dist[v], curr.stops + 1));
                }
            }
        }

        if(dist[dest] == Integer.MAX_VALUE) return -1;
        else return dist[dest];
    }

    public static void main(String[] args) {
        int n = 4;
        int flights[][] = {{0,1,100}, {1,2,100}, {2,0,100}, {1,3,600}, {2,3,200}};
        int src = 0, dest = 3, k = 1;
        System.out.println(cheapestFlight(n, flights, src, dest, k));

    }
}
