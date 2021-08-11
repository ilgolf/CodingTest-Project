package Week_2;

import java.util.*;

class Solution {
    public static int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();  // 중복 불가 Set
        
        set.add(words[0]);
        
        for(int i=1; i<words.length; i++) {
            if(set.contains(words[i])) {
                return new int[]{(i % n) + 1, (i / n) + 1};   // 핵심
            }
            
            if((words[i-1].charAt(words[i-1].length()-1))!=words[i].charAt(0)) {
                return new int[]{(i % n) + 1, (i / n) + 1};
            }
            
            set.add(words[i]);
        }
        
        return new int[]{0, 0};
    }
}