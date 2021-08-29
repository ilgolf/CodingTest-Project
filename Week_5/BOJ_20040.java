import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] parent;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,m,i,a,b,x,y;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n];
		for(i=0;i<n;i++) parent[i] = i;
		for(i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			a = find(x);
			b = find(y);
			if(a==b) {
				System.out.println(i);
				return;
			}
			union(x,y);
		}
		System.out.println("0");
	}
	
	static int find(int x) {
		if(parent[x]==x) return x;
		return find(parent[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x<y) {
			parent[y] = x;
		}else {
			parent[x] = y;
		}
	}
}
