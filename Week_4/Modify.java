package Week_4;

import java.util.*;

public class Modify {
    public String solution(int n, int k, String[] cmd) {
        Stack<Integer> stack = new Stack<>();

        int table = n;

        for(int i=0; i<cmd.length; i++) {
            if(cmd[i].startsWith("C")) {
                stack.push(k);
                table --;
                if(table == k) k --;
            }
            else if(cmd[i].startsWith("Z")) {
                int r = stack.pop();
                if(k >= r) k++;
                table ++;
            }
            else if(cmd[i].startsWith("D")) {
                k += Integer.valueOf(cmd[i].split(" ")[1]);
            }
            else if(cmd[i].startsWith("U")) {
                k -= Integer.valueOf(cmd[i].split(" ")[1]);
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<table; i++) {
            sb.append("O");
        }
        while(!stack.isEmpty()) {
            sb.insert(stack.pop(), "X");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        
    }
}
