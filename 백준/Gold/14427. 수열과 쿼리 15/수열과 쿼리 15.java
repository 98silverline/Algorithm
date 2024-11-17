import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] tree, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N) / Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new int[Math.toIntExact(nodeNum)];
    }

    static int treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];
        else return tree[node] = Math.min(treeInit(node * 2, start, (start + end) / 2),
                treeInit(node * 2 + 1, (start + end) / 2 + 1, end));
    }

    static int update(int node, int start, int end, int index) {
        if(index < start || index > end) return tree[node];
        else {
            if(start == end) return tree[node] = arr[index];
            else {
                return tree[node] = Math.min(update(node * 2, start, (start + end) / 2, index)
                , update(node * 2 + 1, (start + end) / 2 + 1, end, index));
            }
        }
    }

    static int find(int node, int start, int end) {
        if(start == end) return start;
        if(tree[node] == tree[node * 2]) return find(node * 2, start, (start + end) / 2);
        else return find(node * 2 + 1, (start + end) / 2 + 1, end);
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
            int order = Integer.parseInt(st.nextToken());
            if(order == 1) {
                int index = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken());
                arr[index] = to;
                update(1, 0, N - 1, index);
            } else sb.append(find(1, 0, N - 1) + 1).append("\n");
        }
        System.out.println(sb);
    }
}