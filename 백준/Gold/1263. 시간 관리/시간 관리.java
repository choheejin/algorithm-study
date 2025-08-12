import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 정렬 문제, Si 기준 정렬 후 조건 검증
public class Main {
	
	public static class Job implements Comparable<Job> {
		int t, s;
		
		public Job(int t, int s) {
			this.t = t;
			this.s = s;
		}
		
		@Override
		public int compareTo(Job o) {
			return Integer.compare(o.s, s);
		}
		
		@Override
		public String toString() {
			return "[" + t + "," + s + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		Job[] jobs = new Job[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int t = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			jobs[i] = new Job(t, s);
		}
		
		Arrays.sort(jobs);
		
		int std = jobs[0].s - jobs[0].t;
		
		for(int i = 1 ; i < N; i++) {
			if(jobs[i].s < std) {
				std = jobs[i].s;
			}
			
			std -= jobs[i].t;
		}
		
		if(std < -1) {
			System.out.println(-1);
		} else {
			System.out.println(std);
		}
//		System.out.println(Arrays.toString(jobs));
//		System.out.println(std);
	}
}
