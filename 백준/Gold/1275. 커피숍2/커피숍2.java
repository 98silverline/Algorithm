import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static long[] tree, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new long[Math.toIntExact(nodeNum)];
    }

    static long treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];
        else {
            return tree[node] = treeInit(node * 2, start, (start + end) / 2)
                    + treeInit(node * 2 + 1, (start + end) / 2 + 1, end);
        }
    }

    static void update(int node, int start, int end, int index, long n) {
        if(index < start || index > end) return;
        else {
            tree[node] += n;
            if(start != end) {
                update(node * 2, start, (start + end) / 2, index, n);
                update(node * 2 + 1, (start + end) / 2 + 1, end, index, n);
            }
        }
    }

    static long sum(int node, int start, int end, int left, int right) {
        if(left > end || right < start) return 0;
        else if (start >= left && end <= right) return tree[node];
        else {
            return sum(node * 2, start, (start + end) / 2, left, right)
                    + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new long[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(st.nextToken());

        treeSetting();
        treeInit(1, 0, N - 1);


        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(x > y) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            int a = Integer.parseInt(st.nextToken()) - 1;
            long b = Long.parseLong(st.nextToken());
            long n = b - arr[a];
            arr[a] = b;
            System.out.println(sum(1, 0, N - 1, x - 1, y - 1));
            update(1, 0, N - 1, a, n);
        }
    }
}