import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] saw, idx;

    static void solution(int dist, int num, boolean lt, boolean rt) {
        if(lt && num - 1 >= 0 && saw[num][idx[num][0]] != saw[num - 1][idx[num -1 ][1]]) {
            int d = dist == 1 ? -1 : 1;
            solution(d, num - 1, true, false);
        }
        if(rt && num + 1 < 4 && saw[num][idx[num][1]] != saw[num + 1][idx[num + 1][0]]) {
            int d = dist == 1 ? -1 : 1;
            solution(d, num + 1, false, true);
        }
        if(dist == 1) {
            if(idx[num][0] == 0) idx[num][0] = 7;
            else idx[num][0]--;
            if(idx[num][1] == 0) idx[num][1] = 7;
            else idx[num][1]--;
        } else {
            if(idx[num][0] == 7) idx[num][0] = 0;
            else idx[num][0]++;
            if(idx[num][1] == 7) idx[num][1] = 0;
            else idx[num][1]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        saw = new int[4][8];
        idx = new int[4][2];
        for(int i = 0; i < 4; i++) {
            String str = bf.readLine();
            idx[i][0] = 6;
            idx[i][1] = 2;
            for(int j = 0; j < 8; j++) {
                saw[i][j] = str.charAt(j) - '0';
            }
        }
        int N = Integer.parseInt(bf.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1;
            int dist = Integer.parseInt(st.nextToken());
            solution(dist, num, true, true);
        }
        int answer = 0;
        int cost = 1;
        for(int i = 0; i < 4; i++) {
            int cur = idx[i][0] + 2;
            if(cur > 7) cur -= 8;
            if(saw[i][cur] == 1) answer += cost;
            cost *= 2;
        }
        System.out.println(answer);
    }
}
