import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static long[] arr;
    static long[] tree;

    //트리 세팅
    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(N)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new long[Math.toIntExact(nodeNum)];
    }

    //트리 초기화
    static long treeInit(int node, int start, int end) {

        //리프 노드임
        if(start==end) return tree[node] = arr[start];

            //리프 노드 아님
        else {
            return tree[node] = treeInit(node * 2, start, (start + end) / 2)
                    + treeInit(node * 2 + 1, (start + end) / 2 + 1, end);
        }
    }

    //특정 노드 변경
    //n은 변경되어서 더해야 하는 값, index는 위치
    //start와 end는 현재 노드의 시작과 끝
    static void update(int index, long n, int node, int start, int end) {
        //index가 현재 구간에 속하지 않음
        if(index < start || index > end) return;

        else {
            //현재 노드의 값을 변경값 반영해서 갱신해줌
            tree[node] += n;
            //리프노드 아님 > 하위 노드들도 업데이트
            if(start != end) {
                update(index, n, node * 2, start, (start + end) / 2);
                update(index, n, node * 2 + 1, (start + end) / 2 + 1, end);
            }
        }
    }

    //구간 합 구하기
    //start와 end는 현재 노드 구간의 시작과 끝,
    //left와 right는 구하려는 구간의 시작과 끝
    static long sum(int node, int start, int end, int left, int right) {
        //현재 구간에 포함 안됨
        if(end < left || right < start) return 0;

            //노드가 가지는 구간이 구하려고 하는 합의 구간에 속한 경우
        else if (left <= start && end <= right) return tree[node];

            //현재 구간에 포함됨.
            //경우 1. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간의 일부만 속함
            //경우 2. 노드가 가지는 값의 구간이 구하려고 하는 합의 구간을 전부 포함함
            //자식노드를 사용한다
        else {
            return sum(node * 2, start, (start + end) / 2, left, right)
                    + sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());   //수의 개수
        M = Integer.parseInt(st.nextToken());   //수의 변경이 일어나는 횟수
        K = Integer.parseInt(st.nextToken());   //구간의 합을 구하는 횟수
        arr = new long[N];

        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(bf.readLine());

        treeSetting();
        treeInit(1, 0, N - 1);


        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(bf.readLine());

            //cur이 1인 경우 a번째 수를 b로 바꿈
            //cur이 2인 경우 a번째 수부터 b번째 수까지의 합을 구하여 출력함
            int cur = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()) - 1;
            long b = Long.parseLong(st.nextToken());

            if(cur == 1) {
                update(a, b - arr[a], 1, 0, N - 1);
                arr[a] = b;
            }
            else System.out.println(sum(1, 0, N - 1, a, (int) b - 1));
        }
    }
}