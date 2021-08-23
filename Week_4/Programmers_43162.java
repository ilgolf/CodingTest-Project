import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] computers) {
		int i,j,ans,sz,com;
		boolean[] chk = new boolean[n];
	    	// node를 담을 ArrayList 생성
		ArrayList<Integer>[] node = new ArrayList[n];
		for(i=0;i<n;i++) node[i] = new ArrayList<Integer>();
	    	// i로부터 j로가는 길이 있을경우 경로 추가
		for(i=0;i<n;i++) for(j=0;j<n;j++) if(computers[i][j]==1) node[i].add(j);
		ans = 0;
	    	//bfs 시작
		for(i=0;i<n;i++) {
			// i 번째 컴퓨터를 방문하지 않았을 경우 정답을 1 올려준다.
			if(!chk[i]) {
				// 큐에 i 번째 컴퓨터를 넣어주고 큐가 빌때까지 방문한다.
				ans++;
				Queue<Integer> q = new LinkedList<>();
				q.add(i);
				chk[i] = true;
				while(!q.isEmpty()) {
					com = q.poll();
					sz = node[com].size();
					//해당 컴퓨터에서 갈 수 있는 경로를 모두 확인한다.
					for(j=0;j<sz;j++) {
						if(!chk[node[com].get(j)]) {
							chk[node[com].get(j)] = true;
							q.add(node[com].get(j));
						}
					}
				}
				
			}
		}
        return ans;
    }
}
