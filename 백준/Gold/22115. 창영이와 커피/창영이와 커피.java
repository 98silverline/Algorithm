import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] coffee, answer;

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = K; j >= coffee[i]; j--) {
                if((answer[j - coffee[i]] != 0 || j - coffee[i] == 0) && (answer[j - coffee[i]] + 1 < answer[j] || answer[j] == 0))
                    answer[j] = answer[j - coffee[i]] + 1;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());   //커피 개수
        K = Integer.parseInt(st.nextToken());   //섭취해야 하는 카페인
        
        if(K != 0) {
            coffee = new int[N];    //커피 저장
            answer = new int[K + 1];//정답 배열

            st = new StringTokenizer(bf.readLine());

            for (int i = 0; i < N; i++) {
                coffee[i] = Integer.parseInt(st.nextToken());
            }

            solution();

            System.out.println(answer[K] != 0 ? answer[K] : -1);
        } else System.out.println(0);
    }
}