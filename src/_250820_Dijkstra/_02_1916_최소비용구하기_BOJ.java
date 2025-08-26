package _250820_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Node 클래스를 생성한다.
 * 이때, 해당 Node클래스는 연결된 Node들의 인덱스를 가진 ArrayList를 가지고 있다.
 * 
 * A노드를 시작지점으로 설정하고 visited=1로 변경한다.
 * 그리고 해당 값을 우선순위큐에 넣는다.(우선순위큐의 우선순위는 distance, 즉 거리를 기준으로 한다.)
 * 
 * while문을 돌며 우선순위큐에서 새로운 값을 빼낸다.
 * 해당 Node(to)에서 연결된 Node(from)들을 방문한 후, (to 가중치 + from distance) vs (from 가중치)
 * 를 진행하여 더 작은 값으로 from의 가중치를 변경한다.
 * 
 */

public class _02_1916_최소비용구하기_BOJ {
	
	static class Node{
		List<int[]> connected= new ArrayList<>(); // {연결된 노드, 길이}
	}
	
	static int N; // 도시(Node)의 수
	static int M; // 버스(vertex)의 수
	
	static Node[] node; 
	static int[] weight; // 100,000으로 초기화
	static int[] visited; // 0==방문하지 않음,  1==방문
	
	static int start_node;
	static int end_node;
	
	static void solution() {
		
		// 1. 처음 시작 시점 초기화
		weight[start_node]= 0;
		
		// 2. 우선순위큐 생성 (길이가 짧은 순서로 우선순위)
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		queue.offer(new int[] {start_node, weight[start_node]});
		
		// 3. 우선순위 큐에서 노드
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			int to_node= temp[0]; // 출발 노드 번호
			int to_node_weight= temp[1]; // 출발 노드의 현재까지의 가중치
			
			if(visited[to_node]==0) {
				for(int n[] : node[to_node].connected) {
					int from_node= n[0]; // 도착 노드의 노드 번호
					int distance_between_toNode_to_fromNode= n[1]; // 출발 노드와 도착 노드의 거리
					
					if(distance_between_toNode_to_fromNode+to_node_weight < weight[from_node]) {
						weight[from_node]= distance_between_toNode_to_fromNode+to_node_weight;
						queue.offer(new int[] {from_node, weight[from_node]});
					}
				}
				
				visited[to_node]= 1;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		M= Integer.parseInt(br.readLine());
		
		node= new Node[N+1];
		weight= new int[N+1];
		visited= new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			node[i]= new Node();
			weight[i]= 1000000000;
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
		
		System.out.println(weight[end_node]);
	}

}
