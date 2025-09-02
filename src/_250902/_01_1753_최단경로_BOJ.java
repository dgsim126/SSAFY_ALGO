package _250902;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 시작점 node에서 다른 node들로 갈 때의 최단 경로를 구하면 된다.
 * 
 * 시작점 node로부터 다른 node로 얼마나 걸리는지 저장할 len[]를 생성한다.
 * 해당 len는 시작점을 0으로 초기화하고, 나머지는 2147483647로 초기화한다.
 * 
 * Node클래스는 내가 향하는 (해당 노드로부터 뻗어나가는 화살표) node를 저장하고 있는 connected를 포함한다.
 * 
 * 큐에 처음 위치인 (node, weight==0)를 넣고, connected노드들을 꺼내 다시 큐에 넣는다.
 * connected노드를 큐에 넣을 때 (connected, before_weight+current_weight vs len[connected])를
 * 수행한다.
 * 
 * 
 * 
 */

public class _01_1753_최단경로_BOJ {
	
	static class Node{
		List<int []> connected= new ArrayList<>(); // {도착 노드, 가중치}
	}
	
	static int V;
	static int E;
	static Node[] node;
	static int[] weight;
	static int start_node;
	
	static void solution() {
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1]));
		queue.add(new int[] {start_node, 0});
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			int from_node= temp[0]; // 출발 노드
			int distance= temp[1]; // 현재까지의 최소 가중치
			
			for(int[] a : node[from_node].connected) {
				int to_node= a[0]; // 도착 노드
				int between_distance= a[1]; // 출발 노드 <-> 도착 노드 가중치(최초 초기화된)
				
				if(distance+between_distance < weight[to_node]) {
					queue.add(new int[] {to_node, distance+between_distance});
					weight[to_node]= distance+between_distance;
				}
			}
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		V= Integer.parseInt(st.nextToken());
		E= Integer.parseInt(st.nextToken());
		start_node= Integer.parseInt(br.readLine());
		
		node= new Node[V+1];
		weight= new int[V+1];
		
		for(int i=0; i<V+1; i++) {
			weight[i]= 2147483647;
			node[i]= new Node();
		}
		
		weight[start_node]= 0;
		
		for(int i=0; i<E; i++) {
			st= new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int distance= Integer.parseInt(st.nextToken());
			
			node[from].connected.add(new int[] {to, distance});
		}
		
		solution();
		
		for(int i=1; i<V+1; i++) {
			if(weight[i]==2147483647) {
				System.out.println("INF");
			}else {
				System.out.println(weight[i]);
			}
		}
	}

}
