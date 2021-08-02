import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    final static int[] dx = { 0, 0, -1, 1 };
	final static int[] dy = { 1, -1, 0, 0 };

	static class Pair {
		int x;
		int y;
		int z;

		Pair(int x, int y,int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
    public int solution(int[][] board) {
        Queue<Pair> will=new LinkedList<Pair>();
        int n = board.length;
        int m = board[0].length;
        int[][][] a = new int[n][m][4];
        will.add(new Pair(0,0,0));
        will.add(new Pair(0,0,3));
        while(!will.isEmpty())
        {
        	Pair p = will.poll();
        	for(int i=0; i<4; i++)
        	{
        		int x_dx = p.x+dx[i];
        		int y_dy = p.y+dy[i];
        		if(x_dx<0 || y_dy<0 || x_dx>=n ||y_dy>=m)continue;
        		if(board[x_dx][y_dy]==1)continue;
        		int num=0;
    			if(p.z==i)
    				num= a[p.x][p.y][p.z]+100;
    			else if((i==3 || i==2) && (p.z==0 || p.z==1))
    				num=a[p.x][p.y][p.z]+600;
    			else if((i==1 || i==0) && (p.z==2 || p.z==3))
    				num=a[p.x][p.y][p.z]+600;
    			if((p.z==1 && i==0)||(p.z==0 && i==1))continue;
    			if((p.z==2 && i==3)||(p.z==3 && i==2))continue;
    			if( a[x_dx][y_dy][i]==0 || (num < a[x_dx][y_dy][i]&&num!=0)) {
    				will.add(new Pair(x_dx,y_dy,i));
    				a[x_dx][y_dy][i]=num;
    			}
        	}
        }
        int min=1000000;
        for(int i=0; i<4;i++)
        {
        	if(a[n-1][m-1][i]==0)continue;
        	if(a[n-1][m-1][i]<min)
        		min=a[n-1][m-1][i];
        }
        return min;
    }
}