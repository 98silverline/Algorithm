import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] tree, arr;

    static void treeSetting() {
        double treeHeight = Math.ceil(Math.log(1000000)/Math.log(2)) + 1;
        long nodeCnt = Math.round(Math.pow(2, treeHeight));
        tree = new int[Math.toIntExact(nodeCnt)];
    }

    static int treeUpdate(int node, int start, int end, int index, int num) {
        if(index < start || index > end) return tree[node];
        else if (start == end) {
            return tree[node] = tree[node] + num;
        } else {
            return tree[node] = treeUpdate(node * 2, start, (start + end) / 2, index, num)
                    + treeUpdate(node * 2 + 1, (start + end) / 2 + 1, end, index, num);
        }
    }

    static int findCandy(int node, int start, int end, int find) {
        tree[node]--;
        if(start == end) return start;
        else if(tree[node * 2] >= find) return findCandy(node * 2, start, (start + end) / 2, find);
        else return findCandy(node * 2 + 1, (start + end) / 2 + 1, end, find - tree[node * 2]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        treeSetting();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int A = Integer.parseInt(st.nextToken());

            //사탕 상자에서 사탕을 하나 꺼내는 경우
            if(A == 1) {
                int B = Integer.parseInt(st.nextToken());
                System.out.println(findCandy(1, 0, 999999, B) + 1);
            }
            //사탕 상자에 사탕을 넣는 경우
            else {
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());
                treeUpdate(1, 0, 999999, B - 1, C);
            }
        }
    }
}