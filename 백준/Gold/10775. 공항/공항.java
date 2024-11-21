import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int G, P, answer;
    static int[] parent;

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (x < y) parent[y] = x;
        else parent[x] = y;
        return true;
    }
    static int find(int x) {
        if(x != parent[x]) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(bf.readLine());    //공항 게이트 수
        P = Integer.parseInt(bf.readLine());    //비행기 수
        parent = new int[G + 1];
        answer = 0;

        for (int i = 1; i <= G; i++) parent[i] = i;
        for (int i = 0; i < P; i++) {
            int n = Integer.parseInt(bf.readLine());
            if (find(n) == 0) break;
            union(find(n), find(n) - 1);
            answer++;
        }
        System.out.println(answer);
    }
}