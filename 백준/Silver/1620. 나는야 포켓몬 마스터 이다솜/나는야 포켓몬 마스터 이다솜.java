import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Map<Integer, String> num;
    static Map<String, Integer> alpha;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        alpha = new HashMap<>();
        num = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String str = bf.readLine();
            alpha.put(str, i);
            num.put(i, str);
        }

        for (int i = 0; i < M; i++) {
            String str = bf.readLine();

            //알파벳으로 들어온 경우 - alpha에서 찾기
            if(Character.isAlphabetic(str.charAt(0))) {
                System.out.println(alpha.get(str));
            }
            //숫자로 들어온 경우 - num에서 찾기
            else {
                System.out.println(num.get(Integer.parseInt(str)));
            }
        }
    }
}
