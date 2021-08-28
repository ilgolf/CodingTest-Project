package Week_5;

/*
*
* 전형 적인 문자열 문제
* Page 클래스에 구해줘야할 각각의 기본점수 링크점수 매칭 점수를 선언해주고
* Map에 기본 자신의 url을 넣어 준 후에 외부 url을 탐색해 조건에 맞게 매칭 점수를 구해준다.
*
* */

import java.util.*;

public class MatchingScore {

    static class Page {
        int idx; // 웹 페이지 번호
        int basic, link; // 기본점수, 링크점수
        double score; // 매칭 점수

        public Page(int idx, int basic, int link, double score) {
            this.idx = idx;
            this.basic = basic;
            this.link = link;
            this.score = score;
        }
    }

    class Comp implements Comparator<Page> {

        @Override
        public int compare(Page o1, Page o2) {
            if(o1.score == o2.score) {
                return o1.idx - o2.idx;
            }
            else if(o1.score < o2.score) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public int solution(String word, String[] pages) {
        int wsize = word.length();

        Map<String, Integer> map = new HashMap<>();
        List<Page> list = new ArrayList<>();
        word = word.toLowerCase();
        for(int i=0; i<pages.length; i++) {
            StringBuilder sb = new StringBuilder(pages[i].toLowerCase());

            int mid = 0, posl = 0, posr = 0;

            // 한 웹페이지의 주소 url 구하기
            while(mid <= posl) {
                posl = sb.indexOf("<meta", posl + 1);
                posr = sb.indexOf(">", posl);
                mid = sb.lastIndexOf("https://", posr);
            }

            posr = sb.indexOf("\"", mid);
            String url = sb.substring(mid, posr);

            posl = sb.indexOf("<body>", posr);
            int basic = 0; // 기본 점수

            // 기본점수 구하기
            for(int start = posl; ;) {
                start = sb.indexOf(word, start + 1);
                if(start < 0) break;
                // 앞뒤로 알파벳 체크
                if(!Character.isLetter(sb.charAt(start - 1)) && !Character.isLetter(sb.charAt(start + wsize))) {
                    basic ++; // 주변에 공백이나 기호 숫자로 되어있음 기본 점수 + 1
                    start += wsize; // 인덱스를 wsize 만큼 늘려야함
                }
            }

            int link = 0; // 링크점수
            for(int start = posl; ;) {
                start = sb.indexOf("<a href", start + 1);
                if(start < 0) break;
                link ++; // 외부 링크를 찾으면 바로 링크점수 + 1
            }
            map.put(url, i); // 자신의 웹페이지 링크와 인덱스를 저장
            list.add(new Page(i, basic, link, (double)basic)); // 기본점수를 double로 저장 후 link 연산 후 더해주면 매칭점수
        }

        for(int i=0; i< pages.length; i++) {
            StringBuilder sb = new StringBuilder(pages[i].toLowerCase());
            for(int posl = 0, posr = 0; ;) {
                posl = sb.indexOf("<a href", posr);  // -1
                if(posl < 0) break;
                posl = sb.indexOf("https://", posl);
                posr = sb.indexOf("\"", posl);
                String linkeUrl = sb.substring(posl, posr);

                Integer value = map.get(linkeUrl);
                if(value != null) {
                    list.get(value).score += (double) list.get(i).basic / list.get(i).link;
                }
            }
        }

        list.sort(new Comp());
        return list.get(0).idx;
    }
}
