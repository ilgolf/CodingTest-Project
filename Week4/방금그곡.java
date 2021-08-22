import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
class Solution {
    static class Music implements Comparable<Music>{
		int id,playingtime;
		String title, sheet;

		Music(int id,int playingtime, String title, String sheet)
		{
			this.id = id;
			this.playingtime = playingtime;
			this.sheet = sheet;
			this.title = title;
		}

		@Override
		public int compareTo(Music m) {
			if (this.playingtime < m.playingtime) {
				return m.playingtime-this.playingtime;
			} else if(this.playingtime == m.playingtime) {
				if(this.id>m.id)
					return 1;
				else
					return -1;
			}
			else
				return -1;
		}
	}
	
    public static String check(String st) {
		st = st.replaceAll("A#", "a");
		st = st.replaceAll("C#", "c");
		st = st.replaceAll("D#", "d");
		st = st.replaceAll("F#", "f");
		st = st.replaceAll("G#", "g");
		return st;
	}
    public String solution(String m, String[] musicinfos) {
        	String answer = "(None)";
		m=check(m);
		ArrayList<Music> list = new ArrayList<Music>();
		for(int i=0; i<musicinfos.length;i++)
		{
			String[] temp = musicinfos[i].split(",");
			//재생시간 구하기
			String[] start_time= temp[0].split(":");
			String[] end_time=temp[1].split(":");

			int hour = Integer.parseInt(end_time[0])-Integer.parseInt(start_time[0]);
			int minute = Integer.parseInt(end_time[1])-Integer.parseInt(start_time[1]);
			list.add(new Music(i,(hour*60)+minute, temp[2], check(temp[3])));
		}
		ArrayList<Music> contain_list = new ArrayList<Music>();
		for(int i=0; i<list.size();i++)
		{
			StringBuilder builder = new StringBuilder();
			int sheet_len=list.get(i).sheet.length();
			int a =list.get(i).playingtime%sheet_len;
			int b = list.get(i).playingtime/sheet_len;
			for(int j=0; j<b; j++)
				builder.append(list.get(i).sheet);
			builder.append(list.get(i).sheet.substring(0, a));
            if(builder.toString().contains(m))
				contain_list.add(list.get(i));

		}
		Collections.sort(contain_list, new Comparator<Music>() {
			@Override
			public int compare(Music s1, Music s2) {
				return s1.compareTo(s2);
			}
		});
		if(contain_list.size()>0)
			answer  = contain_list.get(0).title;
        return answer;
    }
}