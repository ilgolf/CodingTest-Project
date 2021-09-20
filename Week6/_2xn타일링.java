public class _2xn타일링 {
    static int[] dp;
    static int MOD = 1000000007;

    public int solution(int n) {
        dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
        }
        return dp[n];
    }
}
