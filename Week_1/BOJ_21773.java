import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n,m,a,b,c;
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
        PriorityQueue<process> q = new PriorityQueue<>();
        while(m-->0) {
        	st = new StringTokenizer(br.readLine());
        	a = Integer.parseInt(st.nextToken());
        	b = Integer.parseInt(st.nextToken());
        	c = Integer.parseInt(st.nextToken());
        	q.add(new process(a,b,c));
        }
        while(n-->0) {
        	process p = q.poll();
        	sb.append(p.id+"\n");
        	if(p.t>1) q.add(new process(p.id,p.t-1,p.p-1));
        }
        System.out.println(sb);
	}
	
	public static class process implements Comparable<process> {
		int id;
		int t;
		int p;
		
		public process(int id, int t, int p) {
			this.id = id;
			this.t = t;
			this.p = p;
		}
		@Override
		public int compareTo(process o) {
			if(this.p!=o.p) return o.p-this.p;
			return this.id-o.id;
		}
	}
}
