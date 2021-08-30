import java.io.IOException;
import java.util.HashMap;
class Solution {
   
	public static String solution(String[] table, String[] languages, int[] preference) {
		HashMap<String, Integer> languageMap = new HashMap<String, Integer>();

		//계산해야되는  언어와 그언어 선호도 저장
		for (int i = 0; i < languages.length; i++)
			languageMap.put(languages[i], preference[i]);

		int maxScore=0;
		String maxName=null;
		for (int i = 0; i < 5; i++) {
			String[] arr = table[i].split(" ");
			int score=0;
			for (int j = 1; j < 6; j++)
				if(languageMap.containsKey(arr[j]))//계산해야되는 언어일때만
					score += (6-j) * languageMap.get(arr[j]);

			if(maxScore<score)
			{
				maxScore = score;
				maxName = arr[0];
			}else if(maxScore == score)
			{
				if(maxName.compareTo(arr[0])>0)
					maxName = arr[0];
			}
		}

		return maxName;
	}

	
}