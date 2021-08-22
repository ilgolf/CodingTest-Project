import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,l,i,j,k,t,s,e,cnt,sz,qsz,ssz,num1,num2;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] stop = new ArrayList[l+1];
		for(i=1;i<=l;i++) stop[i] = new ArrayList<Integer>();
		ArrayList<Integer>[] station = new ArrayList[n+1];
		for(i=1;i<=n;i++) station[i] = new ArrayList<Integer>();
		for(i=1;i<=l;i++) {
			st = new StringTokenizer(br.readLine());
			while(true) {
				t = Integer.parseInt(st.nextToken());
				if(t==-1) break;
				stop[i].add(t);
				station[t].add(i);
			}
		}
		Queue<Integer> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		cnt = 0;
		boolean[] visited = new boolean[l+1];
		boolean[] visiteds = new boolean[n+1];
		sz = station[s].size();
		for(i=0;i<sz;i++) {
			q.add(station[s].get(i));
			visited[station[s].get(i)] = true;
		}
		while(!q.isEmpty()) {
			sz = q.size();
			for(i=0;i<sz;i++) {
				t = q.poll();
				qsz = stop[t].size();
				for(j=0;j<qsz;j++) {
					if(stop[t].get(j)==e) {
						System.out.println(cnt);
						return;
					}else {
						num1 = stop[t].get(j);
						if(visiteds[num1]) continue;
						visiteds[num1] = true;
						ssz = station[num1].size();
						for(k=0;k<ssz;k++) {
							num2 = station[num1].get(k);
							if(!visited[num2]) {
								visited[num2] = true;
								q.add(num2);
							}
						}
					}
				}
			}
			cnt++;
		}
		System.out.println("-1");
	}
}
