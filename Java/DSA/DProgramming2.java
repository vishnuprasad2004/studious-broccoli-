public class DProgramming2 {

	// Min Edit Distance Algorithm used for error correction - from CLNLP class in 7th semester
	public static int editDistance(String s1, String s2) { // O(n * m)
		int n = s1.length();
		int m = s2.length();
		int dp[][] = new int[n+1][m+1];

		// initialization
		for (int i=0; i<n+1; i++) {
			for(int j=0; j<m+1; j++) {
				if(i==0) dp[i][j] = j;
				if(j==0) dp[i][j] = i;
			}
		}

		// bottom-up approach
		for (int i=1; i<n+1; i++) {
			for(int j=1; j<m+1; j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					int insertion = dp[i][j-1] + 1;
					int deletion = dp[i-1][j] + 1;
					int updation = dp[i-1][j-1] + 1;
					dp[i][j] = Math.min(insertion, Math.min(deletion, updation));
				}
			}
		}

		return dp[n][m];
	}

	public static void main(String[] args) {
		String word1 = "beautiful";
		String word2 = "bountifull";
		System.out.println(editDistance(word1, word2));
	}
}