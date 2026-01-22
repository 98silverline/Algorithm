class Solution {
    public void rotate(int[][] matrix) {
        Queue<Integer> queue = new LinkedList<>();
        int size = matrix.length;
        for(int i = 0; i < size; i++) {
            for(int j = size - 1; j >= 0; j--) {
                queue.offer(matrix[j][i]);
            }
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                matrix[i][j] = queue.poll();
            }
        }
    }
}