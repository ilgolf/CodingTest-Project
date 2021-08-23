import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		long[] arr = new long[N];

		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(tokenizer.nextToken());
		}
		Arrays.sort(arr);
		long min =  3000000000L;

		int ansLeft = 0;
		int ansMid = 0;
		int ansRight = 0;

		for(int i=0; i < N-1; i++) {
			int j = i+1;
			int k = N-1;
			while(true) {
				if(j==k) break;
				long sum = arr[i] + arr[j] + arr[k];
				if(Math.abs(sum) <= min) {
					ansLeft = i;
					ansMid = j;
					ansRight = k;
					min = Math.abs(sum);
				}
				if(sum < 0) {
					j++;
				}else {
					k--;
				}
			}
		}
		System.out.println(arr[ansLeft] + " " +arr[ansMid]+" "+ arr[ansRight]);
	}
}