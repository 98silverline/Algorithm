import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] tree, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new int[Math.toIntExact(nodeNum)];
    }

    static int treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];
        else return tree[node] = Math.min(treeInit(node * 2, start, (start + end) / 2),
                treeInit(node * 2 + 1, (start + end) / 2 + 1, end));
    }

    static int treeMin(int node, int start, int end, int left, int right) {
        if(right < start || left > end) return Integer.MAX_VALUE;
        else if (left <= start && end <= right) return tree[node];
        else {
            return Math.min(treeMin(node * 2, start, (start + end) / 2, left, right),
                    treeMin(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());
        treeSetting();
        treeInit(1, 0, N - 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(treeMin(1, 0, N - 1, a - 1, b - 1));
        }
    }
}