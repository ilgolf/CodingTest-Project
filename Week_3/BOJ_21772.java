import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int y,x,r,c,t,ans,s;
	static int[][] map;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	static boolean[][] chk;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int i,j;
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		ans = s = 0;
		for(i=0;i<r;i++) {
			String str = br.readLine();
			for(j=0;j<c;j++) {
				if(str.charAt(j)=='G') {
					y = i;
					x = j;
				}else if(str.charAt(j)=='S') {
					map[i][j] = 1;
				}else if(str.charAt(j)=='#') {
					map[i][j] = -1;
				}
			}
		}
		dfs(y,x,0);
		System.out.println(ans);
	}
	
	public static void dfs(int y, int x, int k) {
		if(k==t) return;
		for(int i=0;i<4;i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny<0||ny>r-1||nx<0||nx>c-1) continue;
			if(map[ny][nx]==0) {
				dfs(ny,nx,k+1);
			}else if(map[ny][nx]==1) {
				ans = Math.max(ans, ++s);
				map[ny][nx] = 0;
				dfs(ny,nx,k+1);
				map[ny][nx] = 1;
				s--;
			}
		}
	}
}
