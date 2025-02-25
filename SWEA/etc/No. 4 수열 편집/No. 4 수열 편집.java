package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5122_수열편집 {
	public static class LinkedList {
		Node head;
		Node tail;
		
		public LinkedList() {
			this.head = null;
		}
		
		public void init(int[] nums) {
			for(int num: nums) {
				if(head == null) {
					head = new Node(num, null);
					tail = head;
					continue;
				}
				tail.next = new Node(num, null);
				tail = tail.next;
			}
		}
		
		public void insert(int idx, int num) {
			if(idx == 0) {
				Node newHead = new Node(num, null);
				newHead.next = head;
				head = newHead;
				return;
			}
			
			Node currNode = head;
			Node nextNode = null;
			
			// idx 이전까지 이동 
			for(int i = 0; i < idx - 1; i++) {
				currNode = currNode.next;
			}
			
			nextNode = currNode.next;
			
			currNode.next = new Node(num, null);
			currNode = currNode.next;
			
			if(nextNode == null) {
				tail = currNode;
				return;
			}
			
			currNode.next = nextNode;
		}
		
		public void delete(int idx) {
			if(idx == 0) {
				head = head.next;
				return;
			}
			
			Node currNode = head;
			Node nextNode = null;
			
			// 이동 
			for(int i = 0; i < idx - 1; i++) {
				currNode = currNode.next;
			}
			
			// idx 
			nextNode = currNode.next;
			
			// idx 뒤가 null
			if(nextNode.next == null) {
				tail = currNode;
				return;
			}
			
			currNode.next = nextNode.next;
		}
		
		public void change(int idx, int num) {
			
			Node currNode = head;
			for(int i = 0; i < idx; i++) {
				currNode = currNode.next;
			}
			
			currNode.num = num;
		}
		
		public void print() {
			for(Node n = head; n != null; n = n.next) {
				System.out.print("[" + n.num + "]");
			}
			System.out.println();
		}
		
		public int find(int L) {
			Node currNode = head;
			for(int i = 0; i <= L && currNode != null; i++) {
				if(i == L) {
					return currNode.num;
				}
				currNode = currNode.next;
			}
			return -1;
		}
	}
	
	public static class Node {
		int num;
		Node next;
		
		public Node (int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			LinkedList list = new LinkedList();
			
			int[] nums = new int[N];
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			list.init(nums);
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				String cmd = st.nextToken();
				switch (cmd) {
					case "I": {
						int idx = Integer.parseInt(st.nextToken());
						int num = Integer.parseInt(st.nextToken());
						
						list.insert(idx, num);
						break;
					}
					case "D": {
						int idx = Integer.parseInt(st.nextToken());

						list.delete(idx);
						break;
					}
					case "C": {
						int idx = Integer.parseInt(st.nextToken());
						int num = Integer.parseInt(st.nextToken());

						list.change(idx, num);
						break;
					}
				}
			}
			
			int result = list.find(L);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}
