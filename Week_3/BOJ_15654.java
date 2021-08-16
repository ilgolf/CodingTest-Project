import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer> arr = new ArrayList<Integer>();
	static int[] list;
	static boolean[] chk;
	static int n,m;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		chk = new boolean[n];
		list = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) arr.add(Integer.parseInt(st.nextToken()));
		Collections.sort(arr);
		btr(0);
		System.out.println(sb);
	}
	
	static void btr(int k) {
		if(k==m) {
			for(int i=0;i<m;i++) sb.append(list[i]+" ");
			sb.append("\n");
			return;
		}
		for(int i=0;i<n;i++) {
			if(!chk[i]) {
				chk[i] = true;
				list[k] = arr.get(i);
				btr(k+1);
				chk[i] = false;
			}
		}
	}
}
