package code;

import java.io.*;

/**
 *
 * 점화식 f(x) = f(x - 1) + f(x - 2)
 *
 */

public class Baek_11726 {

    static int n;
    static Integer[] dp = new Integer[1001];
    static final int MOD = 10007;

    static Integer tile(int x) {
        if (dp[x] != null) {
            return dp[x];
        }

        dp[x] = (tile(x - 1) + tile(x - 2)) % MOD;

        return dp[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        System.out.println(tile(n) % MOD);
    }
}
