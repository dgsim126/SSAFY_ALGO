package _250914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class _09_1753_dijkstra_BOJ {
	
	static int V;
	static int E;
	static int start_node;
	
	static Node[] node;
	static int[] weight;
	
	static class Node{
		List<int[]> connected= new ArrayList<>(); // {연결된 노드 인덱스, 가중치}
	}

	
	static void solution() {
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b)->Integer.compare(a[1], b[1])); 
		weight[start_node]= 0;
		
		queue.add(new int[] {start_node, 0});
		
		while(!queue.isEmpty()) {
			int current[]= queue.poll();
			int from_node= current[0];
			int cum_length= current[1];
			
			if(weight[from_node]<cum_length) {
				continue;
			}
			
			
			
			for(int temp[] : node[from_node].connected) {
				int to_node= temp[0];
				int add_length= temp[1];
				
				if(weight[to_node]<cum_length+add_length) {
					continue;
				}else {
					weight[to_node]= cum_length+add_length;
					queue.add(new int[] {to_node, cum_length+add_length});
				}
			}
		}
		
		
		
		// 결과 출력
		for(int i=1; i<weight.length; i++) {
			if(weight[i]==2000000000) {
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
		
		weight= new int[V+1];
		node= new Node[V+1];
		for(int i=1; i<V+1; i++) {
			node[i]= new Node();
			weight[i]= 2000000000;
		}
		
		start_node= Integer.parseInt(br.readLine());
		
		for(int i=0; i<E; i++) {
			st= new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int length= Integer.parseInt(st.nextToken());
			node[from].connected.add(new int[]{to, length});
		}
		
		solution();

	}

}
