import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class node{
		int y,x,w,d;
		
		public node(int y, int x, int w, int d) {
			this.y = y;
			this.x = x;
			this.w = w;
			this.d = d;
		}
	}
	
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  		StringTokenizer st = new StringTokenizer(br.readLine());
  		int n,m,k,s,sz,ny,nx,y,x,w,d;
  		n = Integer.parseInt(st.nextToken());
  		m = Integer.parseInt(st.nextToken());
  		k = Integer.parseInt(st.nextToken());
  		int[] dy = {1,0,-1,0};
  		int[] dx = {0,1,0,-1};
  		boolean[][][][] visited = new boolean[n][m][k+1][2];
  		boolean[][] map = new boolean[n][m];
  		for(int i=0;i<n;i++) {
  			String str = br.readLine();
  			for(int j=0;j<m;j++) if(str.charAt(j)=='1') map[i][j] = true;
  		}
        Queue<node> q = new LinkedList<>();
        q.add(new node(0,0,k,0));
        visited[0][0][k][0] = true;
        s = 0;
        while(true) {
        	if(q.isEmpty()) break;
        	sz = q.size();
        	s++;
        	for(int i=0;i<sz;i++) {
        		node N = q.poll();
        		y = N.y;
        		x = N.x;
        		w = N.w;
        		d = N.d;
        		if(y==n-1&&x==m-1) {
        			System.out.println(s);
        			return;
        		}
        		for(int j=0;j<4;j++) {
        			ny = y + dy[j];
        			nx = x + dx[j];
        			if(ny<0||ny>n-1||nx<0||nx>m-1) continue;
        			if(visited[ny][nx][w][(d+1)%2]) continue;
        			if(map[ny][nx]) {
        				if(w==0||d==1) continue;
        				visited[ny][nx][w-1][1] = true;
        				q.add(new node(ny,nx,w-1,1));
        			}else {
        				visited[ny][nx][w][(d+1)%2] = true;
        				q.add(new node(ny,nx,w,(d+1)%2));
        			}
        		}
        		if(!visited[y][x][w][(d+1)%2]) {
        			visited[y][x][w][(d+1)%2] = true;
        			q.add(new node(y,x,w,(d+1)%2));
        		}
        	}
        }
        System.out.println("-1");
    }
}
