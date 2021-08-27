package Week_5;

/**
 * 
 * 먼저 문자열을 split으로 나눠 준다.
 * 각 문자열을 stack에 넣어주고 꺼내면 역순으로 바꿈
 * check : <가 나오면 ++연산 > 가 나오면 -- 연산으로 구분
 * 
 */

import java.io.*;
import java.util.*;

public class Baek_17413 {
    
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        str = br.readLine();

        int check = 0; // 괄호 닫고 열기 탐색 변수

        for(char c : str.toCharArray()) {
            if(c == '<') {
                check ++;
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append("<");
            } else if(c == '>') {
                check --;
                sb.append(">");
            } else if(c == ' ') {
                while(!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                sb.append(" ");
            } else {
                if(check == 0) {
                    stack.push(c);
                } else {
                    sb.append(c);
                }
            } 
        }

        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
