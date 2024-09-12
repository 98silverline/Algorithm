import java.util.*;
import java.io.*;

public class Main {
    static int T, N, cnt, answer;
    static HashMap<Integer, Integer> hm;
    static int[] dis, next;
    static boolean[] finished;

    static void DFS(int node) {
    	dis[node] += cnt++;
    	if(dis[next[node]] == -1) DFS(next[node]);
    	else if (!finished[next[node]]) {
    		answer = answer - (dis[node] - dis[next[node]] + 1);
    	}
    	finished[node] = true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        for(int test_case = 0; test_case < T; test_case++) {
        	N = Integer.parseInt(bf.readLine());
        	hm = new HashMap<>();
        	answer = N;
        	next = new int[N + 1];
        	dis = new int[N + 1];
        	finished = new boolean[N + 1];
        	cnt = 1;
        	Arrays.fill(dis, -1);
        	StringTokenizer st = new StringTokenizer(bf.readLine());
        	for(int i = 1; i <= N; i++) next[i] = Integer.parseInt(st.nextToken());
        	for(int i = 1; i <= N; i++) if(!finished[i]) DFS(i);
        	System.out.println(answer);
        }
    }
}