package 신규아이디추천;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder result = new StringBuilder();
	static String change_number(int[] arr, int r) {
		StringBuilder temp = new StringBuilder();
		for(int i = 0; i < r; i++)
			temp.append(arr[i]+" ");
		temp.append("\n");
		return temp.toString();

	}

	static void per2(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {

		if(depth == r) {
			result.append(change_number(output, depth));
			return;
		}

		for(int i = 0; i < n; i++) {
			if(visited[i] != true) {
				visited[i] = true;
				output[depth] = arr[i];
				per2(arr, output, visited, depth + 1, n, r);
				visited[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int n = Integer.parseInt(tokenizer.nextToken());
		int m = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(reader.readLine());
		int[] arr = new int[n];
		int[] output = new int[n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(tokenizer.nextToken());

		Arrays.sort(arr);
		per2(arr, output, visited, 0, n, m);
		System.out.println(result);
	}
}