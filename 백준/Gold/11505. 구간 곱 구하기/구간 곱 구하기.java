import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] arr, tree;
    static long MOD = 1000000007;

    static void treeSetting (){
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new long[Math.toIntExact(nodeNum)];
    }

    static long treeInit(int node, int start, int end) {
        if(start == end) {
            return tree[node] = arr[start] % MOD;
        } else {
            return tree[node] = treeInit(node * 2, start, (start + end) / 2)
                    * treeInit(node * 2 + 1, (start + end) / 2 + 1, end) % MOD;
        }
    }

    static long update(int node, int start, int end, int index, long n) {
        if(index < start || index > end) return tree[node];
        else if (start == end) return tree[node] = arr[start];
        else {
            return tree[node] = update(node * 2, start, (start + end) / 2, index, n)
                    * update(node * 2 + 1, (start + end) / 2 + 1, end, index, n) % MOD;
        }
    }

    static long mul(int node, int start, int end, int left, int right) {
        if(end < left || right < start) return 1;
        else if (left <= start && end <= right) return tree[node];
        else {
            return mul(node * 2, start, (start + end) / 2, left, right)
                    * mul(node * 2 + 1, (start + end) / 2 + 1, end, left, right) % MOD;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());   //수의 개수
        M = Integer.parseInt(st.nextToken());   //변경 일어나는 횟수
        K = Integer.parseInt(st.nextToken());   //구간 곱 구하는 횟수
        arr = new long[N];

        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(bf.readLine());

        treeSetting();
        treeInit(1, 0, N - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if(a == 1) {
                arr[b - 1] = c;
                update(1, 0, N - 1, b - 1, c);
            } else {
                System.out.println(mul(1, 0, N - 1, b - 1, (int) (c - 1)));
            }
        }
    }
}
