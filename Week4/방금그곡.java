import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Solution {
	static class Music implements Comparable<Music> {
		int id, playingtime;
		String title, sheet;

		Music(int id, int playingtime, String title, String sheet) {
			this.id = id;
			this.playingtime = playingtime;
			this.sheet = sheet;
			this.title = title;
		}

		
		//조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환한다.
		//재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
		@Override
		public int compareTo(Music m) {
			if (this.playingtime < m.playingtime) {
				return m.playingtime - this.playingtime;
			} else if (this.playingtime == m.playingtime) {
				if (this.id > m.id)
					return 1;
				else
					return -1;
			} else
				return -1;
		}
	}

	//"CC#BCC#BCC#BCC#B“ -> "CcBCcBCcBCcB"
	public static String check(String st) {
		st = st.replaceAll("A#", "a");
		st = st.replaceAll("C#", "c");
		st = st.replaceAll("D#", "d");
		st = st.replaceAll("F#", "f");
		st = st.replaceAll("G#", "g");
		return st;
	}
	//m=네오가 기억한 멜로디
	//musicinfos = 방송된 곡의 정보를 담고 있는 배열
	public String solution(String m, String[] musicinfos) {
		//조건이 일치하는 음악이 없을 때에는 “(None)”을 반환한다
		String answer = "(None)";
		m = check(m);
		ArrayList<Music> list = new ArrayList<Music>();
		for (int i = 0; i < musicinfos.length; i++) {
			String[] temp = musicinfos[i].split(",");
			
			String[] startTime = temp[0].split(":");
			String[] endTime = temp[1].split(":");

			// 재생시간 구하기
			int hour = Integer.parseInt(endTime[0]) - Integer.parseInt(startTime[0]);
			int minute = Integer.parseInt(endTime[1]) - Integer.parseInt(startTime[1]);
			list.add(new Music(i, (hour * 60) + minute, temp[2], check(temp[3])));
		}
		//네오가 기억한 멜로디를 포함하고 있는 곡 list
		ArrayList<Music> containList = new ArrayList<Music>();
		
		for (int i = 0; i < list.size(); i++) {
			StringBuilder builder = new StringBuilder();
			int sheet_len = list.get(i).sheet.length();
			int a = list.get(i).playingtime % sheet_len;
			int b = list.get(i).playingtime / sheet_len;
			/*
			 * CcBCcBCcB, 재생시간 8분
			 * a : 8%9=8
			 * b : 8/9=0
			 * */
			for (int j = 0; j < b; j++)
				builder.append(list.get(i).sheet);
			builder.append(list.get(i).sheet.substring(0, a));
			if (builder.toString().contains(m))
				containList.add(list.get(i));

		}
		Collections.sort(containList, new Comparator<Music>() {
			@Override
			public int compare(Music s1, Music s2) {
				return s1.compareTo(s2);
			}
		});
		if (containList.size() > 0)
			answer = containList.get(0).title;
		return answer;
	}
}