class Solution {
    public int solution(String str1, String str2) {
        int answer = 0;
        
      // 두 글자의 인덱스 저장
        int[][] saveIdx=new int[2][26*26+1];
        int intersection=0,union=0;
        
      // 소문자 변환
        str1=str1.toLowerCase();
        str2=str2.toLowerCase();
        
        saveIdx[0]=makeWord(str1);
        saveIdx[1]=makeWord(str2);
        
      // 교집합, 합집합 구함
        for(int x=0;x<26*26+1;x++){
            
            intersection+=Math.min(saveIdx[0][x],saveIdx[1][x]);
            union+=Math.max(saveIdx[0][x],saveIdx[1][x]);
        }
        
      // 합집합이 0인 경우 1로 세팅
        if(union==0)
            union=intersection=1;
        
        answer=intersection*65536/union;
        
        
        
        return answer;
    }
    
  // 두 글자씩 만들어나감
    public int[] makeWord(String str){
        int[] ret=new int[26*26+1];
        
        for(int x=0;x<str.length()-1;x++){
            
            int one=str.charAt(x);
            int two=str.charAt(x+1);
            
            if(Character.isLetter(one) && Character.isLetter(two)){
                ret[(one-'a')*26+(two-'a')]++;
            }
        
            
        }

        
        return ret;
    }
}
