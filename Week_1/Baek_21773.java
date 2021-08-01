import java.io.*;
import java.util.*;

class Process implements Comparable<Process> {

    int id, time, num;

    public Process(int id, int time, int num) {
        this.id = id;
        this.time = time;
        this.num = num;
    }

    @Override
    public int compareTo(Process o) {
        if(this.num == o.num) {
            return this.id - o.id;
        }

        return o.num - this.num;
    }
}

class Main {

    private static int T;
    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PriorityQueue<Process> queue = new PriorityQueue<>();

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

            Process P = queue.poll();
            sb.append(P.id).append('\n');

            if(P.time == 1) { continue; }

            queue.offer(new Process(P.id, P.time - 1, P.num - 1));
        }

        System.out.println(sb);
    }
}