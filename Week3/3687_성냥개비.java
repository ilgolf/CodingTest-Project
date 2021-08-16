package 신규아이디추천;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(reader.readLine());
		String[] maxArr = new String[101];
		long[] minArr = new long[101];
		Arrays.fill(minArr, Long.MAX_VALUE);
		maxArr[2] = "1";
		maxArr[3] = "7";
		minArr[2] = 1;
		minArr[3] = 7;
		minArr[4] = 4;
		minArr[5] = 2;
		minArr[6] = 6;
		minArr[7] = 8;
		minArr[8] = 10;
		int[] arr = { 2, 2, 5, 5, 5, 6, 7 };
		int index = 0;
		String[] add = { "1", "7", "4", "2", "0", "8" };

		for (int i = 9; i <= 100; i++) {
			for (int j = 2; j <= 7; j++) {
				String line = minArr[i - j] + add[j - 2];
				minArr[i] = Math.min(Long.parseLong(line), minArr[i]);
				System.out.println(i+" "+minArr[num]);
			}
		}
		for (int i = 4; i <= 100; i++) {
			maxArr[i] = maxArr[i - 2] + maxArr[2];
		}
		for (int i = 0; i < T; i++) {
			int num = Integer.parseInt(reader.readLine());
			System.out.println(minArr[num] + " " + maxArr[num]);
		}
	}
}