import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, answer;
    static int[] parent;

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(parent[x] == parent[y]) return;
        if(x < y) parent[y] = x;
        else parent[x] = y;
    }

    static int find(int x) {
        if(parent[x] != x) return find(parent[x]);
        else return parent[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        M = Integer.parseInt(bf.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }
        for (int i = 2; i <= N; i++) {
            if(find(i) == 1) answer++;
        }
        System.out.println(answer);
    }
}
