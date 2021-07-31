public class Square {
    
    public int solution(int[][] board) { 
        int answer = 0;

        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[i].length; j++) {
                if(board[i][j] > 0) {
                    int up = (j - 1 >= 0) ? board[i][j - 1] : 0;
                    int left = (i - 1 >= 0) ? board[i - 1][j] : 0;
                    int diagonal = (i - 1 >= 0 && j - 1 >= 0) ? board[i - 1][j - 1] : 0;

                    board[i][j] = Math.min(Math.min(up, left), diagonal) + 1;

                    answer = Math.min(answer, board[i][j]);
                }
            }
        }

        return answer * answer;
    }
}
