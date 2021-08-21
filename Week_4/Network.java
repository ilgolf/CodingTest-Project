package Week_4;

public class Network {

    static boolean[] checked;
    static int[][] com;

    private static void dfs(int start, int n) {
        if(start == n) {
            return;
        }

        checked[start] = true;

        for(int i=0; i<n; i++) {
            if(i == start) continue;

            if(com[start][i] == 1 && !checked[i]) {
                dfs(start + 1, n);
            }
        }
        return;
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        com = new int[n][n];

        int idx = 0;
        for(int[] computer : computers) {
            com[idx] = computer;
            idx ++;
        }

        checked = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!checked[i]) {
              dfs(i, n);
              answer++;
            }
          }

        return answer;
    }
}
