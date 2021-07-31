import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int n,k,s;
	static int[] arr;
	static boolean[] visited;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		s = 0;
		arr = new int[n];
		visited = new boolean[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
		dfs(0,0);
		System.out.println(s);
	}
	
	static void dfs(int d, int sum) {
		if(d==n) {
			s++;
			return;
		}
		for(int i=0;i<n;i++) {
			if(!visited[i]&&sum+arr[i]-k>=0) {
				visited[i] = true;
				dfs(d+1,sum+arr[i]-k);
				visited[i] = false;
			}
		}
	}
}
