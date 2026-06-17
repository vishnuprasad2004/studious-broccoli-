
import java.util.LinkedList;
import java.util.Queue;

public class Graph2 {

	/*
	 * Minimum time required to rot all oranges:
	 * We have a matrix of dimension m*n where each cell in the matrix can have
	 * values 0, 1 or 2
	 * which has the following meaning:
	 * 0: Empty cell 1: Cells have fresh oranges 2: Cells have rotten oranges
	 * We have to determine what is the minimum time required so that all the
	 * oranges become
	 * rotten. A rotten orange at index [i,j] can rot other fresh orange at indexes
	 * [i-1,j], [i+1,j], [i,j-1],
	 * [i,j+1] (up, down, left and right). If it is impossible to rot every orange
	 * then simply return -1.
	 * 
	 * {2, 1, 0, 2, 1}
	 * {0, 0, 1, 2, 1}
	 * {1, 0, 0, 2, 1}
	 * 
	 * CHALLENGE: Challenge I faced here is that I never worked with multi source BFS & never worked with implicit graph BFS algo.
	 */

	// We have to implement multi source BFS, new concept
	public static int minTimeToRotOranges(int[][] graph) {
		Queue<int[]> q = new LinkedList<>();
		int time = 0;
		int fresh = 0;
		for(int i=0; i< graph.length; i++) {
			for(int j=0; j<graph[0].length; j++) {
				if(graph[i][j] == 1) fresh++;
				// collect all the rotten oranges to simultaneously start the rotting process
				if(graph[i][j] == 2) q.offer(new int[]{i, j}); 
			}
		}
		
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};

		while(!q.isEmpty() && fresh > 0) {
			int size = q.size();
			for(int i=0; i< size; i++) {
				int[] curr = q.poll();

				for(int d=0; d<4; d++) {
					int nr = curr[0] + dx[d];
					int nc = curr[1] + dy[d];

					if(
						nr >= 0 && nr < graph.length &&
						nc >= 0 && nc < graph[0].length &&
						graph[nr][nc] == 1
					) {
						graph[nr][nc] = 2;
						fresh--;
						q.offer(new int[]{nr, nc});
					}
				}
			}
			time++;
		}

		return fresh == 0 ? time : -1;
	}

	public static void main(String[] args) {
		int[][] graph = {
				{ 2, 1, 0, 2, 1 },
				{ 0, 0, 1, 2, 1 },
				{ 1, 0, 0, 2, 1 }
		};

		System.out.println(minTimeToRotOranges(graph));
	}
}
