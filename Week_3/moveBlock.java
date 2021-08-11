package Week_3;

import java.util.*;

public class moveBlock {

    static final int UP = 0;    // 위
    static final int RIGHT = 1;  // 오른쪽
    static final int DOWN = 2;  // 아래
    static final int LEFT = 3;  // 왼쪽

    static class P {
        int x, y, dir, time;

        public P(int x, int y, int dir, int time) {
            this.x = x;
            this.y = y;
            this.dir = dir;  // 방향
            this.time = time;  // 움직인 횟수
        }
    }

    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][][] mrot = {{{1, 1}, {1, -1}, {-1, -1}, {-1, 1}},  // 회전축 UP ~ LEFT
                            {{1, -1}, {-1, -1}, {-1, 1}, {1, 1}}};  // 반시계
    
    static int[][][] mcor = {{{-1, 1}, {1, 1}, {1, -1}, {-1, -1}},  // 돌면서 대각선을 지나침
                            {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}}};
    
    static int N;                            
    static int[][] Board;
    static boolean[][][] checked = new boolean[100][100][4]; // 최대 길이 100 방향 4개
    static Queue<P[]> queue = new LinkedList<>();

    static int bfs() {
        queue.offer(new P[]{new P(0, 0, RIGHT, 0), new P(0, 1, LEFT, 0)});  // 왼쪽 오른쪽 위치 저장
        checked[0][0][RIGHT] = true;
        checked[0][1][LEFT] = true;
        P[] curr = new P[2];
        P[] next = new P[2];

        while((curr = queue.poll()) != null) {
            for(int i=0; i<4; i++) {
                for(int j=0; j<2; j++) {
                    next[j] = new P(curr[j].x + moving[i][0], curr[j].y + moving[i][1], curr[j].dir, curr[j].time + 1); 
                }

                if(!isValid(next)) continue;

                for(int j=0; j<2; j++) {
                    if(next[j].x == N - 1 && next[j].y == N - 1) return next[j].time; // 도착
                    checked[next[j].x][next[j].y][next[j].dir] = true;
                }

                queue.offer(new P[] {next[0], next[1]});
            }
            for(int ccw = 0; ccw < 2; ++ccw) {    // ccw 방향 
                for(int i=0; i<2; ++i) {  // i 축
                    int ret = rotation(curr, ccw, i);
                    if(ret != 0) return ret;
                }
            }
        }
        return 0;
    }

    // 범위 및 이동 제한 체크
    static boolean isValid(P[] pt) {
        for(int i=0; i<2; i++) {
            if(pt[i].x < 0 || pt[i].x > N - 1 || pt[i].y < 0 || pt[i].y > N - 1) return false;
            if(Board[pt[i].x][pt[i].y] == 1) return false;
            if(checked[pt[i].x][pt[i].y][pt[i].dir]) return false;
        }
        return true;
    }

    static int rotation(P[] curr, int ccw, int idx) {
        P[] next = new P[2];
        int a = idx, b = (idx + 1) % 2;  // b 범위 초과 시 나머지 연산
        int dir = curr[a].dir;

        next[0] = new P(curr[a].x, curr[a].y, (curr[a].dir + 1) % 4, curr[a].time + 1);
        // 반시계는 한바퀴 - 1 이랑 같다 그러므로 % 4로 해서 반시계를 해주자
        next[0] = new P(curr[a].x, curr[a].y, (curr[a].dir + (ccw == 0 ? 1 : 3)) % 4, curr[a].time + 1);

        next[1] = new P(curr[b].x + mrot[ccw][dir][0], curr[b].y + mrot[ccw][dir][1], (curr[b].dir + (ccw == 0 ? 1 : 3)) % 4, curr[b].time + 1);

        if(!isValid(next)) return 0;
        if(Board[curr[a].x + mcor[ccw][dir][0]][curr[a].y + mcor[ccw][dir][1]] == 1) return 0;

        for(int i=0; i<2; i++) {
            if(next[1].x == N - 1 && next[i].y == N - 1) return next[i].time;
            checked[next[i].x][next[i].y][next[i].dir] = true;
        }
        queue.add(new P[] {next[0], next[1]});
        return 0;
    }

    public int solution(int[][] board) {
        Board = board.clone();
        N = board.length;
        
        return bfs();
    }
}
