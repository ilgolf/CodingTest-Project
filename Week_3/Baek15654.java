package Week_3;

import java.io.*;
import java.util.*;

public class Baek15654 {

    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static int[] arr, temp;
    static boolean[] checked;
    
    static void search(int start) {
        if(start == m) {
            for(int i=0; i<m; i++) {
                sb.append(temp[i]).append(' ');
            }
            sb.append('\n');

            return;
        }

        for(int i=0; i<n; i++) {
            if(!checked[i]) {
                checked[i] = true;
                temp[start] = arr[i];
                search(start + 1);
                checked[i] = false;
            }
        }
        return;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        arr = new int[n];
        temp = new int[m];
        checked = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        search(0);
        System.out.println(sb);
    }
}
