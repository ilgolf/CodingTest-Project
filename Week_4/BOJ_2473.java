import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,i,a,b,c,l,r;
		long m,t;
		n = Integer.parseInt(br.readLine());
		long[] arr = new long[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(i=0;i<n;i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		m = Long.MAX_VALUE;
		a = b = c = 0;
		for(i=0;i<n-2;i++) {
			l = i+1;
			r = n-1;
			while(l<r) {
				t = arr[i]+arr[l]+arr[r];
				if(Math.abs(t)<m) {
					a = i;
					b = l;
					c = r;
					m = Math.abs(t);
				}
				if(arr[i]+arr[l]+arr[r]<0) {
					l++;
				}else {
					r--;
				}
			}
		}
		System.out.println(arr[a]+" "+arr[b]+" "+arr[c]);
	}
}
