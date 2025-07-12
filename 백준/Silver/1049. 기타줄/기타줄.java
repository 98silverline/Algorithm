import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, pMin, eMin, set, each;

    static int solution() {
        int result = 0;
        if(set > 0) {
            result += pMin < eMin * 6 ? set * pMin : eMin * (set * 6);
        }

        result += Math.min(pMin, each * eMin);
        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        set = N / 6;
        each = N % 6;

        pMin = Integer.MAX_VALUE;
        eMin = Integer.MAX_VALUE;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            pMin = Math.min(pMin, Integer.parseInt(st.nextToken()));
            eMin = Math.min(eMin, Integer.parseInt(st.nextToken()));
        }

        System.out.println(solution());
    }
}
