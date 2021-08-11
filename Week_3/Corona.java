package Week_3;

import java.util.*;

public class Corona {

    static class P {
        int x,y;

        public P(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static char[][] map;
    static boolean[][] checked;
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static int makeArr(String[] arr) {
        map = new char[5][5];

        for(int i=0; i<map.length; i++) {
            map[i] = arr[i].toCharArray();
        }

       for(int i=0; i<map.length; i++) {
           for(int j=0; j<map[i].length; j++) {
               if(map[i][j] == 'P') {
                   if(!bfs(i, j)) {
                       return 0;
                   }
               }
           }
       }
       return 1;
    }

    static boolean bfs(int x, int y) {
        Queue<P> queue = new LinkedList<>();
        queue.offer(new P(x, y));
        checked[x][y] = true;

        while(!queue.isEmpty()) {

            P curr = queue.poll();

            for(int i=0; i<moving.length; i++) {
                int nx = curr.x + moving[i][0];
                int ny = curr.y + moving[i][1];
                int menheton = Math.abs(x - nx) + Math.abs(y - ny);

                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
                    if(!checked[nx][ny] && menheton <= 2) {
                        checked[nx][ny] = true;
                        if(map[nx][ny] == 'X') continue;
                        else if(map[nx][ny] == 'P') return false;
                        else {
                            queue.offer(new P(nx, ny));
                        }
                    }
                }
            }
        }
        return true;
    }

    public int[] solution(String[][] places) {
        int[] answer = {};

        answer = new int[places.length];

        for(int i=0; i<places.length; i++) {
            checked = new boolean[5][5];
            answer[i] = makeArr(places[i]);
        }

        return answer;
    }
}

