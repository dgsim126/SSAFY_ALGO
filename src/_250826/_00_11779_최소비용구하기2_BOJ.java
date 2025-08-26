package _250826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 기본 다익스트라 구조에서 경로를 추적하려면 어떻게 해야할까?
 * 
 */

public class _00_11779_최소비용구하기2_BOJ {
	
	static int N; // 도시의 개수
	static int M; // 버스의 개수
	static Node node[];
	static int start_node;
	static int end_node;
	
	static class Node{
		List<int[]> connected= new ArrayList<>(); // end, distance
	}
	
	static void solution() {
		int weight[]= new int[N+1];
		int visited[]= new int[N+1]; // 0 방문하지 않음, 1 방문
		int parent[]= new int[N+1];
		
		for(int i=1; i<N+1; i++) {
			weight[i]= 2000000000;
			parent[i]= -1;
		}
		weight[start_node]= 0;
		
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		queue.add(new int[] {start_node, weight[start_node]});
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			int from_node= temp[0]; // 출발 노드
			int from_weight= temp[1]; // 이전까지 이동하며 누적된 값
			
			if(visited[from_node]==0) {
				for(int current[] : node[from_node].connected) {
					int to_node= current[0]; // 도착 노드
					int to_weight= weight[to_node]; // 도착 노드에 저장된 현재 최소치
					int between_weight= current[1]; // 출발 노드에서 도착 노드까지의 거리
					
					if(from_weight+between_weight < to_weight) {
						weight[to_node]= from_weight+between_weight;
						parent[to_node]= from_node;
						queue.add(new int[] {to_node, from_weight+between_weight});
					}
				}
				visited[from_node]= 1;
			}
			
		}
		
		// 결과 출력
		System.out.println(weight[end_node]);
		int[] path = new int[N + 1];
		int idx = 0;
		for (int v = end_node; v != -1; v = parent[v]) {
		    path[idx++] = v;
		}

		// 3) 경로 길이(도시 수)
		System.out.println(idx);

		// 4) 경로 출력 (공백 구분, 마지막엔 공백 없이 줄바꿈)
		for (int i = idx - 1; i >= 0; i--) {
		    System.out.print(path[i]);
		    if (i > 0) System.out.print(" ");
		}
		System.out.println();
		
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		M= Integer.parseInt(br.readLine());
		
		node= new Node[N+1];
		for(int i=1; i<N+1; i++) {
			node[i]= new Node();
		}
		
		
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int start= Integer.parseInt(st.nextToken());
			int end= Integer.parseInt(st.nextToken());
			int distance= Integer.parseInt(st.nextToken());
			
			node[start].connected.add(new int[] {end, distance});
		}
		
		st= new StringTokenizer(br.readLine());
		start_node= Integer.parseInt(st.nextToken());
		end_node= Integer.parseInt(st.nextToken());
		
		solution();
		
	}

}







