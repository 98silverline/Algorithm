import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] list;
    static List<Character> vowel = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
    static StringBuilder sb;

    static void DFS(int cnt, int idx, int flag, char[] password) {
        if(cnt == L) {
            if(flag > 0 && L - flag >= 2) {
                for(char c : password) {
                    sb.append(c);
                }
                sb.append("\n");
            }
        } else {
            for(int i = idx; i < C; i++) {
                password[cnt] = list[i];
                if (vowel.contains(list[i])) {
                    DFS(cnt + 1, i + 1, flag + 1, password);
                } else {
                    DFS(cnt + 1, i + 1, flag, password);
                }
            }
        }
    }

    static void solution() {
        DFS(0, 0, 0, new char[L]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new char[C];
        sb = new StringBuilder();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < C; i++) list[i] = st.nextToken().charAt(0);

        Arrays.sort(list);

        solution();
        System.out.println(sb);
    }
}
