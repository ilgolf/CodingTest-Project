import java.util.*;
class Solution
{
	static final int MAX = 1000001;
	static boolean[] c;
	static int N, P, max;

	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt();
			P = sc.nextInt();
			c = new boolean[1000001];
			int last=0;
			for (int i = 0; i < N; i++) {
				int tempNum = sc.nextInt();
				last = Math.max(last, tempNum);
				c[tempNum] = true;
			}
			max = P+1;
			search(last);
			System.out.println("#" + test_case + " " + max);
		}
	}

	public static void search(int last) {
		int start = 1;
		int end = 1;
		int num = 0;
		while(end<last+1) {
			if(c[end]) {
				num++;
				end++;
				max=Math.max(max, num);
			}
			else {
				if(P==0) {
					max=Math.max(max, num);
					if(!c[start]) P++;
					start++;
					num--;
				}
				else {
					P--;
					num++;
					end++;
				}
			}
		}
	}
}