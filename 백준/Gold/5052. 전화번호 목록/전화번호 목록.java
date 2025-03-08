import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int T, N;
    static Node root;

    static class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }

    public static boolean insert(String str) {
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            Node nextNode = node.child.get(c);
            
            if (nextNode == null) {
                nextNode = new Node();
                node.child.put(c, nextNode);
            }
            
            // 1. 현재 노드가 단어의 끝인 경우, 이미 더 긴 번호가 존재한다는 것
            if (nextNode.endOfWord) {
                return false;
            }
            
            node = nextNode;
        }
        
        // 2. 현재 번호가 이전 번호의 접두어가 될 경우 (즉, 그 번호가 중간에 끝났다면)
        if (!node.child.isEmpty()) {
            return false;
        }
        
        // 현재 번호가 끝난 곳을 끝으로 표시
        node.endOfWord = true;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(bf.readLine());
            root = new Node();
            boolean flag = true;

            for (int j = 0; j < N; j++) {
                String str = bf.readLine();
                if (!insert(str)) {
                    flag = false;
                }
            }
            
            sb.append(flag ? "YES" : "NO").append("\n");
        }
        
        System.out.println(sb);
    }
}