// DFS
// 현재 컴퓨터 위치에서 갈 수 있는 컴퓨터들을 DFS로 탐색한다.
// 이전에 방문하지 않은 컴퓨터 -> 이전과는 다른 네트워크 -> answer 1 추가!

class Solution {
    
    // 방문 체크
    public static boolean[] visited;
    public static int computerNum;
    public static int answer=0;
    
    public int solution(int n, int[][] computers) {
        
        
        computerNum=n;
        
        visited=new boolean[computerNum];
        
      // 0번부터 n-1 컴퓨터까지 확인
        for(int x=0;x<n;x++){
            if(!visited[x]){
              // 이전에 방문하지 않았으면 다른 네트워크! 
                visited[x]=true;
                dfs(x,computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    public static void dfs(int x, int[][] computers){
        
        // x와 y가 연결되어 있고, y를 이전에 방문하지 않은 경우 -> 탐색
        for(int y=0;y<computerNum;y++){
            if(computers[x][y]==1 && !visited[y]){
                visited[y]=true;
                dfs(y,computers);
            }
        }
        
       
    }
}
