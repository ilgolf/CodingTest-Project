class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int answerPlayTime=0;
        
      // 변환한 m 곡 저장
        String mChange=changeString(m);
        int mPlayTime=mChange.length();
        
        int musicinfosLength=musicinfos.length;
        
        for(int x=0;x<musicinfosLength;x++){
           
            String[] split=musicinfos[x].split(",");
            
           // 현재 곡 플레이 시간
            int musicPlayTime=PlayTime(split[0],split[1]);
            
            // 음악 플레이 시간보다 m이 길면 false
            
            if(musicPlayTime<mPlayTime)
                continue;
            
          // 현재 음악 # 변환해서 저장
            String music=playMusic(changeString(split[3]),musicPlayTime);
            
            // m의 패턴이 플레이된 음악에 없으면 false
            if(!music.contains(mChange))
                continue;
            
          // 기존 정답 시간보다 현재 음악 플레이 시간이 길면 정답 change 
          
            if(answerPlayTime<musicPlayTime){
                answerPlayTime=musicPlayTime;
                answer=split[2];
            }
            
        }
        return answer;
    }
    
  // 음악 플레이 시간 
    public static int PlayTime(String start, String end){
        int startHour,startMin,endHour,endMin;
        
        
        String[] startSplit=start.split(":");
        String[] endSplit=end.split(":");
        
        startHour=Integer.parseInt(startSplit[0]);
        startMin=Integer.parseInt(startSplit[1]);
        endHour=Integer.parseInt(endSplit[0]);
        endMin=Integer.parseInt(endSplit[1]);
        
        return endHour*60+endMin-startHour*60-startMin;
    }
    
  // 음악 재생 시간 만큼 음악 생성
    public static String playMusic(String music, int time){
        int musicLength=music.length();
        
        String temp="";
        
        for(int x=0;x<time/musicLength;x++){
            temp+=music;
        }
        
        for(int x=0;x<time%musicLength;x++){
            temp+=music.charAt(x);
        }
        
        return temp;
        
    }
  
    // C#, D#, F#, G#, A# -> 변환
    public static String changeString(String music){
        int length=music.length();
        
        String ret="";
        
        for(int x=0;x<length;x++){
            if(x<length-1 && music.charAt(x+1)=='#'){
                ret+=(music.charAt(x)-'A');
                x++;
            }else{
                ret+=(music.charAt(x));
            }
            
        }
        
        return ret;
    }
}
