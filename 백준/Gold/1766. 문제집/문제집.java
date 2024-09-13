import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] ch;
    static StringBuilder sb;
    static PriorityQueue<Integer> que;
    static ArrayList<ArrayList<Integer>> graph;
    static void solution() {
    	while(!que.isEmpty()) {
    		int cur = que.poll();
    		sb.append(cur + " ");
    		for(int n : graph.get(cur)) {
    			ch[n]--;
    			if(ch[n] == 0) que.offer(n);
    		}
    	}
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ch = new int[N + 1];
        que = new PriorityQueue<>();
        graph = new ArrayList<>();
        sb = new StringBuilder();
        
        for(int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(bf.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	graph.get(x).add(y);
        	ch[y]++;
        }
        for(int i = 1; i <= N; i++) if(ch[i] == 0) que.offer(i);
        solution();
        System.out.println(sb.toString());
    }
}