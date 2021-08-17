// JAVA 11, 시간 288ms, 메모리 16388KB
// o(N^2) 풀이
// 정렬 후 기준점 설정 (o(N)) 그 후 나머지로 투 포인터 진행(o(N))

import java.io.*;
import java.util.*;


public class Main {

    public static int num;
    public static long[] list;
    // 1,000,000,000이 최대 -> 세 용액의 합은 3,000,000,000까지 가능 (int 범위 초과)
    public static long MAX = 3000000001L;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        num = Integer.parseInt(br.readLine());
        list=new long[num];
        long[] answer={0,0,0};

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int x = 0; x < num; x++) {
            list[x] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(list);


        long result=MAX;

        // 기준점 X
        for(int x=0;x<num-2;x++){

            int start=x+1;
            int end=num-1;
            long baseNum=list[x];

            // 투포인터
            while(start<end){

                long sum = baseNum + list[start] + list[end];

                // 현재까지 결과보다 지금의 합이 더 작으면,,, answer에 저장
                if(Math.abs(sum)<result){
                    answer[0] = list[x];
                    answer[1] = list[start];
                    answer[2] = list[end];
                    result = Math.abs(sum);
                }

                if(sum==0){
                    break;
                }

                if(sum<0){
                    start++;
                }else{
                    end--;
                }
            }

        }


        for(long x : answer){
            bw.write(String.valueOf(x)+" ");
        }

        bw.flush();
    }



}
