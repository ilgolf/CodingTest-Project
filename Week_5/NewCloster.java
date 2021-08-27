package Week_5;

/**
 * 
 * Set (중복  count) 실패
 * map (getorDefault연산 X) 실패 
 * ArrayList에 각각 집합들을 만들어 준 후에
 * 삭제해 가면서 합집합 교집합을 따로 생성 - > 중복 문제 해결
 * 
 */

import java.util.*;

public class NewCloster {

    static List<String> list1 = new ArrayList<>();
    static List<String> list2 = new ArrayList<>();
    
    static void getString(char start, char end, List<String> list) {
        if(start >= 'a' && end <= 'z' && end >= 'a' && start <= 'z') {
            list.add(start + "" + end);
        }
    }

    public int solution(String str1, String str2) {
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        for(int i=0; i<str1.length() - 1; i++) {
            getString(str1.charAt(i), str1.charAt(i + 1), list1);
        }

        for(int i=0; i<str2.length() - 1; i++) {
            getString(str2.charAt(i), str2.charAt(i + 1), list2);
        }

        Collections.sort(list1);
        Collections.sort(list2);

        List<String> intersection = new ArrayList<>();
        List<String> union = new ArrayList<>();

        for(String s : list1) {
            if(list2.remove(s)) {
                intersection.add(s);
            }
            union.add(s);
        }

        for(String s : list2) {
            union.add(s);
        }

        double result = union.size() == 0 ? 1 : (double)intersection.size() / (double)union.size();

        return (int)(result * 65536);
    }
}