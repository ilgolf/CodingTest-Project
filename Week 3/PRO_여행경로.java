// hashmap 이용해서 dfs로 해결
// result를 알파벳 순서대로 정렬하고 빠른 순서대로 hashmap에 0,1... 로 저장
// 현재 위치 A에서 갈 수 있는 여행지들을 알파벳 순서대로 ArrayList에 저장
// DFS를 한다.

import java.util.*;

class Solution {
    
    // 답
    public static String[] answer;
    // 주어진 tickets 길이
    public static int length;
    // 여행지 String -> arrayList index (숫자)로 바꿔줄 HashMap
    public static HashMap<String,Integer> map=new HashMap<>();
    // 여행지 A에서 갈 수 있는 여행지들 저장한 arrayList
    public static ArrayList<ArrayList<String>> list=new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        
        length=tickets.length;
        answer=new String[length+1];
        
        // 정렬
        Arrays.sort(tickets,new Comparator<String[]>(){
            @Override
            public int compare(String[] o1,String[] o2){
                if(o1[0].equals(o2[0])){
                    return o1[1].compareTo(o2[1]);
                
                }
                return o1[0].compareTo(o2[0]);
            }
        });
        


        // 현재 위치에서 갈 수 있는 여행지 알파벳 순서대로 저장
        int idx=-1;
          
        for(int x=0;x<length;x++){
            if(!map.containsKey(tickets[x][0])){
                map.put(tickets[x][0],++idx);
                list.add(new ArrayList<>());
            }
            list.get(idx).add(tickets[x][1]);
        }
        
        // ICN 부터 시작
        answer[0]="ICN";
        
        dfs(map.get("ICN"),1);
        
        return answer;
    }
    
    // 여행 루트 만들었으면 true, 못만들면 false
    // 현재 위치 start, 현재 여행 루트 idx count
    public static boolean dfs(int start, int count){
        // 마지막 티켓 사용
        if(count==length){
            if(list.get(start).size()==1){
                answer[length]=list.get(start).get(0);
                return true;
            }
            return false;
        }
        
        int size=list.get(start).size();
        
        for(int x=0;x<size;x++){
            String next=list.get(start).get(x);
            
            // next가 map에 저장되어 있지 않다는 것 -> 마지막에 사용되는 여행 티켓이라는 것
            if(!map.containsKey(next))
                continue;
            
            answer[count]=next;
            // 사용 체크 (삭제)
            list.get(start).remove(x);
            if(dfs(map.get(next),count+1))
                return true;
            // 다시 
            list.get(start).add(x,next);
        
        }
        
        return false;
    }
    
}
