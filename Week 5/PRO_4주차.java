import java.util.*;

class Solution {
    // language string -> preference int
    public HashMap<String, Integer> map=new HashMap<>();
    
    public int maxValue=0;
    
    public String solution(String[] table, String[] languages, int[] preference) {
        String answer = "";

        int maxScore=0;
        
      // hashmap 저장
        for(int x=0;x<languages.length;x++){
            map.put(languages[x],preference[x]);
        }
        
      // 각 직업군마다 점수 체크
        for(int x=0;x<5;x++){
            String[] split=table[x].split(" ");
            
            int midScore=maxScore(split);
            
          // 현재 직업 군 합계가 이전보다 높거나, 현재 직업 군 합계가 이전과 같고 이전 직업군보다 사전순으로 빠른 경우 -> change
            if(midScore>maxScore || (midScore==maxScore && split[0].charAt(0)<answer.charAt(0))){
                maxScore=midScore;
                answer=split[0];
            }
            
        }
        
        return answer;
    }
    
  // 직업군별 합계 점수 구하기
    public int maxScore(String[] listSplit){
        
        int ret=0;
        
        for(int x=1;x<6;x++){
          // 현재 언어가 개발자가 사용하는 언어일 경우
            if(map.containsKey(listSplit[x])){
                int key=map.get(listSplit[x]);
                
                ret+=(6-x)*key;
                
            }
        }
        
        return ret;
    }
}
