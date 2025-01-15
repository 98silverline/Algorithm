import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, answer;
    static int[][] arr;
    static int[] ch;

    static void DFS(int num, int sum) {
        if(num > 10) {
            if(answer < sum) answer = sum;
        } else {
            for (int i = 0; i < 11; i++) {
                if(arr[num][i] > 0 && ch[i] == 0) {
                    ch[i] = 1;
                    DFS(num + 1, sum + arr[num][i]);
                    ch[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            arr = new int[11][11];
            ch = new int[11];
            answer = 0;

            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            DFS(0, 0);
            System.out.println(answer);
        }
    }
}