package code;

import java.io.*;
import java.util.StringTokenizer;

public class Beak_11723 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int bitset = 0;

        while (n -- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int num;

            switch (op) {
                case "add" :
                    num = Integer.parseInt(st.nextToken());
                    bitset |= (1 << (num - 1));
                    break;

                case "remove" :
                    num = Integer.parseInt(st.nextToken());
                    bitset = bitset & ~(1 << (num - 1));
                    break;

                case "check" :
                    num = Integer.parseInt(st.nextToken());
                    sb.append((bitset & (1 << (num - 1))) != 0 ? "1" : "0").append('\n');
                    break;

                case "toggle" :
                    num = Integer.parseInt(st.nextToken());
                    bitset ^= (1 << (num - 1));
                    break;

                case "all" :
                    bitset |= (~0);
                    break;

                case "empty" :
                    bitset &= 0;
                    break;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
