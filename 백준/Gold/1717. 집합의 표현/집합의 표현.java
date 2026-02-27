import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] parents;
    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return;
        if(x < y) parents[y] = x;
        else parents[x] = y;
    }
    static int find(int x) {
        if(parents[x] == x) return x;
        parents[x] = find(parents[x]);
        return parents[x];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int cur = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if(cur == 0) union(x, y);
            else {
                x = find(x);
                y = find(y);
                if(x == y) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);

    }
}
