// JAVA 11, 시간 : 168ms, 메모리 15000KB
// DFS를 이용한 백트래킹으로 문제 해결
// DFS vs 백트래킹
// DFS : 깊이 우선 탐색하여 모든 노드를 방문하는 것이 목표
// 백트래킹 : 불필요한 탐색을 하지 않기 위해, 유망하지 않은 경우의 수를 줄이는 것(가지치기)을 목표로 한다.

import java.io.*;
import java.util.StringTokenizer;


public class Main {

    // R : 맵의 세로 크기, C : 맵의 가로 크기 , T : 이동 시간
    public static int R,C, T;
    public static char[][] room;
    public static int start_x=-1, start_y = -1;
    public static int[][] move = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        room = new char[R][C];

        for (int x = 0; x < R; x++) {
            String temp = br.readLine();

            for (int y = 0; y < C; y++) {
                room[x][y] = temp.charAt(y);

                // 가희 위치 체크
                if (room[x][y] == 'G') {
                    start_x = x;
                    start_y = y;
                }
            }
        }

        bw.write(String.valueOf(dfs(start_x, start_y, T)));
        bw.flush();


    }

    public static int dfs(int x, int y,int remain) {
        if (remain == 0) {
            return 0;
        }

        int ret = 0;
        char prev;
        int plus;

        for (int i = 0; i < 4; i++) {

            // 상하좌우 이동
            int next_x = x + move[i][0];
            int next_y = y + move[i][1];
            plus = 0;

            // 범위벗어나면 이동 X
            if (next_x < 0 || next_y < 0 || next_x >= R || next_y >= C) {
                continue;
            }

            // 장애물이면 이동 X
            if(room[next_x][next_y]=='#')
                continue;


            // 고구마가 있다면 plus 1로 설정, 고구마 먹은 횟수를 하나 증가시켜줌
            plus = room[next_x][next_y] == 'S' ? 1 : 0;

            // 고구마는 한번만 먹을 수 있기에 지나갈때는 고구마 먹은거 체크해줌
            prev = room[next_x][next_y];
            room[next_x][next_y] = '.';

            ret = Math.max(ret, dfs(next_x, next_y, remain - 1) + plus);

            // 돌아갈때는 다시 원상 복귀
            room[next_x][next_y] = prev;
        }

        return ret;
    }


}
