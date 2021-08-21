import java.util.*;

class Solution {

    // 답
    public static int[] answer;
    // 다단계 조직 사람 수
    public static int groupMember;
    // hashmap, 사람 이름 입력하면 recommendMember의 인덱스 출력
    public static HashMap<String, Integer> hashmap=new HashMap<>();
    // 자신을 추천한 사람의 인덱스를 가르킴
    public static int[] recommendMember;

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        groupMember=enroll.length;
        recommendMember=new int[groupMember];
        answer=new int[groupMember];

        // hashmap으로 사람 string -> int 변환
        // reterral로 자신을 추천한 사람 index를 recommendMember에 저장
        // enroll에 등장하는 이름은 조직에 참여한 순서대로 이기때문에
        // 자신을 추천한 사람은 enroll에서 자신보다 앞 순서에 등장하기 때문에 가능
        // 만약 이러한 조건이 없었으면 전체적으로 hashmap 저장 후 recommendMember저장해야 함.
        for(int x=0;x<groupMember;x++){
            hashmap.put(enroll[x],x);

            if(referral[x].equals("-")){
                recommendMember[x]=x;
            }else{
                recommendMember[x]=hashmap.get(referral[x]);
            }
        }

        // seller와 amount로 dfs로 계산
        int peopleIdx=0;
        int plus=0;

        for(int x=0;x<seller.length;x++){
            peopleIdx=hashmap.get(seller[x]);
            plus=amount[x]*100;

            while(recommendMember[peopleIdx]!=peopleIdx){
                answer[peopleIdx]+=plus-(int)(plus*0.1);
                peopleIdx=recommendMember[peopleIdx];
                plus/=10;
            }

            answer[peopleIdx]+=plus-(int)(plus*0.1);
        }

        return answer;
    }


}
