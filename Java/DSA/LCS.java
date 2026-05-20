public class LCS {
	/**
	 * LCS (Longest Common Subsequence) Characters need to appear in the same order, but not necessarily continuously.
	 */
	public static int longestCommonSubSeq(String a, String b) {
		int m = a.length();
		int n = b.length();

		
		int[][] dp = new int[m + 1][n + 1];
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}
		
		// now that the dp matrix is filled, we can find the longest common subsequence
		StringBuilder sb = new StringBuilder(); // stores the answer
		int i = m;
		int j = n;
		
		while(i>0 && j>0) { 
			if(a.charAt(i-1) == b.charAt(j-1)) {
				sb.append(a.charAt(i-1));
				i--;
				j--;
			} else if(dp[i-1][j] > dp[i][j-1]) {
				i--;
			} else {
				j--;
			}
		}

		System.out.println(sb.reverse().toString());
		return dp[m][n];
	}



	/**
	 * LCS (Longest Common Substring) Characters must be continuous and consecutive in both strings.
	 */
	public static int longestCommonSubStr(String a, String b) {
		int m = a.length();
		int n = b.length();
	
		int[][] dp = new int[m+1][n+1];
		
		int maxLen = 0;
		int endIdx = 0;

		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					if(dp[i][j] > maxLen) {
						maxLen = dp[i][j]; // if there was a common substring, it will grow diagonally 1 -> 2 -> 3
						endIdx = i;
					}
				} else {
					dp[i][j] = 0; // else the dp[i][j] becomes 0, no more common substring, start from fresh
				}
			}
		}


		System.out.println(a.substring(endIdx-maxLen, endIdx));
		return maxLen;
	}

	static class OptimizedLCS {
		// if we notice in the formulae
		// MATCH => dp[i][j] = dp[i-1][j-1] + 1
		// NO MATCH => dp[i][j] = max(dp[i-1][j], dp[i][j-1])
		// we can reduce the space taken from O(m*n) to merely O(m) if we consider only the current row and the prev row, all before rows are not required

		public static int longestCommonSubSeq(String a, String b) {
			int m = a.length();
			int n = b.length();

			int[] prev = new int[m+1];
			int[] curr = new int[m+1];

			for (int i = 1; i <= m; i++) {
				for (int j = 1; j <= n; j++) {
					if (a.charAt(i - 1) == b.charAt(j - 1)) {
						curr[j] = prev[j-1] +1;
					} else {
						curr[j] = Math.max(prev[j], curr[j-1]);
					}
				}
				prev = curr;
				curr = new int[m+1];
			}


			return prev[m];
		}

	}



	public static void main(String[] args) {
		String a = "abecdg";
		String b = "abedgc";
		System.out.println(longestCommonSubSeq(a, b));
		System.out.println(longestCommonSubStr(a, b));
	}
}
