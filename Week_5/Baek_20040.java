package Week_5;

/**
 *
 * 선수 두명 고정
 * 선 홀수 % == 1, 후 짝수 % == 2
 * 점 n개는 일직선 위에 놓이지 않음
 * m 번 게임 반복해서 사이클이 반복되어있는지 확인
 * 중간에 완성되면 게임 종료
 * 처리 안되어있음 0 출력
 *
 */

/**
 * 분리 집합이 주어지므로 union - find 연산을 이용하여 접근
 *
 */

import  java.util.*;
import  java.io.*;

public class Baek_20040 {

    static int n,m;
    static int[] turn;

    static int find(int val) {
        if(val == turn[val]) return val;

        return turn[val] = find(turn[val]);
    }

    static boolean union(int a, int b) {
        if(a == b) return true;

        a = find(a);
        b = find(b);

        if(a == b) return true;

        turn[b] = a;

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 선분을 그리기 위한 점 n
        m = Integer.parseInt(st.nextToken()); // m번의 턴동안 진행

        int a = 0, b = 0, c = 0;

        turn = new int[n]; //
        boolean cycle = false;

        for (int i = 0; i < n; i++) {
            turn[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // 노드
            b = Integer.parseInt(st.nextToken()); // 노드
            if(!cycle && union(a, b)) {
                cycle = true;
                c = i + 1;
            }
        }

        System.out.println(c);
    }
}
