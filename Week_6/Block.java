package Week_6;

import java.util.*;

public class Block {

    static class Position {
        int x, y, minX, minY, maxX, maxY;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean[][] checkedBoard;
    static boolean[][] checkedTable;
    static int[][] moving = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = 0;

    static List<Position> extractBlock(int[][] board, Position p, boolean isBoard) {
        int boardSize = board.length;
        List<Position> list = new ArrayList<>();
        Queue<Position> queue = new LinkedList<>();

        queue.offer(p);

        if(isBoard) {
            checkedBoard[p.x][p.y] = true;
        } else {
            checkedTable[p.x][p.y] = true;
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        while(!queue.isEmpty()) {
            Position start = queue.poll();

            list.add(start);
            minX = Math.min(start.x, minX);
            minY = Math.min(start.y, minY);
            maxX = Math.max(start.x, maxX);
            maxY = Math.max(start.y, maxY);

            for (int i = 0; i < 4; i++) {
                int row = start.x  + moving[i][0];
                int col = start.y + moving[i][1];

                if (row < 0 || col < 0 || row > boardSize - 1 || col > boardSize -1) continue;

                if(isBoard){
                    if(board[row][col] == 1 || checkedBoard[row][col]) continue;
                    checkedBoard[row][col] = true;
                } else {
                    if(board[row][col] == 0 || checkedTable[row][col]) continue;
                    checkedTable[row][col] = true;
                }
                queue.offer(new Position(row, col));
            }
        }
        list.get(0).minX = minX;
        list.get(0).minY = minY;
        list.get(0).maxX = maxX;
        list.get(0).maxY = maxY;

        return list;
    }

    static int[][] makeBlock(List<Position> list) {
        int[][] result = new int[50][50];
        int minX = list.get(0).minX;
        int minY = list.get(0).minY;

        int emptyBlockSize = list.size();
        for (Position p : list) {
            result[p.x - minX][p.y - minY] = 1;
        }
        return result;
    }

    static boolean isSame(int[][] empty, int[][] block) {
        for (int i = 0; i < empty.length; i++) {
            for (int j = 0; j < empty[0].length; j++) {
                if(block[i][j] != empty[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    static int[][] rotateBlock(int[][] block, int row, int col) {
        int[][] tempBlock = new int[50][50];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tempBlock[j][row - 1 - i] = block[i][j];
            }
        }

        return tempBlock;
    }

    public int solution(int[][] game_board, int[][] table) {
        int boardSize = game_board.length;
        checkedBoard = new boolean[boardSize][boardSize];
        checkedTable = new boolean[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {

                if(game_board[i][j] == 1 || checkedBoard[i][j]) { continue; }

                List<Position> emptyCoord = extractBlock(game_board, new Position(i, j), true);
                int[][] empty = makeBlock(emptyCoord);

                match:
                for (int k = 0; k < boardSize; k++) {
                    for (int l = 0; l < boardSize; l++) {

                        if(table[k][l] == 0 || checkedTable[k][l]) { continue; }

                        List<Position> blockCoord = extractBlock(table, new Position(k, l), false);

                        if(emptyCoord.size() != blockCoord.size()) { continue; }

                        int[][] block = makeBlock(blockCoord);
                        int row = blockCoord.get(0).maxX - blockCoord.get(0).minX + 1;
                        int col = blockCoord.get(0).maxY - blockCoord.get(0).minY + 1;

                        for (int m = 0; m < 4; m++) {

                            if(isSame(empty, block)) {
                                for (Position rollback : blockCoord) {
                                    table[rollback.x][rollback.y] = 0;
                                }
                                answer += blockCoord.size();
                                break match;
                            }

                            block = rotateBlock(block, row, col);
                            int temp = row;
                            row = col;
                            col = temp;
                        }
                    }
                }
                checkedTable = new boolean[boardSize][boardSize];
            }
        }
        return answer;
    }
}
