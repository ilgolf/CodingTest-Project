public class 문자열압축 {
    public int solution(String s) {
        int answer = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            String str = s;
            String pattern = "";
            String result = "";
            int cnt = 1;
            while (str.length() - i >= 0) {
                pattern = str.substring(0, i);
                String tmp = str.substring(i);

                if (tmp.indexOf(pattern) == 0) {
                    cnt++;
                    str = str.substring(i);

                } else if (tmp.indexOf(pattern) != 0 && cnt != 1) {
                    result += cnt + pattern;
                    cnt = 1;
                    str = str.substring(i);

                } else if (tmp.indexOf(pattern) != 0 && cnt == 1) {
                    result += pattern;
                    str = str.substring(i);
                    cnt = 1;
                }
            }
            result += str;
            answer = Math.min(answer, result.length());
        }
        return answer;
    }
}
