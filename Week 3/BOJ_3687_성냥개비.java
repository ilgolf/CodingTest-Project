// JAVA 11, 시간 128ms, 메모리 14020KB
// DP인지 알았으나 규칙 찾아서 푸는게 더 편했음
// 위에는 규칙 찾아서 푼 코드
// 아래는 DP로 푼 코드

import java.io.*;
import java.util.Arrays;

public class Main {

    // 성냥개비 0개 ~ 10개 사용했을 때 최솟값 저장
    public static long[] dp = {0, 0, 1, 7, 4, 2, 6, 8, 10, 18, 22};


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int x = 0; x < testCase; x++) {
            int now = Integer.parseInt(br.readLine());

            int prevNow = now;

            if (now <= 10) {
                sb.append(dp[now]);
            } else {
                // 최솟값 규칙
                if (now % 7 == 1) {
                    sb.append("10");
                    now -= 8;
                } else if (now % 7 == 2) {
                    sb.append("1");
                    now -= 2;
                } else if (now % 7 == 3) {
                    sb.append("200");
                    now -= 17;
                } else if (now % 7 == 4) {
                    sb.append("20");
                    now -= 11;
                } else if (now % 7 == 5) {
                    sb.append("2");
                    now -= 5;
                } else if (now % 7 == 6) {
                    sb.append("6");
                    now -= 6;
                }
                sb.append("8".repeat(now/7));
            }

            sb.append(" ");

            // 최댓값 규칙 -> 홀수인 경우 앞자리 7 세팅
            // 그 후 다 1로 채움
            if (prevNow % 2 == 1) {
                sb.append("7");
                prevNow -= 3;
            }

            sb.append("1".repeat(prevNow / 2));

            sb.append("\n");
        }

        System.out.println(sb);

    }



}

//public class Main {
//    // 성냥개비 X를 사용했을 때 만들 수 있는 최솟값
//    // 100/7=14
//    // 최대 자릿수가 14까지 가능하기에 int로 하면 error
//    public static long[] dp = new long[101];
//
//    // 성냥개비 X를 사용했을 때 만들 수 있는 최소 숫자
//    public static long[] min = {0, 0, 1, 7, 4, 2, 0, 8};
//
//    public static void main(String[] args) throws IOException {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//
//        int testCase = Integer.parseInt(br.readLine());
//
//        // 미리 변수 세팅
//        dp[2] = 1;
//        dp[3] = 7;
//        dp[4] = 4;
//        dp[5] = 2;
//        dp[6] = 6;
//        dp[7] = 8;
//
//        // n의 범위는 100이하
//        for (int x = 8; x <= 100; x++) {
//
//            // 최솟값을 만들기 위해 성냥개비 2개와 3개는 최대 한번만 사용함
//            // 왜냐하면 성냥개비 2개를 2번쓰는 것보다 4개를 한번 쓰는게 더 최솟값이기 때문
//            for (int y = 4; y <= 7; y++) {
//                if(dp[x-y]==0)
//                    break;
//
//                long result = add(x, y);
//
//                if (dp[x] == 0) {
//                    dp[x] = result;
//                } else {
//                    dp[x] = Math.min(dp[x], result);
//                }
//
//            }
//        }
//
//
//        for (int x = 0; x < testCase; x++) {
//            int now = Integer.parseInt(br.readLine());
//
//            sb.append(dp[now]).append(" ");
//
//
//            // 최솟값은 똑같이 규칙 사용
//            while (now > 0) {
//                if (now % 2 == 1) {
//                    sb.append("7");
//                    now -= 3;
//                } else {
//                    sb.append("1");
//                    now -= 2;
//                }
//            }
//
//            sb.append("\n");
//        }
//
//        System.out.println(sb);
//
//    }
//
//    // 현재 수(now)를 만들기 위해 성냥개비 useMatchStick개를 사용했을 때 만들 수 있는 최솟값 반환
//    public static long add(int now,int useMatchStick) {
//
//        String split =Long.toString(dp[now - useMatchStick]) + min[useMatchStick];
//
//        char[] charArray=split.toCharArray();
//
//        Arrays.sort(charArray);
//
//        int size = split.length();
//
//        int idx = -1;
//
//        // 앞에 0이 오면 안되기 떄문에 0이 어디까지 있는지 체크
//        for (int x = 0; x < size-1; x++) {
//            if(charArray[x]!='0')
//                break;
//            idx = x;
//        }
//
//        char temp = charArray[0];
//        charArray[0] = charArray[idx + 1];
//        charArray[idx + 1] = temp;
//
//        return Long.parseLong(new String(charArray));
//
//    }
//
//}
