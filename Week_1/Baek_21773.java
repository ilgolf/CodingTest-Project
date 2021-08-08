import java.io.*;
import java.util.*;

class Process implements Comparable<Process> {

    int id, time, num;

    public Process(int id, int time, int num) {
        this.id = id;  // 사용자 id 
        this.time = time; // 프로세스 시간
        this.num = num;  // 우선순위
    }

    @Override
    public int compareTo(Process o) { // 우선순위 같을 시 id순 정렬
        if(this.num == o.num) {
            return this.id - o.id;
        }

        return o.num - this.num;
    }
}

class Baek_21773 {

    private static int T;
    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Process> queue = new PriorityQueue<>(); // 우선순위 정렬 

        T = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            String[] line = br.readLine().split(" ");

            int id = Integer.parseInt(line[0]);
            int time = Integer.parseInt(line[1]);
            int num = Integer.parseInt(line[2]);

            queue.offer(new Process(id, time, num));
        }

        while(T -- > 0) {
            if(queue.isEmpty()) { break; }

            Process p = queue.poll();
            sb.append(p.id).append('\n');

            if(p.time == 1) { continue; } // 이미 실행된 프로세스는 더 이상 queue에 추가할 필요가 없슴

            queue.offer(new Process(p.id, p.time - 1, p.num - 1));
        }

        System.out.println(sb);
    }
}