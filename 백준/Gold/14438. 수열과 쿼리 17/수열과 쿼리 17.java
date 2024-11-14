import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long[] tree, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new long[Math.toIntExact(nodeNum)];
    }

    static long treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];
        else return tree[node] = Math.min(treeInit(node * 2, start, (start + end) / 2),
                treeInit(node * 2 + 1, (start + end) / 2 + 1, end));
    }

    static long update(int node, int start, int end, int index, long n) {
        if(index < start || index > end) return tree[node];
        else {
            if(start == end) return tree[node] = n;
            else {
                return tree[node] = Math.min(update(node * 2, start, (start + end) / 2, index, n),
                update(node * 2 + 1, (start + end) / 2 + 1, end, index, n));
            }
        }
    }

    static long find(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return Long.MAX_VALUE;
        else if (start >= left && end <= right) return tree[node];
        else {
            return Math.min(find(node * 2, start, (start + end) / 2, left, right)
                    , find(node * 2 + 1, (start + end) / 2 + 1, end, left, right));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        arr = new long[N + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) arr[i] = Long.parseLong(st.nextToken());

        treeSetting();
        treeInit(1, 1, N);

        M = Integer.parseInt(bf.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());
            if(a == 1) {
                update(1, 1, N, b, c);
            } else {
                sb.append(find(1, 1, N, b, (int) c)).append("\n");
            }
        }
        System.out.println(sb);
    }
}