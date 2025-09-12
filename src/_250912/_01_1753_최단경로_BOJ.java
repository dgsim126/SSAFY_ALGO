package _250912;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작점이 주어지기에 다익스트라로 문제를 풀면 된다.
 * 
 * 1. 현재 내 노드와 연결된 노드들을 큐에 넣는다. {node_index, current_weight}
 * 2. 큐에서 하나를 꺼내 방문처리를 하고, 연결된 노드들의 가중치+current_weight vs weight[index]를 확인한다.
 * 3. 그 값으로 갱신하면 된다.
 * 
 *
 */

public class _01_1753_최단경로_BOJ {
	
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int K; // 출발 노드 인덱스
	
	static Node node[]; // 노드들의 인덱스를 나타내기 위함
	static int weight[]; // 각 노드별 거리 가중치 결과
	
	static class Node{
		List<int[]> connected= new ArrayList<>(); // {현재 node와 연결된 node의 인덱스, 두 node간 가중치}
	}
	
	static void solution() {
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1])); // {도착 노드, 현재까지의 가중치}
		queue.add(new int[] {K, 0});
		
		while(!queue.isEmpty()) {
			int current[]= queue.poll();
			int from= current[0];
			int current_weight= current[1];
			
			if(current_weight>weight[from]) {
				continue;
			}
			
			// 2. 연결된 값들 비교하여 다시 큐에 넣기
			for(int temp[]: node[from].connected) {
				int to= temp[0];
				int distance_between= temp[1];
				

				if(distance_between+current_weight < weight[to]) {
					queue.add(new int[] {to, distance_between+current_weight});
					weight[to]= distance_between+current_weight;
						
				}
			}
		}
	}
	
	static void showResult() {
		for(int i=1; i<weight.length; i++) {
			if(weight[i]==1000000000) {
				System.out.println("INF");
			}else {
				System.out.println(weight[i]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		V= Integer.parseInt(st.nextToken());
		E= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(br.readLine());
		
		
		weight= new int[V+1];
		node= new Node[V+1];
		for(int i=0; i<V+1; i++) {
			node[i]= new Node();
			weight[i]= 1000000000;
		}
		
		weight[K]= 0;
		
		for(int i=0; i<E; i++) {
			st= new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int distance= Integer.parseInt(st.nextToken());
			
			node[from].connected.add(new int[] {to, distance});
			// node[to].connected.add(new int[] {from, distance});
		}
		
		solution();
		showResult();
	}

}
