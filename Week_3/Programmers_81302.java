import java.util.*;

class Location{
	int h;
	int w;
	
	public Location(int h, int w) {
		this.h = h;
		this.w = w;
	}
	
	int getH() {
		return h;
	}
	
	int getW() {
		return w;
	}
}

class Solution {
    
    private static int dh[] = {-1,0,1,0};
	private static int dw[] = {0,1,0,-1};
	
	public static boolean checkDistance(String[] places, Location location) {
		Queue<Location> q = new LinkedList<>();
		int[][] visited = new int[5][5];
		q.offer(location);
        visited[location.getH()][location.getW()] = 1;
		
		while(!q.isEmpty()) {
			int h = q.peek().getH();
			int w = q.peek().getW();
			q.poll();

			for(int i=0; i<4; i++) {
				int nh = h + dh[i];
				int nw = w + dw[i];
				
				if(nh >= 0 && nh < 5 && nw >= 0 && nw < 5 && visited[nh][nw] == 0) {
					if(places[nh].charAt(nw) != 'X') {
						visited[nh][nw] = visited[h][w]+1;
												
						q.offer(new Location(nh, nw));
					}
					
					if(visited[nh][nw] <= 3 && places[nh].charAt(nw) == 'P') {
						return false;
					}
				}
			}
		}
		
		return true;
	}
    
    public static boolean check(String[] places) {
		boolean flag = true;
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(places[i].charAt(j) == 'P') {
					if(!checkDistance(places, new Location(i, j))) {
						return false;
					}
				}
			}
		}
		if(!flag) {
			return false;
		}else {
			return true;
		}
	}
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
		
		for(int i=0; i<places.length; i++) {
			if(check(places[i])) {
				answer[i] = 1;
			}else {
				answer[i] = 0;
			}
		}
        
        return answer;
    }
}