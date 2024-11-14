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
        else return tree[node] = treeInit(node * 2, start, (start + end) / 2)
                + treeInit(node * 2 + 1, (start + end) / 2 + 1, end);
    }

    static void update(int node, int start, int end, int index, int n) {
        if(index < start || index > end) return;
        else if(start == end) tree[node] += n;
        else {
            tree[node] += n;
            update(node * 2, start, (start + end) / 2, index, n);
            update(node * 2 + 1, (start + end) / 2 + 1, end, index, n);
        }
    }

    static int find(int node, int start, int end, int n) {
        if(start == end) return start + 1;
        if(tree[node * 2] >= n) return find(node * 2, start, (start + end) / 2, n);
        else return find(node * 2 + 1, (start + end) / 2 + 1, end, n - tree[node * 2]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        treeSetting();
        treeInit(1, 0, N - 1);

        M = Integer.parseInt(bf.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1) {
                int b = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());
                update(1, 0, N - 1, b, c);
            } else {
                int b = Integer.parseInt(st.nextToken());
                sb.append(find(1, 0, N - 1, b)).append("\n");
            }
        }
        System.out.println(sb);
    }
}