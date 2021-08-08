package Week_2;

import java.io.*;
import java.util.*;

public class English {

    static boolean[] visited;
    static int max;
    static int p,n;

    static void search() {
        int start = 1;
        int end = 1;
        int cnt = 0;
        while(end < visited.length) {
            if(visited[end]) {
                cnt++;
                end++;
                max = Math.max(max, cnt);
            } else {
                if(p == 0) {
                    max = Math.max(max, cnt);
                    if(!visited[start]) p ++;
                    start ++;
                    cnt --;
                } else {
                    p--;
                    cnt ++;
                    end ++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int[] arr;

        int T = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        for(int testCase = 1; testCase<=T; testCase++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            visited = new boolean[1200000];  // ?

            for(int day : arr) {
                if(day != 0) {
                    visited[day] = true;
                }
            }

            max = p + 1;

            search();
            sb.append("#" + testCase + " ").append(max).append('\n');
        }
        System.out.println(sb);
    }
}
