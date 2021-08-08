import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t,i,j,n,p,a,b;
		t = Integer.parseInt(br.readLine());
		for(i=1;i<=t;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[1200002];
			for(j=0;j<n;j++) arr[Integer.parseInt(st.nextToken())+1]++;
			for(j=1;j<=1200001;j++) arr[j] += arr[j-1];
			b = 0;
			a = 0;
			while(b<=1200001) {
				if(arr[b]-arr[a]+p>=b-a) {
					b++;
				}else {
					a++;
					b++;
				}
			}
			sb.append("#"+i+" "+(b-a-1)+"\n");
		}
		System.out.println(sb);
	}
}
