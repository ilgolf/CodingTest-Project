import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n,i,t,v;
		HashMap<Integer, Integer> map = new HashMap<>();
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		ArrayList<Integer> tmp = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(i=0;i<n;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			tmp.add(arr[i]);
		}
		Collections.sort(tmp);
		v = 0;
		for(i=0;i<n;i++) {
			t = tmp.get(i);
			if(!map.containsKey(t)) map.put(t, v++);	
		}
		for(i=0;i<n;i++) sb.append(map.get(arr[i])+" ");
		System.out.println(sb);
	}
}
