package Week_5;

import java.io.*;

public class Baek_16916 {
    static int result;
    static int[] pi;
    static String origin, pattern;

    static void getPi() {
        int j = 0;
        for(int i=1; i<pattern.length(); i++) {

            while(j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if(pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
    }

    static void KMP() {
        int j = 0;
        for(int i=0; i<origin.length(); i++) {
            while(j > 0 && origin.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }

            if(origin.charAt(i) == pattern.charAt(j)) {
                if(j == pattern.length() - 1) {
                    result = 1;
                    break;
                }
                else {
                    ++j;
                }
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        origin = br.readLine();
        pattern = br.readLine();

        pi = new int[pattern.length()];
        getPi();
        KMP();
    }
}
