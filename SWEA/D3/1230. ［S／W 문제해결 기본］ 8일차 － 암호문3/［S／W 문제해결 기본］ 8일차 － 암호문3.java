import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static class LinkedList {
		Node head;
		Node tail;
		
		public LinkedList() {
			this.head = null;
			this.tail = null;
		}
		
		public void initNode(int[] s) {
			for(int num: s) {
				if(head == null) {
					head = new Node(num, null);
					tail = head;
					continue;
				}
				tail.nxt = new Node(num, null);
				tail = tail.nxt;
			}
		}
		
		
		public void insertNode(int x, int[] s) {
			if(x == 0) {
				Node newHead = new Node(s[0], head);
				Node tmp = newHead;
				for(int i = 1; i < s.length; i++) {
					tmp.nxt = new Node(s[i], tmp.nxt);
					tmp = tmp.nxt;
				}
				head = newHead;
				return;
			}
			
			
			Node currNode = head;
			Node nxtNode = null;
			
			// 이동 
			for(int i = 0; i < x - 1; i++) {
				currNode = currNode.nxt;
			}

			// 연결될 노드 
			nxtNode = currNode.nxt;
			
			for(int num : s) {				
				currNode.nxt = new Node(num, null);
				currNode = currNode.nxt;
			}
			
			currNode.nxt = nxtNode;
		}
		
		
		public void deleteNode(int x, int y) {
			if(x == 0) {
				for(int i = 0; i < y && head != null; i++) {
					head = head.nxt;
				}
				return;
			}
			
			Node currNode = head;
			Node nxtNode = null;
			
			// 이동 
			for(int i = 0; i < x; i++) {
				currNode = currNode.nxt;
			}
			
			nxtNode = currNode;
			
			for(int i = 0; i < y; i++) {
				nxtNode = nxtNode.nxt;
			}
			
			currNode.nxt = nxtNode;
			
			if(currNode.nxt == null) {
				tail = currNode;
			}
		}
		
		
		public void addNode(int[] s) {
			if(tail == null) {
				head = new Node(s[0], null);
				tail = head;
				for(int i = 1; i < s.length; i++) {
					tail.nxt = new Node(s[i], null);
					tail = tail.nxt;
				}
				return;
			}
			for(int num: s) {
				tail.nxt = new Node(num, null);
				tail = tail.nxt;
			}
		}
				
		public void print() {
			for(Node n = head; n != null; n = n.nxt) {
				System.out.print("[" + n.cihper + "]");
			}
			System.out.println();
		}
	}
	
	public static class Node {
		int cihper;
		Node nxt;
		
		public Node(int cihper, Node nxt) {
			this.cihper = cihper;
			this.nxt = nxt;
		}
		
		@Override
		public String toString() {
			return "[" + cihper + ":" + nxt + "]";
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		int T = 10;
		
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc<=T; tc++) {
			int N = Integer.parseInt(bf.readLine());
			
			LinkedList list = new LinkedList();
			
			int[] nums = new int[N];
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			list.initNode(nums);
			
//			list.print();
			
			int M = Integer.parseInt(bf.readLine());
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < M; i++) {
				String cmd = st.nextToken();
				
				switch (cmd) {
					case "I": {
						int x = Integer.parseInt(st.nextToken());
						int y = Integer.parseInt(st.nextToken());
						
						int[] s = new int[y];
						for(int j = 0; j < y; j++) {
							s[j] = Integer.parseInt(st.nextToken());
						}
						
						list.insertNode(x, s);
						break;
					}
					case "D" : {
						int x = Integer.parseInt(st.nextToken());
						int y = Integer.parseInt(st.nextToken());
						
						list.deleteNode(x, y);
						break;
					}
					case "A" : {
						int y = Integer.parseInt(st.nextToken());
						
						int[] s = new int[y];
						for(int j = 0; j < y; j++) {
							s[j] = Integer.parseInt(st.nextToken());
						}
						
						list.addNode(s);
						break;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ");
			Node curr = list.head;
			for(int i = 0 ; i < 10; i++) {
				sb.append(curr.cihper).append(" ");
				curr = curr.nxt;
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
}