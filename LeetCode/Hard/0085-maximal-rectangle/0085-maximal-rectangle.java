import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static int answer;
    static int[][] board;
    static class Rectangle {
        int width;
        int height;
        int x;
        int y;

        public Rectangle(int width, int height, int x, int y) {
            this.width = width;
            this.height = height;
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int x, int y) {
        Queue<Rectangle> que = new LinkedList<>();
        que.offer(new Rectangle(1, board[x][y], x, y));
        int extent = board[x][y];
        while (!que.isEmpty()) {
            Rectangle cur = que.poll();
            // System.out.println(cur.width + ", " + cur.height + ", " + cur.x + ", " + cur.y);
            extent = Math.max(extent, cur.width * cur.height);
            int px = cur.x;
            int py = cur.y + 1;
            //py가 좌표 바깥이 아니면서 0이 아님
            if(py < board[0].length && board[px][py] != 0) {
                cur.height = Math.min(board[px][py], cur.height);
                cur.width++;
                que.offer(new Rectangle(cur.width, cur.height, px, py));
            };
        }
        return extent;
    }

    public int maximalRectangle(char[][] matrix) {
        answer = 0;
        board = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if(i > 0) {
                        board[i][j] = board[i - 1][j] + 1;
                    } else {
                        board[i][j] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(board[i][j] != 0) {
                    answer = Math.max(answer, Math.max(board[i][j], solution(i, j)));
                    // System.out.println("x: " + i + ", y: " + j + ", 크기: " + Math.max(board[i][j], solution(i, j)));
                }
            }
        }
        
        return answer;
    }
}