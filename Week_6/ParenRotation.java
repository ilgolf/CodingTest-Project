package Week_6;

/**
 *
 * stack을 이용한 풀이
 * 회전 하는 형태로 문자열 파싱 후 붙여놓고 stack으로 올바른 문자열 일때마다
 * count 하여 count 한 값을 리턴 Exception을 try - catch로 잡아 return false
 *
 */

import java.util.Stack;

public class ParenRotation {

    static boolean check(String paren) {
        Stack<Character> stack = new Stack<>();

        for (char c : paren.toCharArray()) {
            try {
                switch (c) {
                    case '[' :
                        stack.add(c);
                        break;
                    case ']' :
                        if(stack.peek() != '[') return false;
                        stack.pop();
                        break;
                    case '{' :
                        stack.add(c);
                        break;
                    case '}' :
                        if(stack.peek() != '{') return false;
                        stack.pop();
                        break;
                    case '(' :
                        stack.add(c);
                        break;
                    case ')' :
                        if(stack.peek() != '(') return false;
                        stack.pop();
                        break;
                }
            } catch (Exception e) {
                return false;
            }
        }
        return stack.isEmpty() ? true : false;
    }

    public int solution(String s) {
        int answer = 0;

        String newS = s;
        for (int i = 0; i < s.length(); i++) {
            if(check(newS)) answer ++;

            newS = newS.substring(1, s.length()) + newS.charAt(0);
        }

        return answer;
    }
}
