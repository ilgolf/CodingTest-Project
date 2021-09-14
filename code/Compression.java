package code;

import java.util.LinkedList;
import java.util.Queue;

public class Compression {

    public int[] solution(String msg) {
        Queue<Character> queue = new LinkedList<>();

        for(char m : msg.toCharArray()) {
            queue.offer(m);
        }

        int[] answer = {};
        return answer;
    }
}
