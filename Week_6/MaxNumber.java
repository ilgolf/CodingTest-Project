package Week_6;

/**
 *
 * 재귀 방식으로 dfs 탐색 하여 모든 경우를 탐색해가며
 * 정답을 갱신하여준다.
 * List 2개를 이용해 연산자와 숫자 저장해주고
 * List 2개를 더 추가해 복사해준 다음 연산해준다.
 *
 */

import java.util.*;

public class MaxNumber {

    static long answer = Long.MIN_VALUE;

    static List<Long> numList = new ArrayList<>(); // 숫자 저장 리스트
    static List<String> operList = new ArrayList<>(); // 연산자 리스트

    static String[] oper = {"+", "-", "*"}; // 조건 : 연산자 3개
    static String[] outputs = new String[3]; // 연산할 연산자와 숫자 저장 ex) 100, * 200
    static boolean[] checked = new boolean[3]; // 방문 처리

    // 백트래킹
    static void per(int depth, int r) {
        if(depth == r) {
            solve();

            return;
        }

        for (int i = 0; i < oper.length; i++) {
            if(!checked[i]) {
                checked[i] = true;
                outputs[depth] = oper[i];
                per(depth + 1, r);
                checked[i] = false;
            }
        }
    }

    static void solve() {
        List<String> operCopy = new ArrayList<>(operList);

        List<Long> num = new ArrayList<>(numList);

        for (String curOper : outputs) {
            for (int j = 0; j < operCopy.size(); j++) {
                if (operCopy.get(j).equals(curOper)) {
                    long n1 = num.get(j);
                    long n2 = num.get(j + 1);
                    long res = cal(n1, n2, curOper);

                    num.remove(j + 1);
                    num.remove(j);
                    operCopy.remove(j);

                    num.add(j, res);
                    j--;
                }
            }
        }
        answer = Math.max(answer, Math.abs(num.get(0)));
    }

    static long cal(long n1, long n2, String o) {
        long res = 0;
        switch (o) {
            case "+" :
                res = n1 + n2;
                break;

            case "-" :
                res = n1 - n2;
                break;

            case "*" :
                res = n1 * n2;
                break;
        }

        return res;
    }

    public long solution(String expression) {
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char exp = expression.charAt(i);
            if(exp == '+' || exp == '-' || exp == '*') {
                operList.add(exp + "");
                numList.add(Long.parseLong(n.toString()));
                n = new StringBuilder();
            } else {
                n.append(exp);
            }
        }

        numList.add(Long.parseLong(n.toString()));

        per(0, oper.length);

        return answer;
    }
}
