import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,m,k,i,j,t,ny,nx;
		int[] dy = {-1,1,0,0};
		int[] dx = {0,0,-1,1};
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int[][][] dir = new int[m+1][4][4];
		int[] ndir = new int[m+1];
		int[][][] map = new int[n][n][2];
		boolean[][] mapchk = new boolean[n][n];
		for(i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(j=0;j<n;j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				if(map[i][j][0]!=0) {
					map[i][j][1] = k; 
					mapchk[i][j] = true;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for(i=1;i<=m;i++) ndir[i] = Integer.parseInt(st.nextToken())-1;
		for(i=1;i<=m;i++) {
			for(j=0;j<4;j++) {
				st = new StringTokenizer(br.readLine());
				for(t=0;t<4;t++) {
					dir[i][j][t] = Integer.parseInt(st.nextToken())-1;
				}
			}
		}
		Queue<shark> q = new LinkedList<>();
		for(i=0;i<n;i++) for(j=0;j<n;j++) {
			if(map[i][j][0]!=0) {
				q.add(new shark(i, j, ndir[map[i][j][0]], map[i][j][0]));
			}
		}
		t = 0;
		while(true) {
			if(q.size()==1) {
				System.out.println(t);
				return;
			}
			t++;
			if(t==1001) {
				System.out.println("-1");
				return;
			}
			while(!q.isEmpty()) {
				shark p = q.poll();
				boolean chk = false;
				for(i=0;i<4;i++) {
					ny = p.y + dy[dir[p.lv][p.d][i]];
					nx = p.x + dx[dir[p.lv][p.d][i]];
					if(ny<0||ny>n-1||nx<0||nx>n-1) continue;
					if(!mapchk[ny][nx]) {
						map[ny][nx][0] = map[ny][nx][0]==0?p.lv:Math.min(map[ny][nx][0], p.lv);
						ndir[p.lv] = dir[p.lv][p.d][i];
						map[ny][nx][1] = k+1;
						chk = true;
						break;
					}
				}
				if(chk) continue;
				for(i=0;i<4;i++) {
					ny = p.y + dy[dir[p.lv][p.d][i]];
					nx = p.x + dx[dir[p.lv][p.d][i]];
					if(ny<0||ny>n-1||nx<0||nx>n-1) continue;
					if(map[ny][nx][0]==p.lv) {
						ndir[p.lv] = dir[p.lv][p.d][i];
						map[ny][nx][1] = k+1;
						break;
					}
				}
			}
			for(i=0;i<n;i++) for(j=0;j<n;j++) {
				map[i][j][1]--;
				mapchk[i][j] = map[i][j][1]>0?true:false;
				if(map[i][j][1]==0) {
					map[i][j][0] = 0;
				}else if(map[i][j][1]==k) {
					q.add(new shark(i, j, ndir[map[i][j][0]], map[i][j][0]));
				}
			}
		}
	}
	public static class shark {
		int y;
		int x;
		int d;
		int lv;
		
		public shark(int y, int x, int d, int lv) {
			this.y = y;
			this.x = x;
			this.d = d;
			this.lv = lv;
		}
	}
}
