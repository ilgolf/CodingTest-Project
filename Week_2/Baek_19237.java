package Week_2;

import java.io.*;
import java.util.*;

class Baek_19237 {

    static class Shark {
        int id, x, y, dir;
        int[][] priority = new int[5][5];

        Shark() {

        }

        int findNextDir(Set<Integer> candidates) {
            for (int i = 1; i < 5; i++) {
                if (candidates.contains(priority[dir][i])) {
                    return priority[dir][i];
                }
            }
            return 0;
        }
    }
    static int n,m,k;
    static int[][] map = new int[21][21];
    static int[][] smell = new int[21][21];
    static int[][] leftTime = new int[21][21];
    static Map<Integer, Shark> hm = new HashMap<>();
    static int time = 0;

    static void solution() {
        while(time ++ < 1000) {
            moveShark();
            decreaseSmellTime();
            createSmell();

            if(hm.size() == 1) {
                System.out.println(time);
                return;
            }
        }

        System.out.println(-1);
    }

    static void moveShark() {
        int[][] moving = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Integer> willRemove = new LinkedList<Integer>();

        for (Integer id : hm.keySet()) {
            Set<Integer> noSmellSet = new HashSet<>();
            Set<Integer> mySmellSet = new HashSet<>();
            Shark s = hm.get(id);

            for (int i = 0; i < 4; i++) {
                int nx = s.x + moving[i][0];
                int ny = s.y + moving[i][1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                if (smell[nx][ny] == 0) {
                    noSmellSet.add(i + 1);
                } else if (smell[nx][ny] == s.id) {
                    mySmellSet.add(i + 1);
                }
            }

            // 냄새 없는 곳부터 스캔하고 자기 냄새 있는 곳을 스캔해서 이동할 방향 구하기
            int nextDir = s.findNextDir(noSmellSet);

            if (nextDir == 0) {
                nextDir = s.findNextDir(mySmellSet);
            }

            // 상어 이동
            map[s.x][s.y] = 0;
            switch (nextDir) {
                case 1 :
                    s.x --;
                    break;
                case 2 :
                    s.x ++;
                    break;
                case 3 :
                    s.y --;
                    break;
                case 4 :
                    s.y ++;
                    break;
            }
            
            // 이동할 위치에 상어 있으면 경쟁 후 작은 번호가 승리
            if (map[s.x][s.y] == 0 || s.id < map[s.x][s.y]) {
                map[s.x][s.y] = s.id;
                s.dir = nextDir;
            } else {
                willRemove.add(s.id);
            }
        }

        while (!willRemove.isEmpty()) {
            hm.remove(willRemove.poll());
        }
    }

    static void decreaseSmellTime() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(leftTime[i][j] == 0) {
                    continue;
                }

                leftTime[i][j] --;

                if(leftTime[i][j] == 0) {
                    smell[i][j] = 0;
                }
            }
        }
    }

    static void createSmell() {
        for(Integer id : hm.keySet()) {
            Shark s = hm.get(id);

            smell[s.x][s.y] = s.id;
            leftTime[s.x][s.y] = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > 0) {
                    Shark s = new Shark();
                    s.id = map[i][j];
                    s.x = i;
                    s.y = j;
                    hm.put(s.id, s);
                    
                    smell[i][j] = s.id;
                    leftTime[i][j] = k;
                }
            }
        }

        // direction of sharks
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            Shark s = hm.get(i + 1);
            s.dir = Integer.parseInt(st.nextToken());
        }

        // priority of sharks
        for (int i = 0; i < m; i++) {
            Shark s = hm.get(i + 1);

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());

                for (int z = 0; z < 4; z++) {
                    s.priority[j + 1][z + 1] = Integer.parseInt(st.nextToken());
                }
            }
        }

        // solution
        solution();
    }
}