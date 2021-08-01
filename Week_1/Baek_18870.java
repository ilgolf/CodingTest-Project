import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Integer, Integer> map = new HashMap<>();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int[] temp = arr.clone();
		
		
		Arrays.sort(arr);
		
		int k = 0;
		for(int val : arr) {
			if(!map.containsKey(val)) {
				map.put(val, k++);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int val : temp) {
			sb.append(map.get(val)).append(" ");
		}
		
		System.out.println(sb);
	}
}
