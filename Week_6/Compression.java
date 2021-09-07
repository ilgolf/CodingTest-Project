package Week_6;

/**
 *
 * "ababcdcdababcdcd" -> ab 2개 2ab start = 0
 * cd 2개 2cd start = 4 "2ab2cd"
 * ab 2개 2ab start = 8 "2ab2cd2ab"
 * cd 2개 2cd start = 12 "2ab2cd2ab2cd"
 *
 */

public class Compression {

    static String processHit(int hit) {
        return hit > 1 ? String.valueOf(hit) : "";
    }

    public int solution(String s) {
        if(s.length() == 1) return 1;

        int answer = 1001;

        for (int i = 1; i <= s.length() / 2; i++) {
            String now, next = "", result = "";
            int hit = 1;
            for (int j = 0; j <= s.length() / i; j++) {
                int start = j * i;
                int end = Math.min(i * (j + 1), s.length());

                now = next;
                next = s.substring(start, end);

                if(now.equals(next)) {
                    hit ++;
                } else {
                    result += (processHit(hit) + now);
                    hit = 1;
                }
            }

            result += (processHit(hit) + next);
            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}
