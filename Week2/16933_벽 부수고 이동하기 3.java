import java.util.LinkedList;
import java.util.*;

class Main{
    static int n,m,k;
    static int[][][][] ch;
    static int[][] arr;
    static int answer = Integer.MAX_VALUE;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        arr = new int[n][m];
        ch = new int[n][m][2][k+1];

        for(int i=0;i<n;i++){
            char[] temp = sc.next().toCharArray();
            for(int j=0;j<m;j++){
                arr[i][j] = temp[j] - '0';
            }
        }
        ch[0][0][0][0] = 1; // (0,0) night 0 wall
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0,0,0));

        while(!q.isEmpty()){
            Pair p = q.poll();

            for(int i=0;i<4;i++){
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(nx<0 || nx>=n || ny<0 || ny>=m) continue;

                // 현재 밤인경우
                if(p.day == 1){
                    // 현재 위치를 낮으로 변경
                    if(ch[p.x][p.y][0][p.k]==0){
                        ch[p.x][p.y][0][p.k] = ch[p.x][p.y][1][p.k]+1;
                        q.add(new Pair(p.x,p.y,0,p.k));
                    }

                    // 다음 위치를 낮으로 변경
                    if(arr[nx][ny] == 0 && ch[nx][ny][p.day-1][p.k]==0){
                        ch[nx][ny][0][p.k] = ch[p.x][p.y][1][p.k] + 1;
                        q.add(new Pair(nx,ny,0,p.k));
                    }
                }
                // 현재 낮인 경우
                else{
                    // 현재 위치를 밤으로 변경
                    if(ch[p.x][p.y][1][p.k]==0){
                        ch[p.x][p.y][1][p.k] = ch[p.x][p.y][0][p.k]+1;
                        q.add(new Pair(p.x,p.y,1,p.k));
                    }

                    // 밤으로 변경
                    if(arr[nx][ny] == 0 && ch[nx][ny][1][p.k]==0){
                        ch[nx][ny][1][p.k] = ch[p.x][p.y][0][p.k] + 1;
                        q.add(new Pair(nx,ny,1,p.k));
                    }

                    // 벽 부시기
                    if(arr[nx][ny] == 1 && p.k + 1 <= k && ch[nx][ny][1][p.k+1]==0){
                        ch[nx][ny][1][p.k+1] = ch[p.x][p.y][0][p.k] + 1;
                        q.add(new Pair(nx,ny,1,p.k+1));
                    }
                }
            }
        }
        for(int i=0;i<k+1;i++){
            if(ch[n-1][m-1][0][i]!=0)
            answer = Math.min(answer,ch[n-1][m-1][0][i]);
            if(ch[n-1][m-1][1][i]!=0)
            answer = Math.min(answer,ch[n-1][m-1][1][i]);
        }
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }
    static class Pair{
        int x,y,day,k;

        public Pair(int x, int y,int day,int k) {
            this.x = x;
            this.y = y;
            this.day = day;
            this.k = k;
        }
    }
}