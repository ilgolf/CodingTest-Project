package Week_4;

import java.io.*;
import java.util.*;

public class Three {

    static long[] solution;
    static int n, idx1, idx2, idx3;
    static long min = Long.MAX_VALUE;

    static void shake() {
        for(int i=0; i<solution.length - 2; i++) {
            int start = i + 1;
            int end = n - 1;

            while(start < end) {
                long sum = solution[i] + solution[start] + solution[end];

                if(Math.abs(sum) < min) {
                    idx1 = i;
                    idx2 = start;
                    idx3 = end;

                    min = Math.abs(sum);
                }

                if(sum < 0) {
                    start ++;
                } else {
                    end --;
                }
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        solution = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            solution[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(solution);

        shake();

        System.out.println(solution[idx1] + " " +  solution[idx2] + " " + solution[idx3]);
    }
}
