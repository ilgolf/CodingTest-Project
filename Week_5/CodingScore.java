package Week_5;

/**
 * Map<언어, 선호도> 저장 후 
 * 각 배열 돌면서 containsKey 가 참일 경우 getKey 해서 연산 후 배열에 저장
 * Comparable sort 로 정렬 후 사전 순으로 정답 리턴
 */

import java.util.*;

public class CodingScore {
    
    static class Company {
        String com;
        int score;

        public Company(String com, int score) {
            this.com = com;
            this.score = score;
        }
    }
    
    public String solution(String[] table, String[] languages, int[] preference) {
        Map<String, Integer> map = new HashMap<>();
        List<Company> result = new ArrayList<>(); // 각 연산을 구해서 저장할 거임
        int max = 0; // 정답 갱신해줄거임
        
        String answer = "";
        
        for(int i=0; i<languages.length; i++) {
            map.put(languages[i], preference[i]); // ex. PYTHON, 7
        }
        
        for(String t : table) {
            int sum = 0;
            String[] line = t.split(" ");
                
            for(int i=1; i<line.length; i++) {
                if(map.containsKey(line[i])) {
                   sum += (map.get(line[i]) * (6 - i));
                }
            }
            
            max = Math.max(max, sum);
            result.add(new Company(line[0], sum));
        }

        Collections.sort(result, (o1, o2) -> {
            if(o1.score == o2.score) {
                return o1.com.compareTo(o2.com);
            }
            return -1;
        });

        for(int i=0; i<5; i++) {
            if(result.get(i).score == max) {
                answer = result.get(i).com;
                break;
            }
        }
        
        return answer;
    }
}
