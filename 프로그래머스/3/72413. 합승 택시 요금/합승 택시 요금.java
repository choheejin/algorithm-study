/**
완전탐색과 다익스트라 응용문제로 예상된다.

0. 양방향 연결리스트 만든다.
1. 스타트 포인트에서 다익스트라 돌린다.
    => 환승하지 않고, A/B 지점으로 가는 최단 경로 구할 수 있음
2. 스타트 포인트에서 다익스트라 돌린다.
    => 한 지점 도착할 때마다 해당 지점을 start로 하는 다익스트라 추가적으로 돌린다.
    => 환승을 하면서, 각각의 지점으로 가는 최단 경로를 구할 수 있음
**/

import java.util.*;

class Solution {
    class Node {
        int to, weight;
        Node next;
        
        public Node(int to, int weight, Node next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }
        
        @Override
        public String toString() {
            return "{"+to + "|" + weight + "|" + next + "}";
        }
    }
    
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        
        Node[] adjList = initList(n, fares);
        
        // System.out.println(Arrays.toString(adjList));
        // System.out.println(Arrays.toString(dijstra(adjList, n, s)));
        
        // step1: 합승 안하고 각자 탔을 때의 비용
        
        int[] dist = dijstra(adjList, n, s);
        int eachFare = dist[a] + dist[b];
        int otherFare = findWay(adjList, dist, n, a, b);
        // System.out.println(eachFare);
        // System.out.println(Arrays.toString(dist));
        
        // System.out.println(dijstraTrans(adjList, n, s, a, b));
        // System.out.println(findWay(adjList, dist, n, a, b));
        return Math.min(eachFare, otherFare);
    }
    
    public Node[] initList(int n, int[][] fares) {
        Node[] adjList = new Node[n+1];
        
        int edges = fares.length;
        
        for(int i = 0; i < edges; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int w = fares[i][2];
            
            adjList[from] = new Node(to, w, adjList[from]);
            adjList[to] = new Node(from, w, adjList[to]);
        }
        
        return adjList;
    }
    
    public static int[] dijstra(Node[] adjList, int N, int start) { 
         PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> Integer.compare(x[1], y[1]));
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        q.offer(new int[] {start, 0});
        dist[start] = 0;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int from = curr[0];
                        
            if(visited[from]) continue;
            visited[from] = true;
            
            for(Node n = adjList[from]; n != null; n=n.next) {
                if(visited[n.to]) continue;
                                
                if(dist[n.to] > curr[1] + n.weight) {
                    dist[n.to] = curr[1] + n.weight;
                    q.offer(new int[] {n.to, dist[n.to]});
                }
            }
        }
        
        return dist;
    }
        
    public static int findWay(Node[] adjList, int[] dist, int n, int a, int b) {
        int result = Integer.MAX_VALUE;
        
        for(int t = 0 ; t < n; t++){
            int minV = Integer.MAX_VALUE;
            int idx = 0;

            for(int i = 1; i<= n; i++) {
                if(dist[i] != 0 && minV > dist[i]) {
                    idx = i;
                    minV = dist[i];
                }
            }      
            
            dist[idx] = 0;
            
            int[] distMiddle = dijstra(adjList, n, idx);
            // System.out.println(Arrays.toString(distMiddle));  
            // System.out.println(distMiddle[a] + distMiddle[b]);
            
            if(result > minV + distMiddle[a] + distMiddle[b]) {
                result = minV + distMiddle[a] + distMiddle[b];
            }
        }
        
        return result;
    }
    
}