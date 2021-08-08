package Week_2;

import java.util.*;
import java.io.*;

public class Baek_1561 {

    static int n,m;
    static int[] rides;

    static int amusementPark() {
        if(n <= m) {
            return n;
        }

        long left = 0;
        long right = 2000000000L * 30L;
        while(left <= right) {
            long mid = (left + right) / 2;
            long begin = 0;
            long end = m;
            for(int i=0; i<m; i++) {
                end += mid/rides[i];
            }
            begin = end;
            for(int i=0; i<m; i++) {
                if(mid % rides[i] == 0) {
                    begin -= 1;
                }
            }

            begin += 1;
            if(n < begin) {
                right= mid - 1;
            }
            else if(n > end) {
                left = mid + 1;
            } else {
                for(int i=0; i<m; i++) {
                    if(mid % rides[i] == 0) {
                        if(n == begin) {
                            return i + 1;
                        }
                        begin += 1;
                    }
                }
            }
        }
        return 0;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        rides = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            rides[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(amusementPark());
    }
}
