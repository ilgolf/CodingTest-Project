import java.io.*;
import java.util.*;

class Main {

    private static int[][] gear = new int[4][8];
    private static int[] isValid;

    private static void check(int num, int dir) {
        isValid[num] = dir;

        int prev = num - 1;
        int next = num + 1;

        if(prev >= 0 && isValid[prev] == 0) {
            if(gear[prev][2] != gear[num][6]) {
                check(prev, dir * -1);
            }
        }

        if(next <= 3 && isValid[next] == 0) {
            if(gear[next][6] != gear[num][2]) {
                check(next, dir * -1);
            }
        }
    }

    private static void rotate(int[] isValid) {
        for(int i=0; i<4; i++) {
            if(isValid[i] != 0) {
                int[] temp = new int[8];

                int idx;
                for(int j=0; j<8; j++) {
                    idx = j + isValid[i];

                    if(idx == -1) {
                        idx = 7;
                    }
                    else if(idx == 8) {
                        idx = 0;
                    }
                    
                    temp[idx] = gear[i][j];
                }

                gear[i] = temp;
            }
        }
    }

    private static int calc() {
        int sum = 0;
        for(int i=0; i<4; i++) {
            int num = gear[i][0];

            if(num == 1) {
                sum += Math.pow(2, i);
            }
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0; i<gear.length; i++) {
            String[] line = br.readLine().split("");
            for(int j=0; j<line.length; j++) {
                gear[i][j] = Integer.parseInt(line[j]);
            }
        }

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        while(K -- > 0) {
            st = new StringTokenizer(br.readLine());
            isValid = new int[4];

            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

           check(gearNum, dir);
           rotate(isValid);
        }

        System.out.println(calc());
    }
}