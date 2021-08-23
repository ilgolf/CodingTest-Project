import java.io.IOException;
import java.util.HashMap;

public class Main {
	static class Salesman {
		String referralName;
		double amount;

		public Salesman(String referralName, double amount) {
			// TODO Auto-generated constructor stub
			this.referralName = referralName;
			this.amount = amount;
		}
	}

	static HashMap<String, Salesman> map;

	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];
		map = new HashMap<String, Salesman>();
		map.put("-", null);
		for (int i = 0; i < enroll.length; i++)
			map.put(enroll[i], new Salesman(referral[i], 0));
		for (int i = 0; i < seller.length; i++) {
			String now = seller[i];
			int profit = 100 * amount[i];
			while (!now.equals("-")) {
				int referralProfit = profit / 10;
				int myProfit = profit - referralProfit;
				map.put(now, new Salesman(map.get(now).referralName, map.get(now).amount+myProfit));
				now = map.get(now).referralName;
				profit /= 10;
				if (profit < 1)
					break;
				System.out.println();
			}
		}
		for (int i = 0; i < enroll.length; i++)
			answer[i] = (int) map.get(enroll[i]).amount;
		return answer;
	}
}