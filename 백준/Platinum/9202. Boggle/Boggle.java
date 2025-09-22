import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
	static HashSet<String> words;
	static HashSet<String> finds;
	static int[] dx = {-1, -1, 1, 1, 0, 0, -1, 1}, dy = {-1, 1, -1, 1, 1, -1, 0, 0};
	static int[] scoreBoard = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	static StringBuilder sb = new StringBuilder();
    static TrieNode root = new TrieNode();

	
    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    static void insert(String s) {
        TrieNode cur = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'A';
            if (cur.child[idx] == null) cur.child[idx] = new TrieNode();
            cur = cur.child[idx];
        }
        cur.end = true;
    }

	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		words = new HashSet<>();
		
		for(int i = 0; i < N; i++) {
            String w = bf.readLine().trim();
			words.add(w);
			insert(w); 
		}
		
		bf.readLine();
		
		int B = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < B; i++) {
			char[][] board = new char[4][];
			
			for(int j = 0; j < 4; j++) {
				board[j] = bf.readLine().toCharArray();
			}
			
			check(board);
			
			if(i == B - 1) {
				System.out.println(sb);
				return;
			}
			bf.readLine();
		}
	}
	
	public static void check(char[][] board) {
		int maxLen = 0;
		String maxWord = "";
		int score = 0;
		finds = new HashSet<>();

		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				isWordExisted(i, j, root, board, new boolean[4][4], new StringBuilder());
			}
		}
		
		for(String find: finds) {
			score += scoreBoard[find.length()];
			
			if(maxLen == find.length() && maxWord.compareTo(find) > 0 ) {
				maxWord = find;
			}
			if (maxLen < find.length()) {
				maxLen = find.length();
				maxWord = find;
			}
		}
		
		sb.append(score).append(" ")
			.append(maxWord).append(" ")
			.append(finds.size()).append("\n");
	}
	
	static void isWordExisted (int x, int y, TrieNode node, char[][] board, boolean[][] visited, StringBuilder sb) {
	    if (x < 0 || y < 0 || x >= 4 || y >= 4) return;
	    if (visited[x][y]) return;

	    int idx = board[x][y] - 'A';
	    if (node.child[idx] == null) return; // prefix 아님, 가지치기

	    node = node.child[idx];
	    sb.append(board[x][y]);

	    if (node.end) finds.add(sb.toString());

	    visited[x][y] = true;
	    for (int d = 0; d < 8; d++) {
	    	isWordExisted(x + dx[d], y + dy[d], node, board, visited, sb);
	    }
	    visited[x][y] = false;

	    sb.setLength(sb.length() - 1);
	}

	
	// 첫 내가 시도 했던 풀이...
//	public static boolean isPrefix(String prefix) {
//	    for (String word : words) {
//	        if (word.startsWith(prefix)) {
//	            return true;
//	        }
//	    }
//	    return false;
//	}
//	
//	public static void isWordExisted (int x, int y, String curr, char[][] board, boolean[][] isVisited) {
//		if(isVisited[x][y]) return;
//		
//		if(words.contains(curr + board[x][y])) {
//			finds.add(curr + board[x][y]);
//		}
//		
//		if(!isPrefix(curr + board[x][y])) return;
//		
//		
//		isVisited[x][y] = true;
//
//		for (int d = 0; d < 8; d++) {
//			int nx = x + dx[d], ny = y + dy[d];
//			
//			if (nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
//			
//			isWordExisted(nx, ny, curr + board[x][y], board, isVisited);
//			
//		}
// 		
//		isVisited[x][y] = false;
//	}
}
