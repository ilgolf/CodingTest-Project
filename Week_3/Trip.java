package Week_3;

import java.util.*;

public class Trip {

    static String[][] t;
    static List<String> list;
    static boolean[] checked;

    static void dfs(int count, String prev, String road) {
        
        if(count == t.length) {
            list.add(road); // 마지막에 저장
            return;
        }

        for(int i=0; i<t.length; i++) {
            if(t[i][0].equals(prev) && !checked[i]) {
                checked[i] = true; 
                dfs(count + 1, t[i][1], road + " " + t[i][1]);  // 도착지 계속해서 저장
                checked[i] = false; 
            }
        }

        return;
    }
    
    public static String[] solution(String[][] tickets) {
        list = new ArrayList<>();

        t = tickets.clone();
        checked = new boolean[tickets.length];

        dfs(0, "ICN", "ICN");
        
        Collections.sort(list); // 정렬하여 사전순으로 출력

        String[] answer = list.get(0).split(" ");
        
        return answer;
    }
}