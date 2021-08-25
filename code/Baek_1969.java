package code;

import java.io.*;
import java.util.*;

public class Baek_1969 {

    static int n,m;
    static List<String> list = new ArrayList<>();

    static int Hamming(char[] prev, char[] next) {
        int count = 0;

        

        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        char[][] chArr = new char[n][m];

        for(int i=0; i<n; i++) {
            chArr[i] = br.readLine().toCharArray();
        }

        for(int i=1; i<n; i++) {

        }
    }
}