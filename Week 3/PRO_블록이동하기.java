import java.util.*;
import java.io.*;

class Solution {
    
    public static int size;
    public static boolean[][][] visited;
    public static int[][] move={{0,-1},{-1,0},{1,0},{0,1}};
    
    public int solution(int[][] board) {
        int answer = 0;
        size=board.length;
    
        Queue<Point> queue=new LinkedList<>();
        
        //[][][0] : 가로, [][][1]: 세로
        visited=new boolean[size][size][2];
            
        queue.add(new Point(0,0,true,0));
        
        visited[0][0][0]=true;
        
        boolean flag=false;
        
        
        while(!queue.isEmpty()){
            
            Point now=queue.poll();
            
            System.out.println(now.x+" "+now.y+" "+now.move+" "+now.direction);
            
            if(now.direction && now.x==size-1 && now.y==size-2){
                answer=now.move;
                break;
            }
            if(!now.direction && now.x==size-2 && now.y==size-1){
                answer=now.move;
                break;
            }
            
            for(int i=0;i<4;i++){
                int next_x=now.x+move[i][0];
                int next_y=now.y+move[i][1];
                
                if(now.direction){
                    if(!isValid(next_x,next_y,next_x,next_y+1,now.direction,board))
                        continue;
                }else{
                    if(!isValid(next_x,next_y,next_x+1,next_y,now.direction,board))
                        continue;
                }

                
        
                queue.add(new Point(next_x,next_y,now.direction,now.move+1));
                visited[next_x][next_y][now.direction?0:1]=true;
                
                if(now.direction){
                    
                    if(i==1){
                        
                        if(!visited[next_x][next_y][now.direction?1:0]){
                            queue.add(new Point(next_x,next_y,!now.direction,now.move+1)); 
                            visited[next_x][next_y][now.direction?1:0]=true;
                        }
                        if(!visited[next_x][next_y+1][now.direction?1:0]){
                            queue.add(new Point(next_x,next_y+1,!now.direction,now.move+1));
                            visited[next_x][next_y+1][now.direction?1:0]=true;
                        }
                    }else if(i==2){
                        if(!visited[now.x][now.y][now.direction?1:0]){
                            queue.add(new Point(now.x,now.y,!now.direction,now.move+1));
                            visited[now.x][now.y][now.direction?1:0]=true;
                        }
                        if(!visited[now.x][now.y+1][now.direction?1:0]){
                            queue.add(new Point(now.x,now.y+1,!now.direction,now.move+1));
                            visited[now.x][now.y+1][now.direction?1:0]=true;
                        }
                    }
                    
                }else{
                    if(i==0){
                        if(!visited[next_x][next_y][now.direction?1:0]){
                            queue.add(new Point(next_x,next_y,!now.direction,now.move+1)); 
                            visited[next_x][next_y][now.direction?1:0]=true;
                        }
                        if(!visited[next_x+1][next_y][now.direction?1:0]){
                            queue.add(new Point(next_x+1,next_y,!now.direction,now.move+1));
                            visited[next_x+1][next_y][now.direction?1:0]=true;
                        }
                    }else if(i==3){
                        if(!visited[now.x][now.y][now.direction?1:0]){
                            queue.add(new Point(now.x,now.y,!now.direction,now.move+1)); 
                            visited[now.x][now.y][now.direction?1:0]=true;
                        }
                        if(!visited[now.x+1][now.y][now.direction?1:0]){
                            queue.add(new Point(now.x+1,now.y,!now.direction,now.move+1));
                            visited[now.x+1][now.y][now.direction?1:0]=true;
                        }
                    }
                }
            }
        }
        
        
        return answer;
    }
    
    // 이동 가능한지 확인
    public static boolean isValid(int x1, int y1,int x2, int y2, boolean direction,int[][] board){
        
        if(x1<0 || x1>=size || y1<0 || y1>=size || x2<0 || x2>=size || y2<0 || y2>=size)
            return false;
        
        if(visited[x1][y1][direction?0:1])
            return false;
        
        if(board[x1][y1]==1)
            return false;
        
        if(board[x2][y2]==1)
            return false;
        
        return true;
    }
    
    // direction true : 가로 , false : 세로
    public static class Point{
        int x, y;
        boolean direction;
        int move;
        
        Point(int x, int y,boolean direction,int move){
            this.x=x;
            this.y=y;
            this.direction=direction;
            this.move=move;
        }
        
    }
}
