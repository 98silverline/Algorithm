import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] minTree, maxTree, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long treeNode = Math.round(Math.pow(2, treeHeight));
        minTree = new int[Math.toIntExact(treeNode)];
        maxTree = new int[Math.toIntExact(treeNode)];
    }

    static int treeInit1(int node, int start, int end) {
        if(start == end) {
            return minTree[node] = arr[start];
        } else return minTree[node] = Math.min(treeInit1(node * 2, start, (start + end) / 2)
        , treeInit1(node * 2 + 1, (start + end) / 2 + 1, end));
    }

    static int treeInit2(int node, int start, int end) {
        if(start == end) {
            return maxTree[node] = arr[start];
        } else return maxTree[node] = Math.max(treeInit2(node * 2, start, (start + end) / 2)
        , treeInit2(node * 2 + 1, (start + end) / 2 + 1, end));
    }

    static int findMin(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return Integer.MAX_VALUE;
        else {
            if(start >= left && end <= right) return minTree[node];
            else {
                return Math.min(findMin(node * 2, start, (start + end) / 2, left, right)
                , findMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
            }
        }
    }

    static int findMax(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return Integer.MIN_VALUE;
        else {
            if(start >= left && end <= right) return maxTree[node];
            else {
                return Math.max(findMax(node * 2, start, (start + end) / 2, left, right)
                        , findMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        treeSetting();
        treeInit1(1, 0, N - 1);
        treeInit2(1, 0, N - 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;
            sb.append(findMin(1, 0, N - 1, left, right)).append(" ").append(findMax(1, 0, N - 1, left, right)).append("\n");
        }
        System.out.println(sb);
    }
}