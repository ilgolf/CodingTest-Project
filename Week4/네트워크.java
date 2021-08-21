import java.util.LinkedList;
import java.util.Queue;

public class 네트워크 {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visit = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                queue.add(i);
                visit[i] = true;
                answer++;
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    for (int next = 0; next < n; next++) {
                        if (computers[cur][next] == 1 && !visit[next]) {
                            visit[next] = true;
                            queue.add(next);
                        }
                    }
                }
            }
        }
        //System.out.println(answer);
        return answer;
    }
}
