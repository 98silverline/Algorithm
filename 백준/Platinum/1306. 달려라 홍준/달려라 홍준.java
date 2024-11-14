import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr, tree;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new int[Math.toIntExact(nodeNum)];
    }

    static int treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];
        else {
            return tree[node] = Math.max(treeInit(node * 2, start, (start + end) / 2),
                    treeInit(node * 2 + 1, (start + end) / 2 + 1, end));
        }
    }

    static int find(int node, int start, int end, int left, int right) {
        if(right < start || left > end) return 0;
        else {
            if(left <= start && end <= right) return tree[node];
            else {
                return Math.max(find(node * 2, start, (start + end) / 2, left, right),
                        find(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        treeSetting();
        treeInit(1, 1, N);

        for (int i = M; i <= N - M + 1; i++) {
            sb.append(find(1, 1, N, i - (M - 1), i + (M - 1))).append(" ");
        }
        System.out.println(sb);
    }
}