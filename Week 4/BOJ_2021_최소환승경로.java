// JAVA 11, 시간 1124ms, 메모리 149812KB
// 평범한 BFS / DFS인지 알았으나,,,
// 그렇게 풀면 시간초과 남
// 역의 개수 N(100,000) * 노선의 개수 L(100,000) =  100,0000,0000이라 시간 초과가 나는거 같다.
// 우선순위큐로 풀었으나 메모리와 시간을 많이 사용함 (메모리 : 252760KB, 시간 : 2560ms)
// list에 현재 역 X에서 갈 수 있는 노선과 현재 노선 X를 통해 갈 수 있는 역들을 저장해서
// 이것들을 이용해서 BFS/DFS 한다면 N+L (200,000)으로 아까방법에 비해서 시간 절약 가능!

// 역 -> 역 -> 역 탐색이 아닌 역-> 노선 -> 역 식으로 탐색

import java.io.*;
import java.util.*;


public class Main {


    // 역의 개수, 노선의 개수
    public static int stationNum, lineNum;
    // 1 - N : x번째 역을 지나는 노선 번호 저장 , N+1 - N+L : x번째 노선이 지나는 역 번호 저장
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    // 방문 확인
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        stationNum = Integer.parseInt(st.nextToken());
        lineNum = Integer.parseInt(st.nextToken());

        visited = new boolean[stationNum + lineNum + 1];


        for(int x=0;x<=stationNum+lineNum;x++){
            list.add(new ArrayList<>());
        }

        for(int x=1;x<=lineNum;x++){
            st = new StringTokenizer(br.readLine());

            int countToken = st.countTokens();

            for(int y=0;y<countToken-1;y++){

                int now = Integer.parseInt(st.nextToken());
                // x번째 노선에 현재 역 번호 저장
                list.get(stationNum+x).add(now);
                // 현재 역 번호에 x번째 노선 저장
                list.get(now).add(stationNum + x);
            }

        }

        st = new StringTokenizer(br.readLine());

        bw.write(String.valueOf(minChangeNum(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))));
        bw.flush();

    }

    public static int minChangeNum(int start, int end){
        Queue<int[]> queue = new LinkedList<>();

        // 시작 노선 번호 저장
        for(int next : list.get(start)){
            queue.add(new int[]{next,0});
        }


        while(!queue.isEmpty()){
            int[] now=queue.poll();


            // 종착지에 도착하면 return
            if(now[0]==end){
                return now[1];
            }

            for(int next : list.get(now[0])){

                // 방문 체크
               if(visited[next])
                    continue;

                visited[next]=true;

                if(next<=stationNum){
                    // 다음 차례가 역 번호라면 -> 지금은 같은 노선 -> 따라서 환승 횟수 증가 X
                    queue.add(new int[]{next,now[1]});
                }else{
                    // 다음 차례가 노선 번호라면 -> 지금은 같은 역 다른 노선 -> 환승 횟수 증가
                    queue.add(new int[]{next, now[1] + 1});

                }
            }


        }
        return -1;
    }

}
