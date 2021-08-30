import java.util.*;

class Solution {
    
    public int[][] distance;
    public ArrayList<ArrayList<Point>> connect=new ArrayList<>();
    public int MAX=30000000;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        distance=new int[3][n+1];
        
        for(int x=0;x<3;x++){
            for(int y=0;y<=n;y++)
                distance[x][y]=MAX;
        }
        
        for(int x=0;x<=n;x++)
            connect.add(new ArrayList<>());
        
        for(int x=0;x<fares.length;x++){
            connect.get(fares[x][0]).add(new Point(fares[x][1],fares[x][2]));
            connect.get(fares[x][1]).add(new Point(fares[x][0],fares[x][2]));
        }
        
        distance(s,0);
        distance(a,1);
        distance(b,2);
        
        answer=minFares(n);
        
        
        return answer;
    }
    
    public int minFares(int n){
        int ret=MAX;
        
        for(int x=1;x<=n;x++){
            ret=Math.min(ret,distance[0][x]+distance[1][x]+distance[2][x]);
        }
        return ret;
    }
    
    public void distance(int s,int idx){
        PriorityQueue<Point> queue=new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2){
                return o1.cost-o2.cost;
            }
        });
        
        queue.add(new Point(s,0));
        distance[idx][s]=0;
        
        while(!queue.isEmpty()){
            Point now=queue.poll();
            
            if(distance[idx][now.edge]<now.cost){
                continue;
            }
            
            for(int x=0;x<connect.get(now.edge).size();x++){
                Point next=connect.get(now.edge).get(x);
                
                if(distance[idx][next.edge]>now.cost+next.cost){
                    distance[idx][next.edge]=now.cost+next.cost;
                    queue.add(new Point(next.edge,distance[idx][next.edge]));
                }
            }
            
            
        }
    }
    
    public class Point{
        int edge, cost;
        
        Point(int edge, int cost){
            this.edge=edge;
            this.cost=cost;
        }
    }
}
