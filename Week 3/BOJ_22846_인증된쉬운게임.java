// JAVA 11, 시간 200ms, 메모리 29428KB
// DP로 문제 해결

import java.io.*;


public class Main {

    // 자연수 K
    public static int K;
    // dp 배열
    // 모니터의 값이 x일때부터 게임을 시작할때 Kali가 이기면 True, 지면 False
    public static boolean [] dp;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());

        dp = new boolean[K+1];

        for (int x = K; x >= 1; x--) {
            // 시간 절약을 위해 y*y<=x 인 범위만큼만 약수 확인
            for (int y = 1; y * y <= x; y++) {

                if (x % y == 0) {
                    // x에 y를 더했을 때 범위를 벗어나지 않고 x에 y를 더한 값이 false 일 때 true
                    // 범위를 벗어날 경우 자동적으로 지는 것이기 때문에 범위 벗어나면 안됨

                    // Kali의 다음 차례는 Ringo
                    // Kali가 이기는 최선의 전략은 Ringo가 지는 경우
                    // 따라서 x에 y를 더해 Ringo의 차례를 갔을 때 dp의 값이 False라면 dp[x]=true
                    if (x + y <= K && !dp[x + y]) {
                        dp[x]=true;
                        break;
                    }
                    // y*y<=x인 경우만큼만 구했기에 x/y도 추가적으로 구해줘야 함.
                    if (x + x / y <= K && !dp[x + x / y]) {
                        dp[x]=true;
                        break;
                    }
                }

            }
        }


        // 초기 모니터의 숫자가 1이기 때문에 dp[1]의 값을 비교
        // Kali가 1부터 시작해서 이긴다면 (dp[1]=True)라면 Kali 출력
        if (dp[1]) {
            bw.write("Kali");
        } else {
            bw.write("Ringo");
        }




        bw.flush();

    }





}
