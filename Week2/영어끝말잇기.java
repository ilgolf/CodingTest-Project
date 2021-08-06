import java.util.HashSet;
class Solution {
	public int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		HashSet<String> wordMap = new HashSet<String>();
		int index = 0;
		char last = 0;
		while (true) {
			if (index == words.length) {
				answer[0] = 0;
				answer[1] = 0;
				break;
			}
			if ((last != 0 && last != words[index].charAt(0)) || wordMap.contains(words[index])) {
				answer[0] = index % n + 1;
				answer[1] = index / n + 1;
				break;
			}
			last = words[index].charAt(words[index].length() - 1);
			wordMap.add(words[index]);
			index++;
		}
		return answer;
	}
}