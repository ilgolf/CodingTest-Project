// JAVA 11, 시간 368ms, 메모리 29428KB
// 백트래킹으로 문제 해결

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    // N개의 자연수, 자연수 M
    public static int arrayLength,makeLength;
    // N개의 자연수 저장
    public static int [] array;
    // 길이가 M인 수열 저장
    public static int[] answer;
    // 자연수를 전에 이미 사용했는지 확인
    public static boolean[] visited;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        StringTokenizer st = new StringTokenizer(br.readLine());

        arrayLength = Integer.parseInt(st.nextToken());
        makeLength = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        answer = new int[makeLength];
        array = new int[arrayLength];
        visited = new boolean[arrayLength];

        for (int x = 0; x < arrayLength; x++) {
            array[x] = Integer.parseInt(st.nextToken());
        }

        // 정렬 -> 사전 순으로 증가하는 순으로 출력하기 위함
        Arrays.sort(array);


        makeArray(0);

        System.out.println(sb);

    }

    public static void makeArray(int answer_idx) {
        // base case
        if (answer_idx == makeLength) {
            for(int x : answer){
                sb.append(x).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int x = 0; x < arrayLength; x++) {
            // 전에 방문하지 않은 것들만 사용
            if (!visited[x]) {
                // 사용했다고 체크
                visited[x]=true;
                answer[answer_idx] = array[x];
                makeArray(answer_idx+1);
                // 다시 초기화
                visited[x]=false;
            }
        }
    }




}
