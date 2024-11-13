import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] tree, lazy, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new long[Math.toIntExact(nodeNum)];
        lazy = new long[Math.toIntExact(nodeNum)];
    }

    static long treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = arr[start];
        else {
            return tree[node] = treeInit(node * 2, start, (start + end) / 2)
                    + treeInit(node * 2 + 1, (start + end) / 2 + 1, end);
        }
    }

    static void propagate(int node, int start, int end) {
        if(lazy[node] != 0) {
            //리프노드 아닐 경우
            if(start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            tree[node] += lazy[node] * (end - start + 1);
            lazy[node] = 0;
        }
    }

    //업데이트
    static void update(int node, int start, int end, int left, int right, long num) {
        propagate(node, start, end);
        //범위 아님 > 리턴
        if(start > right || end < left) return;
        //현재 범위가 변경하려는 범위 이내
        if(left <= start && end <= right) {
            lazy[node] = num;
            propagate(node, start, end); //lazy 반영
            return;
        }
        //일부만 범위에 포함된 경우
        update(node * 2, start, (start + end) / 2, left, right, num);
        update(node * 2 + 1, (start + end) / 2 + 1, end, left, right, num);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static long sum(int node, int start, int end, int left, int right) {
        //현재 범위 업데이트 할 거 있으면 업데이트
        propagate(node, start, end);
        if( end < left || start > right) return 0; //현재 범위는 구하는 범위가 아님

        //현재 범위가 구하는 범위에 포함됨
        if( left <= start && right >= end) return tree[node];

        //일부만 범위에 포함된 경우
        return sum(node * 2, start, (start + end) / 2, left, right)
                + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(bf.readLine());

        treeSetting();
        treeInit(1, 0, N - 1);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());
            if(A == 1) {    //A가 1인 경우 B부터 C까지 D를 더함
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                long D = Long.parseLong(st.nextToken());
                update(1, 0, N - 1, B - 1, C - 1, D);
            } else {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                System.out.println(sum(1, 0, N - 1, B - 1, C - 1));
            }
        }
    }
}