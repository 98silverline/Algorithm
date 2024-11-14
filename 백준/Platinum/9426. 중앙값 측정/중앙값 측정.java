import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, mid;
    static long answer;
    static int[] arr, init, tree;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(65536)/Math.log(2)) + 1;
        long nodeNum = Math.round(Math.pow(2, treeHeight));
        tree = new int[Math.toIntExact(nodeNum)];
    }

    static int treeInit(int node, int start, int end) {
        if(start == end) return tree[node] = init[start];
        else {
            return tree[node] = treeInit(node * 2, start, (start + end) / 2)
                    + treeInit(node * 2 + 1, (start + end) / 2 + 1, end);
        }
    }

    static void update(int node, int start, int end, int add, int delete) {
//        System.out.println("add: " + add + ", delete: " + delete + ", start: " + start + ", end: " + end);
        //둘 다 범위 바깥이면 그냥 리턴
        if((add < start || end < add) && (delete < start || end < delete)) {
//            System.out.println("둘다 범위 아님! 범위: " + start + ", " + end);
            return;
        }
        //둘 다 범위 이내
        else if (add >= start && add <= end && delete >= start && delete <= end) {
//            System.out.println("둘다 범위 이내! 범위: " + start + ", " + end);
            update(node * 2, start, (start + end) / 2, add, delete);
            update(node * 2 + 1, (start + end) / 2 + 1, end, add, delete);
        }
        //add는 범위 바깥, delete는 범위 이내
        else if(add < start || end < add) {
//            System.out.println("delete 범위 이내! 범위: " + start + ", " + end);
            tree[node]--;
            if(start != end) {
                update(node * 2, start, (start + end) / 2, add, delete);
                update(node * 2 + 1, (start + end) / 2 + 1, end, add, delete);
            }
        }
        //detete는 범위 바깥, add는 범위 이내
        else {
//            System.out.println("add 범위 이내! 범위: " + start + ", " + end);
            tree[node]++;
            if (start != end) {
                update(node * 2, start, (start + end) / 2, add, delete);
                update(node * 2 + 1, (start + end) / 2 + 1, end, add, delete);
            }
        }
    }

    static int find(int node, int start, int end, int n) {
        if(start == end) return start;
        if(tree[node * 2] >= n) return find(node * 2, start, (start + end) / 2, n);
        else return find(node * 2 + 1, (start + end) / 2 + 1, end, n - tree[node * 2]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        init = new int[65536];
        arr = new int[N];
        answer = 0;
        mid = (K + 1) / 2;

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(bf.readLine());
            init[num]++;
            arr[i] = num;
        }
        treeSetting();
        treeInit(1, 0, 65535);
        for (int i = K; i < N; i++) {
            answer += find(1, 0, 65535, mid);
            arr[i] = Integer.parseInt(bf.readLine());
            if(arr[i] != arr[i - K]) update(1, 0, 65535, arr[i], arr[i - K]);
        }
        answer += find(1, 0, 65535, mid);
        System.out.println(answer);
    }
}