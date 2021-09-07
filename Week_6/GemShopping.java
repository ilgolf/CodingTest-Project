package Week_6;

/**
 *
 * 종류를 저장하는 Set정렬 1개
 * 종류별 갯수 저장하는 MAP 정렬 1개
 * 중복을 최대한 제거하고 큐를 이용하여 시작점 중복 여부 검사
 * 시작점 중복시 제거한 만큼 startPoint 증가
 *
 */

import java.util.*;

public class GemShopping {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>(); // 종류와 갯수 저장
        Set<String> set = new HashSet<>(); // 종류 저장
        Queue<String> queue = new LinkedList<>(); // 길이를 쟤기 위해 사용
        int minLength = (int)1e9;
        int startPoint = 0;

        for(String gem : gems) {
            set.add(gem);
        }

        int start = 0;
        for (int i = 0; i < gems.length; i++) {
            if(!map.containsKey(gems[i])) map.put(gems[i], 1);
            else map.put(gems[i], map.get(gems[i]) + 1);

            queue.offer(gems[i]);

            while(true) {
                String temp = queue.peek();

                // 중복 제거
               if(map.get(temp) > 1) {
                    map.put(temp, map.get(temp) - 1);
                    queue.poll();
                    startPoint ++; // 맨 처음 위치 중복 제거 했으므로 시작 포인트 증가
                } else {
                    break;
                }
            }

            if(map.size() == set.size() && minLength > queue.size()) {
                minLength = queue.size();
                start = startPoint;
            }
        }

        return new int[]{start + 1, start + minLength};
    }
}
