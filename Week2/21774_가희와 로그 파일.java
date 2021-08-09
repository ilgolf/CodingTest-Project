package 신규아이디추천;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAX = 1000001;

	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int L = Integer.parseInt(tokenizer.nextToken()); // 로그
		int Q = Integer.parseInt(tokenizer.nextToken()); // 로그
		String[][] logs = new String[L][2];
		String[][] queries = new String[Q][3];
		for(int i = 0; i<L; i++)
			logs[i] = reader.readLine().split("#");
		for(int i = 0; i<Q; i++) {
			queries[i] = reader.readLine().split("#");
			int start=0;
			int end=L;
			int mid=0;
			while(start>end)
			{
				mid = (start+end)/2;
				
			}
		}
		


	}
}
