package code;

import java.util.*;

public class visitlength {

    static int[][] map = new int[10][10];
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // up 0, down 1, left 2, right 3
    static boolean[][] checked = new boolean[10][10];
    static Queue<P> queue = new LinkedList<>();


    static class P {
        int x,y,dir;

        public P(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    

    public String solution(int n, int k, String[] cmd) {
        String answer = "";

        

        return answer;
    }
}
