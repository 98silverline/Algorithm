import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr, minTree, maxTree;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        minTree = new int[(int) nodeNum];
        maxTree = new int[(int) nodeNum];
    }

    static int minTreeInit (int start, int end, int node) {
        if(start == end) return minTree[node] = arr[start];
        else {
            return minTree[node] = Math.min(minTreeInit(start, (start + end) / 2, node * 2),
                    minTreeInit((start + end) / 2 + 1, end, node * 2 + 1));
        }
    }

    static int maxTreeInit (int start, int end, int node) {
        if(start == end) return maxTree[node] = arr[start];
        else {
            return maxTree[node] = Math.max(maxTreeInit(start, (start + end) / 2, node * 2),
                    maxTreeInit((start + end) / 2 + 1, end, node * 2 + 1));
        }
    }

    static int treeMin(int node, int start, int end, int left, int right) {
        if(end < left || start > right) return Integer.MAX_VALUE;
        else if (left <= start && end <= right) return minTree[node];
        else {
            return Math.min(treeMin(node * 2, start, (start + end) / 2, left, right)
            , treeMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
        }
    }
    static int treeMax(int node, int start, int end, int left, int right) {
        if(end < left || start > right) return Integer.MIN_VALUE;
        else if (left <= start && end <= right) return maxTree[node];
        else {
            return Math.max(treeMax(node * 2, start, (start + end) / 2, left, right)
            , treeMax(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
        }
    }



    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];

        //원본배열 저장
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        treeSetting();
        minTreeInit(0, N - 1, 1);
        maxTreeInit(0, N - 1, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            System.out.println(treeMin(1, 0, N - 1, a, b) + " " + treeMax(1, 0, N - 1, a, b));
        }
    }
}