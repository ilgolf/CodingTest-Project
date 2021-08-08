import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,m,i,k;
		long left,right,mid,t;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[] arr = new int[m];
		st = new StringTokenizer(br.readLine());
		for(i=0;i<m;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		left = 0;
		right = 60000000001L;
		while(left<=right) {
			t = k = 0;
			mid = (left+right)/2;
			for(i=0;i<m;i++) {
				t += (mid+arr[i]-1)/arr[i];
				if(mid%arr[i]==0) k++;
			}
			if(t<n) {
				if(t+k>=n) {
					for(i=0;i<m;i++) {
						if(mid%arr[i]==0) t++;
						if(t==n) {
							System.out.println(i+1);
							return;
						}
					}
				}
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
	}
}
