package Week_4;

public class FindSong {
    public String solution(String m, String[] musicinfos) {
        String answer = "";

        m.replaceAll("A#", "a");
        m.replaceAll("D#", "d");
        m.replaceAll("C#", "c");
        m.replaceAll("F#", "f");
        m.replaceAll("G#", "g");

        String startH = "";  String endH = "";
        String startM = "";  String endM = "";
        String title = "";   String lyrics = "";
        for(int i=0; i<musicinfos.length; i++) {
            if(i % 4 == 0) {
                String[] str = musicinfos[i].split(":");

                startH = str[0];
                startM = str[1]; 
            }
            else if(i % 4 == 1) {
                String[] str = musicinfos[i].split(":");

                endH = str[0];
                endM = str[1];
            }
            else if(i % 4 == 2) {
                title = musicinfos[i];
            }
            else if(i % 4 == 3) {
                int hourS = startH.startsWith("0") ? startH.charAt(1) - '0' : Integer.parseInt(startH);
                int minS = startM.startsWith("0") ? startM.charAt(1) - '0' : Integer.parseInt(startM);

                int hourE = endH.startsWith("0") ? endH.charAt(1) - '0' : Integer.parseInt(endH);
                int minE = endM.startsWith("0") ? endM.charAt(1) - '0' : Integer.parseInt(endM);

                int hour = hourE - hourS;
                int min = (minE - minS) + (hour > 0 ? 60 * hour : 0);

                lyrics = musicinfos[i];
                
                if(min / m.length() > 0) {
                    for(int j=0; j<min/m.length(); j++) {
                        lyrics += musicinfos[i];
                    }
                    lyrics += min % m.length() > 0 ? musicinfos[i].substring(0, min % m.length()) : "";
                }
                else {
                    lyrics = min % m.length() > 0 ? musicinfos[i].substring(0, min % m.length()) : musicinfos[i];
                } 
            }

            if(lyrics.contains(m)) {
                answer = title;
                break;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        FindSong f = new FindSong();

        System.out.println(f.solution(m, musicinfos));
    }
}
