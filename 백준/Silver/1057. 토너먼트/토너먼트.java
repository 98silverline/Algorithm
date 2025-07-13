import java.io.*;
import java.util.*;

public class Main {
    static int N, a, b;

    static int solution() {
        int answer = 1;
        while(b - a != 1 || b % 2 != 0) {
            a = a/2 + a%2;
            b = b/2 + b%2;
            answer++;
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        if(b < a) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        System.out.println(solution());
    }
}
