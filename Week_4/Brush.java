package Week_4;

import java.util.*;

public class Brush {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, String> child1 = new HashMap<>();  // 부모 저장
        Map<String, Integer> memberIdx = new HashMap<>(); // 위치 저장

        for(int i=0; i<enroll.length; i++) {
            child1.put(enroll[i], referral[i]);
            memberIdx.put(enroll[i], i);
        }

        for(int i=0; i<seller.length; i++) {
            String now = seller[i];
            int money = 100 * amount[i];

            while(!now.equals("-")) {
                int amortization = money / 10;
                int lastMoney = money - amortization;

                // 금액을 자신의 인덱스에 더해줌
                answer[memberIdx.get(now)] += lastMoney;

                now = child1.get(now);
                money /= 10;

                if(money < 1) break;
            }
        }

        return answer;
    }
}
