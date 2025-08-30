
public class UnionFind {


	/**
	 * 
	 */

	static int n = 7;
	public static int rank[];
	public static int par[];

	// initialization of Union find
	public static void init() {
		rank = new int[n];
		par = new int[n];
		for (int i = 0; i < n; i++) {
			par[i] = i;
		}
	}

	public static int find(int x) {
		if(x == par[x]) return x;
		else return find(par[x]);
	}

	public static void union(int a, int b) {
		int parA = find(a);
		int parB = find(b);

		if(rank[parA] == rank[parB]) {
			par[parB] = parA;
			rank[parA]++;
		} else if (rank[parA] > rank[parB]) {
			par[parB] = parA;
		} else {
			par[parA] = parB;
		}
	}

	public static void main(String[] args) {
		init();
		union(1, 3);
		System.out.println(find(2));
	}
}