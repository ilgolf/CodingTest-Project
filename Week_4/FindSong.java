package Week_4;

class Solution {
    
    private static String answer = "(None)";
    private static int maxTime = -1;
    
    private static String update(String str) {
        
        str = str.replaceAll("A#", "a");
        str = str.replaceAll("C#", "c");
        str = str.replaceAll("D#", "d");
        str = str.replaceAll("G#", "g");
        str = str.replaceAll("F#", "f");
        
        return str;
    }
    
    private static void findSong(String[] temp, String m) {
        String[] start = temp[0].split(":");
        String[] end = temp[1].split(":");
        
        String title = temp[2];
        
        String lyrics = update(temp[3]);
        
        int hourS = Integer.parseInt(start[0]) * 60;
        int minS = Integer.parseInt(start[1]) + hourS;
        
        int hourE = Integer.parseInt(end[0]) * 60;
        int minE = Integer.parseInt(end[1]) + hourE;
        
        int min = minE - minS;
        
        if(min > lyrics.length()) {
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