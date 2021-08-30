
import java.util.*;
import java.util.regex.*;

public class 매칭점수_2 {
    private int length;
    private Map<Integer, Node> map;
    private Map<String, Integer> mapping;
    private Pattern myUrlPattern = Pattern.compile("(?i)<meta property=\"og:url\" content=\"https://(.+?)\"/>");
    private Pattern outSidePattern = Pattern.compile("(?i)<a href=\"https://(.+?)\">");

    public int solution(String word, String[] pages) {
        map = new HashMap<>();
        mapping = new HashMap<>();
        length = pages.length;

        findAddr(pages);
        findWord(pages, word);
        findOutSide(pages);

        double max = 0;
        int ret = 0;
        for (int i = 0; i < length; i++) {
            double sum = map.get(i).score1 + map.get(i).score2;
            if (max < sum) {
                max = sum;
                ret = i;
            }
        }
        return ret;
    }

    private void findAddr(String[] pages) {
        for (int i = 0; i < length; i++) {
            map.put(i, new Node(0, 0));
            Matcher matcher = myUrlPattern.matcher(pages[i]);
            if (matcher.find()) {
                mapping.put(matcher.group(1), i);
            }
        }
    }

    private void findWord(String[] pages, String word) {
        for (int i = 0; i < length; i++) {
            Pattern p = Pattern.compile("(?i)" + word);
            Matcher m = p.matcher(pages[i]);
            while (m.find()) {
                char a = pages[i].charAt(m.start() - 1);
                char b = pages[i].charAt(m.end());
                if ('a' < a && a < 'z') continue;
                if ('a' < b && b < 'z') continue;
                map.get(i).score1++;
            }
        }
    }

    private void findOutSide(String[] pages) {
        for (int i = 0; i < length; i++) {
            List<String> list = new ArrayList<>();
            Matcher matcher = outSidePattern.matcher(pages[i]);

            while (matcher.find()) {
                list.add(matcher.group(1));
            }

            double val = map.get(i).score1 / list.size();
            for (String x : list) {
                if (!mapping.containsKey(x)) continue;
                int outside = mapping.get(x);
                map.get(outside).score2 += val;
            }
        }
    }

    private static class Node {
        double score1;
        double score2;

        public Node(double score1, double score2) {
            this.score1 = score1;
            this.score2 = score2;
        }
    }
}

