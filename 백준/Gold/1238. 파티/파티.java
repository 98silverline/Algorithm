import java.util.*;
import java.io.*;

public class Main {
	static int N, M, X, answer;
	static int[] node, nodeX;
	static PriorityQueue<Node> pq;
	static ArrayList<ArrayList<Node>> arr;
	
	static class Node {
		int node;
		int cost;
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	
	public static void solution(int start) {
		pq.add(new Node(start, 0));
		node = new int[N + 1];
		for(int i = 0; i <= N; i++) node[i] = Integer.MAX_VALUE;
		node[start] = 0;
		
		while(!pq.isEmpty()) {
			Node n = pq.poll();
			if(node[n.node] < n.cost) continue;
			for(int i = 0; i < arr.get(n.node).size(); i++) {
				Node ntx = arr.get(n.node).get(i);
				if(node[ntx.node] > n.cost + ntx.cost) {
					node[ntx.node] = n.cost + ntx.cost;
					pq.offer(new Node(ntx.node, n.cost + ntx.cost));
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		answer = Integer.MIN_VALUE;
		arr = new ArrayList<>();
		pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
		for(int i = 0; i <= N; i++) arr.add(new ArrayList<>());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			arr.get(a).add(new Node(b, c));
		}
		
		solution(X);
		nodeX = node.clone();
		for(int i = 1; i <= N; i++) {
			solution(i);
			answer = Math.max(node[X] + nodeX[i], answer);
		}
		System.out.println(answer);
	}
}