import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {	
	static int N;
	static boolean[] visited;
	static ArrayList<Integer>[] list;
	
	public static class Person implements Comparable<Person> {
		int idx, cost;
		
		public Person (int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public int compareTo (Person o) {
			return Integer.compare(cost, o.cost);
		}

		@Override
		public String toString() {
			return "Person [idx=" + idx + ", cost=" + cost + "]";
		}		
	}
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
				
		st = new StringTokenizer(bf.readLine());
		
		ArrayList<Person> people = new ArrayList();
		list = new ArrayList[N+1];
		visited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) {
			int cost = Integer.parseInt(st.nextToken());
			people.add(new Person(i, cost));
			
			list[i] = new ArrayList<>();
		}
		
		Collections.sort(people);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
				
		int answer = 0;
		for(int i = 0; i < N; i++) {
			Person p = people.get(i);
			if(visited[p.idx]) continue;
			if(answer > K) {
				System.out.println("Oh no");
				return;
			}
			bfs(p.idx);
			answer += p.cost;
		}
		
		if(answer > K) {
			System.out.println("Oh no");
			return;
		}

		System.out.println(answer);
	}
	
	public static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for(int nxt: list[v]) {
				if(visited[nxt]) continue;
				q.offer(nxt);
				visited[nxt] = true;
			}
		}
	}
}
