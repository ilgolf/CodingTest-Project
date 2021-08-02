import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder builder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int[] nums = new int[N];
		for(int i=0;i<N;i++)
			nums[i] = Integer.parseInt(tokenizer.nextToken());

		int[] temo_nums=nums.clone();
		Arrays.sort(nums);
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		int j=0;
		for(int i=0;i<N;i++)
			if(!map.containsKey(nums[i]))
			{
				map.put(nums[i], j);
				j++;
			}
		for(int i=0;i<N;i++)
			builder.append(map.get(temo_nums[i])).append(" ");
		System.out.println(builder);
	}
}