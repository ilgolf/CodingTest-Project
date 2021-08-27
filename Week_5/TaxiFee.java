package Week_5;

/**
 * 
 * 간선을 무조건 지나가야함 (a 나 b)
 * 일단 내가볼땐 다익스트라 같음
 * 간선을 지나가면서 각 값들을 더 했을때 최소값을 찾아야함
 * 
 */

import java.util.*;

public class TaxiFee {

    static class Node implements Comparable<Node> {
        int idx;
        int distance;
        
        public Node(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    static final int INF = (int)1e9; // 최대값
    static ArrayList<ArrayList<Node>> map = new ArrayList<>(); // 각 노드에 대한 정보 담을거임

    static int[] dijkstra(int start, int[] costs) {
        PriorityQueue<Node> queue = new PriorityQueue<>(map.get(start)); // a나 b 일경우엔 시작지점이 아니므로 그대로 정점 간선 가중치대로 출발
        
        for(Node n : map.get(start)) {
            costs[n.idx] = n.distance;
        }

        costs[start] = 0;
        
        while(!queue.isEmpty()){
            Node node = queue.poll();
            if(costs[node.idx] < node.distance) continue;
            for(Node ne : map.get(node.idx)){
                if(costs[ne.idx] > node.distance + ne.distance){
                    costs[ne.idx] = node.distance + ne.distance;
                    queue.offer(new Node(ne.idx, node.distance + ne.distance));
                }
            }
        }
        return costs;
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        for(int i = 0 ; i <= n ; i ++) {
            map.add(new ArrayList<>());
        }

        for(int[] i : fares){
            map.get(i[0]).add(new Node(i[1], i[2]));
            map.get(i[1]).add(new Node(i[0], i[2]));
        }

        int[] A = new int[n + 1];
        int[] B = new int[n + 1];
        int[] S = new int[n + 1];

        Arrays.fill(A, 100000 * n);
        Arrays.fill(B, 100000 * n);
        Arrays.fill(S, 100000 * n);

        A = dijkstra(a, A);
        B = dijkstra(b, B);
        S = dijkstra(s, S);

        for(int i=1; i<=n; i++) {
            answer = Math.min(answer, A[i] + B[i] + S[i]);
        }

        return answer;
    }
}
