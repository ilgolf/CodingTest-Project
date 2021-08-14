// 첫번째 코드 - 이중 배열
// 두번째 코드 - DFS

import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = {0,0,0,0,0};
        
        for(int testCase=0;testCase<5;testCase++){
            ArrayList<Point> list=new ArrayList<>();
            int size=0;
            boolean flag=false;
            
            // P의 위치 확인, ArrayList에 좌표 저장
            for(int x=0;x<5;x++){
                for(int y=0;y<5;y++){
                    if(places[testCase][x].charAt(y)=='P'){
                        list.add(new Point(x,y));
                    }
                }
            }
            
            size=list.size();
            
            for(int x=0;x<size-1;x++){
                
                Point now=list.get(x);
                
                for(int y=x+1;y<size;y++){
                    Point next=list.get(y);
                    
                    // 맨해튼 거리가 2 이상이면 확인해볼 필요 없음.
                    if(Math.abs(now.x-next.x)+Math.abs(now.y-next.y)>2)
                        continue;
                    
                    // 두개의 x 좌표 또는 y 좌표가 같으면 두개의 좌표 사이에 있는 것이 X여야 함.
                    if(now.x==next.x){
                        if(places[testCase][now.x].charAt((now.y+next.y)/2)=='X')
                            continue;
                    }else if(now.y==next.y){
                        if(places[testCase][(now.x+next.x)/2].charAt(now.y)=='X')
                            continue;
                    }else{
                        // 두개의 x좌표 y좌표가 다르면, 2개의 위치에 있는 값이 X여야 함.
                        // P X
                        // X P
                        if(places[testCase][now.x].charAt(next.y)=='X' && places[testCase][next.x].charAt(now.y)=='X')
                            continue;
                    }
                    
                    flag=true;
                    break;
                }
                
                if(flag)
                    break;
            }
            
            answer[testCase]=(flag)?0:1;
            
            
        }
        
        return answer;
    }
    
  // 좌표를 저장하기 위한 Class
    public static class Point{
        int x, y;
        
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}

// class Solution {
    
//     public static boolean[][] visited;
//     public static int[][] move={{0,1},{1,0},{-1,0},{0,-1}};
    
//     public int[] solution(String[][] places) {
//         int[] answer = {1,1,1,1,1};
        
//         for(int testCase=0;testCase<5;testCase++){
            
//           // 방문 체크
//             visited=new boolean[5][5];
//             boolean flag=false;
            
//             for(int x=0;x<5;x++){
//                 for(int y=0;y<5;y++){
//                   // P 주변 맨해튼 거리 2 이하로 거리를 지키지 않은 경우가 있는지 확인
//                     if(places[testCase][x].charAt(y)=='P'){
//                         visited[x][y]=true;
//                         if(!dfs(x,y,0,places[testCase])){
//                             answer[testCase]=0;
//                             flag=true;
//                             break;
//                         }
//                         visited[x][y]=false;
//                     }
//                 }
//                 if(flag)
//                     break;
//             }
            
//         }
        
//         return answer;
//     }
    
//     public static boolean dfs(int x, int y, int count, String[] room){
//       // 거리가 2 초과
//         if(count>2)
//             return true;
        
//       // 맨해튼 거리 2 이하에 사람이 있다 -> false
//         if(count!=0 && room[x].charAt(y)=='P')
//             return false;
        
//         boolean result=true;
        
//         for(int idx=0;idx<4;idx++){
            
//             int nx=x+move[idx][0];
//             int ny=y+move[idx][1];
            
//           // 다음 위치가 범위를 벗어나지 않고 파티션이 아닌 경우 이동
//             if(nx>=0 && nx<5 && ny>=0 && ny<5 && room[nx].charAt(ny)!='X'){
//                 if(!visited[nx][ny]){
//                     visited[nx][ny]=true;
//                     result&=dfs(nx,ny,count+1,room);
//                 }
//             }
//         }
//         return result;
//     }
// }
