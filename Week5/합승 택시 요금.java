package 신규아이디추천;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

	static class Edge implements Comparable<Edge> {
		int v, weight; //도착지 ,가중치

		public Edge(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return weight + "";
		}

	}

	 public static int solution(int n, int s, int a, int b, int[][] fares) {
	        int answer = 0;
	        List<Edge>[] adj = new ArrayList[n+1];
			for (int i = 1; i <= n; i++)
				adj[i] = new ArrayList<>();
			for (int i = 0; i <fares.length ; i++) {
				// 첫번째가 출발지, 두번째가 도착지, 세번째가 가중치ㅋ
				adj[fares[i][0]].add(new Edge(fares[i][1], fares[i][2]));
				adj[fares[i][1]].add(new Edge(fares[i][0], fares[i][2]));
			}
			//
			// dijkstra
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			boolean[] check = new boolean[n+1];
			Edge[] D = new Edge[n+1];
			// 0번에서 출발하는걸로.
			for (int i = 1; i <= n; i++) {
				// 원하는 출발지
				if (i == s) {
					D[i] = new Edge(i, 0);
				} else {
					D[i] = new Edge(i, Integer.MAX_VALUE);
				}
				pq.add(D[i]);
			}
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				System.out.println(edge.v);
				for (Edge next : adj[edge.v]) {
					// check되지 않았으면서, D[next.v]가 D[edge.v] + next.weight 보다 더 크다면 갱신
					if (!check[next.v] && D[next.v].weight > D[edge.v].weight + next.weight) {
						D[next.v].weight = D[edge.v].weight + next.weight;
						// decrease key
						pq.remove(D[next.v]);
						pq.add(D[next.v]);
					}
				}
				check[edge.v] = true;
			}
			System.out.println(Arrays.toString(D));
	        return answer;
	    }
	public static void main(String[] args) throws IOException {
		int n=6;
		int s=4;
		int a=6;
		int b=2;
		int[][] fares= {{4, 1, 10},{3, 5, 24},{5, 6, 2},{3, 1, 41},{5, 1, 24},{4, 6, 50},{2, 4, 66},{2, 3, 22},{1, 6, 25}};

		System.out.println( solution(n, s, a, b, fares));
	}
}