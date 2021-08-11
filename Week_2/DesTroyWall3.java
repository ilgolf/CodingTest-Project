package Week_2;

import java.io.*;
import java.util.*;

class DestroyWall3 {

    static int n, m, k;
    static char[][] map;
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static class P {
        int x;
        int y; 
        int count; // 벽 부순 갯수
        int dis; // 거리 
        boolean time;  // true 낮, false : 밤
        
        public P(int x, int y, int count, int dis, boolean time) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.dis = dis;
            this.time = time;
        } 
        
    }

    private static int bfs(){

        int[][] broken = new int[n + 1][m + 1];  // 각 위치에 부순 벽의 수를 저장


        for(int i=0; i <= n; i++){
            Arrays.fill(broken[i], Integer.MAX_VALUE);
        }
        Queue<P> queue = new LinkedList<>();
        
        queue.offer(new P(0, 0, 0, 1, true));
        broken[0][0] = 0; // 최초 위치는 벽을 부순적이 없음
        
        while(!queue.isEmpty()){
            P curr = queue.poll();
            
            if(curr.x == n && curr.y == m) {
                return curr.dis;  
            }
            
            for(int i=0; i < 4; i++){
                int nx = curr.x + moving[i][0];
                int ny = curr.y + moving[i][1];
                
                // 이미 부순 벽의 수가 더 많다면 넘어간다.
                if(nx < 0 || nx >= n + 1 || ny < 0 || ny >= m + 1|| broken[nx][ny] <= curr.count) continue;
                
                if(map[nx][ny] == '1') {

                    // 벽 카운트 최대 도달 시
                    if(curr.count >= k) continue;
                    
                    if(curr.time){
                        broken[nx][ny] = curr.count + 1;
                        queue.offer(new P(nx, ny, curr.count+1, curr.dis+1, false));
                    } else {
                        queue.offer(new P(curr.x, curr.y, curr.count, curr.dis+1, true));
                    }
                } else {
                    broken[nx][ny] = curr.count;
                    
                    // 다음 탐색 지점의 낮/밤 여부 지정
                    boolean day = curr.time ? false : true;
                    queue.offer(new P(nx, ny, curr.count, curr.dis+1, day));
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(st.nextToken()) - 1;
        m = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        map = new char[n + 1][m + 1];

        for(int i=0; i<map.length; i++) {
            map[i] = br.readLine().toCharArray();
        }

        bw.write(String.valueOf(bfs()));
        bw.close();
        br.close();
    }
}