package Week_3;

import java.io.*;
import java.util.*;

public class Baek_21772 {  

    static class Gahee {
        int x,y,depth;

        public Gahee(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static int n, m, t;
    static char[][] map;
    static boolean[][] checked;
    static int result = 0;
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void find(Gahee gh, int count) {
        if(gh.depth == t) {
            result = Math.max(result, count);
            return;
        } 
        int x = gh.x;
        int y = gh.y;
        checked[x][y] = true;

        for(int i=0; i<4; i++) {
            int nx = x + moving[i][0];
            int ny = y + moving[i][1];

            if(!isValid(nx, ny)) continue;

            if(map[nx][ny] == '#') continue;

            if(checked[nx][ny]) {
                find(new Gahee(nx, ny, gh.depth + 1), count);
            } else {
                if(map[nx][ny] == 'S') {
                    find(new Gahee(nx, ny, gh.depth + 1), count + 1);
                    checked[nx][ny] = false;
                } else {
                    find(new Gahee(nx, ny, gh.depth + 1), count);
                }
            }
        }
    }

    static boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        Gahee gh = null;

        map = new char[n][m];
        checked = new boolean[n][m];

        for(int i=0; i<n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j] == 'G') {
                    gh = new Gahee(i, j, 0);
                    break;
                }
            }
        }
        
        find(gh, 0);
        System.out.println(result);
    }
}
