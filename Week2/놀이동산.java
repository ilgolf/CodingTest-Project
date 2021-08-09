import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static long binarySearch(int[] arr, int n, int m, long max) {
		long people, num = 0,mid_ans=max, ans=0;
		long start = 1;
		long end = max;
		long mid = (start + end) / 2;
		while (start <= end) {
			people = 0;
			num = max;
			for (int i = 0; i < arr.length; i++) {
				people+=(((mid-1)/arr[i])+1);

			}
			for (int i = 0; i < arr.length; i++) {
				if(mid%arr[i]==0)
					people++;
				if(people==n)
				{
					num=i+1;
					break;
				}

			}
			//System.out.println(start+" "+end+" "+mid+" "+people+" "+num);
			if (people >= n) {
				if(mid<mid_ans)
				{
					mid_ans=mid;
					ans = num;
				}
				end = mid-1;
			} else {
				start = mid + 1;
			}
			mid = (start + end) / 2;
		}
		return ans;
	}

	public static void main(String[] agrs) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(reader.readLine());
		int[] arr = new int[M];
		long max = -1;
		for (int i = 0; i < M; i++) {
			arr[i] = Integer.parseInt(tokenizer.nextToken());
			max = Math.max(max, arr[i]);
		}
		max =(N / M) * max;
		if(N<=M)
			System.out.print(N);
		else
			System.out.print(binarySearch(arr, N, M, max));

	}
}