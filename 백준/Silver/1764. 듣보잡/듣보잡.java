import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static HashSet<String> arr;
    static ArrayList<String> arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        arr = new HashSet<>();
        arr2 = new ArrayList<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            arr.add(bf.readLine());
        }
        for (int i = 0; i < M; i++) {
            String str = bf.readLine();
            if (arr.contains(str)) {
                arr2.add(str);
            }
        }
        Collections.sort(arr2);
        StringBuilder sb = new StringBuilder();
        sb.append(arr2.size()).append("\n");
        for (String s : arr2) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}
