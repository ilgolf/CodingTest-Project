class Solution {
    static int ok;
	public static void dfs(int N, int j, int k, int x, int y, String[] places, boolean[][] check) {
		if (x < 0 || x >= N || y < 0 || y >= N)
			return;
		if (places[x].charAt(y) == 'X')
			return;
		if (check[x][y] == true)
			return;
		if (Math.abs(j - x) + Math.abs(k - y) > 2)
			return;
		if (Math.abs(j - x) + Math.abs(k - y) != 0 && places[x].charAt(y) == 'P') {
			ok = 0;
			return;
		}
		check[x][y] = true;
		dfs(N, j, k, x + 1, y, places, check);
		dfs(N, j, k, x - 1, y, places, check);
		dfs(N, j, k, x, y + 1, places, check);
		dfs(N, j, k, x, y - 1, places, check);
	}

	public static int checkPlace(String[] places, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				boolean[][] check = new boolean[N][N];
				if (places[i].charAt(j) == 'P') {
					ok = 1;
					dfs(N, i, j, i, j, places, check);
					if (ok == 0)
						return 0;
				}
			}
		}
		return 1;
	}

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
		int N = 5;
		for (int i = 0; i < places.length; i++)
			answer[i] = checkPlace(places[i], N);
		return answer;
    }
}