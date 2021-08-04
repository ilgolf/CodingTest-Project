import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n,q,i,ans,l,r,m,lv;
		long t,a,b;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		ArrayList[] log = new ArrayList[7];
		for(i=1;i<=6;i++) log[i] = new ArrayList<Long>();
		while(n-->0) {
			st = new StringTokenizer(br.readLine().replaceAll("[-: ]", ""),"#");
			t = Long.parseLong(st.nextToken());
			log[Integer.parseInt(st.nextToken())].add(t);
		}
		while(q-->0) {
			st = new StringTokenizer(br.readLine().replaceAll("[-: ]", ""),"#");
			a = Long.parseLong(st.nextToken());
			b = Long.parseLong(st.nextToken());
			lv = Integer.parseInt(st.nextToken());
			ans = 0;
			for(i=lv;i<=6;i++) {
				l = 0;
				r = log[i].size();
				while(l<r) {
					m = (l+r)/2;
					if((long)log[i].get(m)>=a) {
						r = m;
					}else {
						l = m + 1;
					}
				}
				ans -= r;
				l = 0;
				r = log[i].size();
				while(l<r) {
					m = (l+r)/2;
					if((long)log[i].get(m)>b) {
						r = m;
					}else {
						l = m + 1;
					}
				}
				ans += r;
			}
			sb.append(ans+"\n");
		}
		System.out.println(sb);
	}
}
