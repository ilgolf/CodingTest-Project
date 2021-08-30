import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
		int i,j,k,sz,p,q,c,ans;
		int[][] cost = new int[n+1][n+1];
	    	// 3 수의 합이 21억..(Int Max)을 넘어가지 않게 비용을 7억으로 초기화 
		for(i=1;i<=n;i++) Arrays.fill(cost[i],700000000);
	    	// 자기 자신으로의 경로 비용은 0
        	for(i=1;i<=n;i++) cost[i][i] = 0;
		sz = fares.length;
		for(i=0;i<sz;i++) {
			p = fares[i][0];
			q = fares[i][1];
			c = fares[i][2];
			// p와 q는 양방향으로 이어져 있으므로 둘다 추가 비용을 초기화 해준다
			cost[p][q] = c;
			cost[q][p] = c;
		}
		for(k=1;k<=n;k++) {
			for(i=1;i<=n;i++) {
				for(j=1;j<=n;j++) {
					if(i==j) continue;
					// 핵심 : i에서 k를 거쳐 j로 가는 비용이 더 작다면 값 수정해줌
					if(cost[i][j]>cost[i][k]+cost[k][j]) cost[i][j] = cost[i][k] + cost[k][j];
				}
			}
		}
		ans = Integer.MAX_VALUE;
	    	// s(출발지) 에서 i 까지는 같이가고 i에서는 각자 가야할 곳으로 갈때의 비용 최솟값 계산
	    for(i=1;i<=n;i++) ans = Math.min(ans, cost[s][i]+cost[i][a]+cost[i][b]);
        return ans;
    }
}
