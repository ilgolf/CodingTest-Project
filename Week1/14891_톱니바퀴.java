package 신규아이디추천;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] rotate_left(int[] arr) {
		int[] temp = new int[arr.length];
		int n = arr.length - 1;
		for (int i = 0; i < n; i++)
			temp[i] = arr[i + 1];
		temp[n] = arr[0];
		return temp;

	}

	public static int[] rotate_right(int[] arr) {
		int[] temp = new int[arr.length];
		int n = arr.length - 1;
		for (int i = n; i > 0; i--)
			temp[i] = arr[i - 1];
		temp[0] = arr[n];
		return temp;
	}

	public static void main(String[] agrs) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = 4; // 톱니바퀴 개수
		int M = 8; // 톱니바퀴 각각 8개의 톱니를 가지고 있다.
		int[][] arr = new int[4][8];
		StringTokenizer tokenizer;
		for (int i = 0; i < N; i++) {
			String s = reader.readLine();
			for (int j = 0; j < M; j++)
				arr[i][j] = s.charAt(j) - '0';

		}
		int K = Integer.parseInt(reader.readLine());
		for (int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int num = Integer.parseInt(tokenizer.nextToken()) - 1; // 회전시킨 톱니바퀴의 번호
			int dir = Integer.parseInt(tokenizer.nextToken()); // 방향
			int[] d = new int[N];
			d[num] = dir;
			// 오른쪽
			for (int j = num + 1; j < N; j++) {
				if (arr[j - 1][2] != arr[j][6])
					d[j] = -d[j - 1];
				else
					break;
			}
			// 왼쪽
			for (int j = num - 1; j >= 0; j--) {
				if (arr[j + 1][6] != arr[j][2])
					d[j] = -d[j + 1];
				else
					break;
			}
			for (int j = 0; j < N; j++) {
				if(d[j]==-1)
					arr[j] = rotate_left(arr[j]);
				else if(d[j]==1)
					arr[j] = rotate_right(arr[j]);
			}
		}
		double answer = 0;
		while (--N >= 0) {
			if (arr[N][0] == 1)
				answer += Math.pow(2, N);
		}
		System.out.println((int)answer);
	}
}