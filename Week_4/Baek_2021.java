package Week_4;

import java.util.*;
import java.io.*;

public class Baek_2021 {

	static class Node {
		int to, count;

		public Node(int to, int count) {
			this.to = to;
			this.count = count;
		}
	}
	
	static int N, L, S, E;
	static List<Integer>[] routes, edges;

    public static int bfs() {
		boolean[] visitRoute = new boolean[L + 1]; //route 방문 체크
		boolean[] visitStation = new boolean[N + 1]; //station 방문 체크
		
		Queue<Node> q = new LinkedList<>();
		visitStation[S] = true;
		for (int v : edges[S]) {
			visitRoute[v] = true;
			q.offer(new Node(v, 0));
		}
		
		while (!q.isEmpty()) {
			Node curr = q.poll();
			for (int v : routes[curr.to]) {
				if (v == E) return curr.count;
				if (visitStation[v]) continue;
				visitStation[v] = true;
				for (int nv : edges[v]) {
					if (visitRoute[nv]) continue;
					visitRoute[nv] = true;
					q.offer(new Node(nv, curr.count + 1));
				}
			}
		}
		return -1;
	}

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		routes = new ArrayList[L + 1]; //routes[i]:i번째 경로에 속한 역들의 집합
		edges = new ArrayList[N + 1]; //edges[i]:i번 역이 갈 수 있는 routes들의 집합

		for (int i = 0; i <= L; ++i) routes[i] = new ArrayList<>();
		for (int i = 0; i <= N; ++i) edges[i] = new ArrayList<>();

		for (int i = 1; i <= L; ++i) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < str.length - 1; ++j) {
				int station = Integer.parseInt(str[j]);
				routes[i].add(station);
				edges[station].add(i);
			}
		}
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		bw.write(bfs() + "\n");
	}
}