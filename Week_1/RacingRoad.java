import java.util.*;

class P {
    int x, y, dir, cost;
    
    public P(int x, int y, int cost, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cost = cost;
    }
}

class RacingRoad {
    
    private static int[][] map;
    private static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static int answer;
    private static int n;
    
    private static void bfs(int x, int y, int cost, int dir) {
        Queue<P> queue = new LinkedList<>();
        queue.offer(new P(x, y, cost, dir));
        map[x][y] = 1;
        
        while(!queue.isEmpty()) {
            P cur = queue.poll();
            
            if(cur.x == n - 1 && cur.y == n - 1) {
                answer = Math.min(answer, cur.cost);
                continue;
            }
            
            for(int i=0; i<moving.length; i++) {
                int nx = cur.x + moving[i][0];
                int ny = cur.y + moving[i][1];
                
                if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if(map[nx][ny] != 1) {
                        int nCost = 0;
                        
                        if(cur.dir == -1 || cur.dir == i) {
                            nCost = cur.cost + 100;
                        } 
                        else if(cur.dir != i) {
                            nCost = cur.cost + 600;
                        }
                        
                        if(map[nx][ny] == 0) {
                            map[nx][ny] = nCost;
                            queue.offer(new P(nx, ny, nCost, i));
                        } 
                        else if(map[nx][ny] >= nCost) {
                            map[nx][ny] = nCost;
                            queue.offer(new P(nx, ny, nCost, i));
                        }
                    }
                }
            }
        }
    }
    
    public int solution(int[][] board) {
        answer = (int)1e9;
        n = board.length;
        map = board;
            
        bfs(0, 0, 0, -1);
        return answer;
    }
}