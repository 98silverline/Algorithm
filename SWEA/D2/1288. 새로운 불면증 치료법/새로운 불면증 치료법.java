
import java.util.*;
import java.io.*;

public class Solution {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");
            N = Integer.parseInt(bf.readLine());
            int visited = 0;
            int cur = N;
            int num = (1 << 10) - 1;
            int cnt = 0;
            while(visited != num) {
                char arr[] = String.valueOf(cur).toCharArray();
                for (char c : arr) {
                    int n = c - '0';
                    visited = visited | (1 << n);
                }
                cnt++;
                cur += N;
            }
            sb.append(cnt * N).append("\n");
        }
        System.out.println(sb);
    }
}