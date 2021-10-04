package code;

import java.io.*;

public class Baek_1676 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 0;

        while (n >= 5) {
            count += n/5;
            n /= 5;
        }

        System.out.println(count);
    }
}
