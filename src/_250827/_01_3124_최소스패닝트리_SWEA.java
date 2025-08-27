package _250827;

import java.io.*;
import java.util.*;

/**
 * 크루스칼이 아닌 프림으로 접근해보자
 * 크루스칼은 간선의 길이를 기준으로 정렬해서 UNION-FIND를 응용해 사이클이 생기지 않도록 하는 방식이다.
 * 
 * 프림은 이와는 다른 방식으로 현재 노드 기준으로 연결된 모든 노드들을 우선순위 큐에 넣는다.
 * 우선순위 큐에서 뺀 후, 방문처리를 하고, 연결된 모든 노드들을 큐에 넣는다.
 * 큐에서 뺄 때, 이미 방문처리가 된 노드라면 제외한다.
 * 
 * 
 */

public class _01_3124_최소스패닝트리_SWEA {
	
	
	static class Node{
		List<int[]> connected= new ArrayList<>(); // 현재 node와 연결된 (node, distance)
	}
	
	static int V; // 정점
	static int E; // 간선
	static Node[] node;
	static int[] visited;
	static int result;
	static long cnt;
	
	static void solution() {
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		
		queue.add(new int[] {1, 0}); // node번호, distance
		
		while(!queue.isEmpty()) {
			int current[]= queue.poll();
			int current_node= current[0];
			int current_distance= current[1];
			
			if(visited[current_node]==1) { // 이미 방문했던 노드라면 확인할 필요 x
				continue;
			}
			
			result+=current_distance;
			cnt+=1;
			
			if(cnt==V) { // 조기종료
				return;
			}
			
			visited[current_node]= 1;
			
			for(int[] new_ : node[current_node].connected) {
				int new_node= new_[0];
				int new_distance= new_[1];
				
				if(visited[new_node]==1) {
					continue;
				}
				
				queue.add(new int[] {new_node, new_distance});
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			V= Integer.parseInt(st.nextToken());
			E= Integer.parseInt(st.nextToken());
			
			node= new Node[V+1];
			visited= new int[V+1];
			
			for(int i=0; i<V+1; i++) {
				node[i]= new Node();
			}
			
			result= 0;
			cnt= 0;
			
			for(int i=0; i<E; i++) {
				st= new StringTokenizer(br.readLine());
				
				int from= Integer.parseInt(st.nextToken());
				int to= Integer.parseInt(st.nextToken());
				int distance= Integer.parseInt(st.nextToken());
				
				node[from].connected.add(new int[] {to, distance});
				node[to].connected.add(new int[] {from, distance});
			}
			
			solution();
			
			System.out.println("#" + tc + " " + result);
		}
	}

}
