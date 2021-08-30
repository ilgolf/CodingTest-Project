import java.util.HashMap;
import java.util.Map;

public class 직업군추천하기 {
    // 직업군리스트 (사전순)
    final String[] resultList = {"CONTENTS", "GAME", "HARDWARE", "PORTAL", "SI"};

    public String solution(String[] table, String[] languages, int[] preference) {
        // 테이블에 대한 정보를 저장하는 Map
        Map<String, Map<String, Integer>> map = new HashMap<>();

        for (int i = 0; i < table.length; i++) {
            String[] arr = table[i].split(" ");
            String job = arr[0]; // 직업군
            map.put(job, new HashMap<>()); // 초기화

            for (int j = 1; j < arr.length; j++) {
                map.get(job).put(arr[j], 6 - j);
            }
        }

        String answer = "";
        int max = 0;
        for (String str : resultList) {
            int sum = 0;

            Map<String, Integer> tmp = map.get(str);
            for (int i = 0; i < languages.length; i++) {
                sum += (preference[i] * tmp.getOrDefault(languages[i], 0));
            }
            if (sum > max) {
                max = sum;
                answer = str;
            }
        }
        return answer;
    }
}