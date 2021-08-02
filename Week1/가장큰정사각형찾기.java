class Solution
{ 
    public int solution(int [][]board)
    {
        int answer = 0;
        for(int i=0; i<board.length; i++)
        {
            for(int j=0; j<board[i].length; j++)
            {
                if(board[i][j]==0)continue;
                if(i-1>=0 && j-1>=0)
                    board[i][j] = 1+Math.min(board[i-1][j-1],Math.min(board[i-1][j],board[i][j-1]));
                if(board[i][j]>answer)
                    answer = board[i][j];
            }
        }
        answer=answer*answer;
        return answer;
    }
}