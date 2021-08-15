import java.util.*;
import java.io.*;

class Solution {
    
    public static int size;
    public static boolean[][][] visited;
    
    public int solution(int[][] board) {
        int answer = 0;
        size=board.length;
    
        Queue<Point> queue=new LinkedList<>();
        
        //[][][0] : 가로, [][][1]: 세로
        visited=new boolean[size][size][2];
            
        queue.add(new Point(0,0,true));
        
        visited[0][0][0]=true;
        
        boolean flag=false;
        
        
        while(!queue.isEmpty()){
            int queueSize=queue.size();
            
            for(int i=0;i<queueSize;i++){
                Point now=queue.poll();
                
                
                if(now.x==size-1 && now.y==size-2 && now.direction){
                    flag=true;
                    break;
                }
                
                if(now.x==size-2 && now.y==size-1 && !now.direction){
                    flag=true;
                    break;
                }

                
                
                if(now.direction){
                    if(isValid(now.x,now.y+2) && board[now.x][now.y+2]==0 && !visited[now.x][now.y+1][0]){
                        queue.add(new Point(now.x,now.y+1,true));
                        visited[now.x][now.y+1][0]=true;
                    }
                    
                    if(isValid(now.x,now.y-1) && board[now.x][now.y-1]==0 && !visited[now.x][now.y-1][0]){
                        queue.add(new Point(now.x,now.y-1,true));
                        visited[now.x][now.y-1][0]=true;
                    }
                    
                    
                    if(isValid(now.x-1,now.y) && isValid(now.x-1,now.y+1) && board[now.x-1][now.y]==0 && board[now.x-1][now.y+1]==0){
                    
                        if(!visited[now.x-1][now.y+1][1]){
                            visited[now.x-1][now.y+1][1]=true;
                            queue.add(new Point(now.x-1,now.y+1,false));
                        }
                        
                        if(!visited[now.x-1][now.y][1]){
                            visited[now.x-1][now.y][1]=true;
                            queue.add(new Point(now.x-1,now.y,false));
                        }
                        
                    
                        if(!visited[now.x-1][now.y][0]){
                            visited[now.x-1][now.y][0]=true;
                            queue.add(new Point(now.x-1,now.y,true));
                        }
                    }
                    
                    if(isValid(now.x+1,now.y) && isValid(now.x+1,now.y+1) && board[now.x+1][now.y]==0 && board[now.x+1][now.y+1]==0){
                        
                        if(!visited[now.x][now.y][1]){
                            visited[now.x][now.y][1]=true;
                            queue.add(new Point(now.x,now.y,false));
                        }
                        
                        if(!visited[now.x][now.y+1][1]){
                            visited[now.x][now.y+1][1]=true;
                            queue.add(new Point(now.x,now.y+1,false));
                        }
                        
                        if(!visited[now.x+1][now.y][0]){
                            visited[now.x+1][now.y][0]=true;
                            queue.add(new Point(now.x+1,now.y,true));
                        }
                    }
                       
                }else{
                    
                    if(isValid(now.x+2,now.y) && board[now.x+2][now.y]==0 && !visited[now.x+1][now.y][1]){
                        queue.add(new Point(now.x+1,now.y,false));
                        visited[now.x+1][now.y][1]=true;
                    }
                    
                    if(isValid(now.x-1,now.y) && board[now.x-1][now.y]==0 && !visited[now.x-1][now.y][1]){
                        queue.add(new Point(now.x-1,now.y,false));
                        visited[now.x-1][now.y][1]=true;
                    }
                    
                    
                    if(isValid(now.x,now.y+1) && isValid(now.x+1,now.y+1) && board[now.x][now.y+1]==0 && board[now.x+1][now.y+1]==0){
                    
                        if(!visited[now.x][now.y][0]){
                            visited[now.x][now.y][0]=true;
                            queue.add(new Point(now.x,now.y,true));
                        }
                        
                        if(!visited[now.x+1][now.y][0]){
                            visited[now.x+1][now.y][0]=true;
                            queue.add(new Point(now.x+1,now.y,true));
                        }
                        
                        if(!visited[now.x][now.y+1][1]){
                            visited[now.x][now.y+1][1]=true;
                            queue.add(new Point(now.x,now.y+1,false));
                        }
                    }
                    
                    if(isValid(now.x,now.y-1) && isValid(now.x+1,now.y-1) && board[now.x][now.y-1]==0 && board[now.x+1][now.y-1]==0){
                        
                        if(!visited[now.x+1][now.y-1][0]){
                            visited[now.x+1][now.y-1][0]=true;
                            queue.add(new Point(now.x+1,now.y-1,true));
                        }
                        
                        if(!visited[now.x][now.y-1][0]){
                            visited[now.x][now.y-1][0]=true;
                            queue.add(new Point(now.x,now.y-1,true));
                        }
                        
                        if(!visited[now.x][now.y-1][1]){
                            visited[now.x][now.y-1][1]=true;
                            queue.add(new Point(now.x,now.y-1,false));
                        }
                    }
                }
            }
            
            if(flag)
                break;
            
            answer++;
        }
        
        
        return answer;
    }
    
    // 범위안에 있는지 확인
    public static boolean isValid(int x, int y){
        return 0<=x && x<size && 0<=y && y<size;
    }
    
    // direction true : 가로 , false : 세로
    public static class Point{
        int x, y;
        boolean direction;
        
        Point(int x, int y,boolean direction){
            this.x=x;
            this.y=y;
            this.direction=direction;
        }
        
    }
}
