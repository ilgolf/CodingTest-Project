package Week_2;

import java.io.*;
import java.util.*;

public class Baek_21774 {
    
    static String[] log;
    static int[] lev;
    static int[][] check;

    static int BinaryLowwer(int start, int end, String target) {
        long newTarget = Long.parseLong(target);

        while(start < end) {
            int mid = (start + end) / 2;

            long temp = Long.parseLong(log[mid]);

            if(newTarget <= temp) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    static int BinaryUpper(int start, int end, String target) {
        long newTarget = Long.parseLong(target);
        
        while(start < end) {
            int mid = (start + end) / 2;

            long temp = Long.parseLong(log[mid]);

            if(newTarget < temp) {
                end = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return end;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        log = new String[N + 2];
        log[N+ 1] = String.valueOf((int)1e9);
        lev = new int[N + 1];
        check = new int[N + 1][7];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), "#");

            log[i] = st.nextToken().replaceAll("[- :]", "");
            lev[i] = Integer.parseInt(st.nextToken());
            for(int j=lev[i]; j>=1; j--) {
                check[i][j] ++;
            }
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=6; j++) {
                check[i][j] += check[i - 1][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Q; i++) {
            st = new StringTokenizer(br.readLine(), "#");

            String start = st.nextToken().replaceAll("[- :]", "");
            String end = st.nextToken().replaceAll("[- :]", "");
            int findLev = Integer.parseInt(st.nextToken());

            int low = BinaryLowwer(1, N + 1, start);
            int upper = BinaryUpper(1, N + 1, end);
            int count = check[upper - 1][findLev] - check[low - 1][findLev];
            sb.append(count).append('\n'); 
        }

        System.out.println(sb);
    }
}
