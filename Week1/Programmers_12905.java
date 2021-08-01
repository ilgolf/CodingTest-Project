import java.util.Arrays;

public class Programmers_12905 {
    int[][] dp, board;
    int n, m;

    public int solution(int[][] board) {
        this.board = board;
        n = board.length;
        m = board[0].length;
        dp = new int[n][m];
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res = Math.max(res, solve(i, j));

            }
        }
        return res * res;
    }

    // top-down
    private int solve(int x, int y) {
        if (x < 0 || y < 0) return 0;
        if (board[x][y] == 0) return 0;

        int ret = dp[x][y];
        if (ret != -1) return ret;
        // 좌, 상, 대각선 중에서 가장 작은 변의 길이
        ret = Math.min(solve(x - 1, y), solve(x, y - 1));
        ret = Math.min(ret, solve(x - 1, y - 1));

        // 현재 좌표가 0 이 아니므로 +1
        return dp[x][y] = ret + 1;
    }
}
