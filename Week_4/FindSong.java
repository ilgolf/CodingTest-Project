package Week_4;

public class FindSong {
    
    private static String answer = "(None)";
    private static int maxTime = -1;
    
    // 중복 동작이므로 묶어서 함수로 만듬
    private static String update(String str) {
        
        str = str.replaceAll("A#", "a");
        str = str.replaceAll("C#", "c");
        str = str.replaceAll("D#", "d");
        str = str.replaceAll("G#", "g");
        str = str.replaceAll("F#", "f");
        
        return str;
    }
    
    private static void findSong(String[] temp, String m) {
        String[] start = temp[0].split(":"); // 시작점
        String[] end = temp[1].split(":");  // 끝나는 점
        
        String title = temp[2]; // 노래 제목
        
        String lyrics = update(temp[3]); // 가사
        
        int hourS = Integer.parseInt(start[0]) * 60;
        int minS = Integer.parseInt(start[1]) + hourS;
        
        int hourE = Integer.parseInt(end[0]) * 60;
        int minE = Integer.parseInt(end[1]) + hourE;
        
        int min = minE - minS; // 실제 재생 시간
        
        if(min > lyrics.length()) { // 재생시간이 가사보다 길면
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<min / lyrics.length(); i++) {
                sb.append(lyrics);
            }
            
            sb.append(lyrics.substring(0, min % lyrics.length()));
            lyrics = sb.toString();
        } else {
            lyrics = lyrics.substring(0, min);
        }
        
        if(lyrics.contains(m) && min > maxTime) {
            answer = title;
            maxTime = min;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        
        m = update(m);
        
        for(String music : musicinfos) {
            String[] temp = music.split(",");
            
            findSong(temp, m);
        }
        return answer;
    }
}