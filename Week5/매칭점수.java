package 신규아이디추천;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

	static class Page implements Comparable<Page>{
		int score, externalLinksNum; //기본점수
		String str;
		ArrayList<String> fromLink;
		public Page(int score, int externalLinksNum, String str, ArrayList<String> fromLink) {
			this.score = score;
			this.externalLinksNum = externalLinksNum;
			this.str = str;
			this.fromLink = fromLink;
		}

		@Override
		public String toString() {
			return "";
		}

	}
	static void findURL(String regexs, String str) {
		String url = null;
		boolean urlCheck=false;
		for(int i=0; i<str.length(); i++)
		{
			if(i==str.indexOf("content=")+9 || urlCheck==true)
			{
				urlCheck=true;
				if(str.charAt(i)=='"') {
					url=str.substring(str.indexOf("content")+9,i);
					return;
				}
			}
		}
	}
	static void setDefaultScore(String regexs, String str) {
		int true_count = 0;
		for(int i=0; i<str.length()-regexs.length(); i++)
		{
			if(str.substring(i,i+regexs.length()).equals(regexs))
				true_count++;
		}
	}
	// 패턴이 들어갔는지 check
	static void find(String regexs, String str) {
		int true_count = 0;
		String url = null;
		boolean urlCheck=false;
		int externalLinksNum = 0;
		boolean externalLinksNumCheck=false;
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		int index=-1;
		for(int i=0; i<str.length()-regexs.length(); i++)
		{
			if(str.substring(i,i+regexs.length()).equals(regexs))
				true_count++;
			//System.out.println(true_count);

			if(str.substring(i, i+6).compareTo("href=") == 0)
			{
				index=i+6;
				System.out.println(str.indexOf(index));
			}
			if(index==i || externalLinksNumCheck==true)
			{
				externalLinksNumCheck=true;
				System.out.println(str.substring(index, i+1));
				if(str.charAt(i)=='"') {
					externalLinksNum++;
					externalLinksNumCheck = false;
					list.add(str.substring(i, i+6));
				}
			}
		}

		pageMap.put(url, new Page(true_count, externalLinksNum, str, list));
		System.out.println(url);
		System.out.println(true_count);
		System.out.println(externalLinksNum);
	}
	static HashMap<String, Page> pageMap;
	static int solution(String word, String[] pages) {
		pageMap= new HashMap<String, Page>();
		for(int i=0; i<pages.length; i++)
		{
			check_pattern(word, pages[i].toLowerCase());
		}

		//System.out.println(Arrays.toString(D));
		return 0;
	}

	public static void main(String[] args) throws IOException {
		String word = "blind";
		String[] pages = {
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>",
				"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>" };
		System.out.println(solution(word.toLowerCase(), pages));
	}
}