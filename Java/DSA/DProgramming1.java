public class DProgramming1 {

    public static int fib(int n, int dp[]) {
        if(n == 0 || n == 1) return n;
        if(dp[n] != 0) return dp[n];
        dp[n] = fib(n-1, dp) + fib(n-2, dp);
        return dp[n];
    }

    public static int fibTabulation(int n, int dp[]) {
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2;i<=n;i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }

    // ======== counting stairs problem =================

    static class CountingStairs {
        public static int recursion(int n) {
            if(n < 0) return 0;
            if(n == 0) return 1;
            return recursion(n-1) + recursion(n-2);
        }
        
        public static int memoization(int n,int dp[]) {
            if(n < 0) return 0;
            if(n == 0) return 1;
            dp[n] = memoization(n-1, dp) + memoization(n-2, dp);
            return dp[n];
        }


    }

    public static void main(String[] args) {
        int n = 5;
        int dp[] = new int[n+1];
        System.out.println(fibTabulation(n,dp));
        
    }
}