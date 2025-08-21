package _250820_Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 정점을 표현하기 위해 Node 클래스를 생성
 * from과 to가 존재한다면 from Node 안에는 to Node들이 저장된 ArrayList가 존재
 */



public class _01_1753_최단경로_BOJ_ver1 {
	
	static class Node{
		List<int[]> connected= new ArrayList<>(); // {연결된 노드 번호, 거리}
	}
	
	static int V; // 정점의 개수
	static int E; // 간선의 개수
	static int start_node;
	static Node[] node;
	static int[] visited;
	static int[] weight;
	
	static int toFindMinWeightNode() {
		int min_= 2147483647;
		int index= -1;
		
		for(int i=1; i<V+1; i++) {
			if(visited[i]!=1) {
				if(weight[i]<min_) {
					min_= weight[i];
					index= i;
				}
			}
		}
		
		return index;
	}
	
	static void solution() {
		
		/**
		 * 1. 시작 노드의 경우에는 0으로 설정
		 */
		weight[start_node]= 0; 

		
		
		/**
		 * 2. weight에서 가장 가중치가 작은 값을 찾는다. 이때 만약 visited가 1이라면(방문했다면)
		 * 그 다음으로 가중치가 작은 값을 찾는다.
		 * 방문했다면 이 시점에서 vistied를 1로 변경할 것
		 * 
		 * 찾은 fromNode와 연결된 값들을 찾는다.
		 * 이때 연결된 toNode들의 값을 최신화한다.
		 * 
		 * 최신화는 다음과 같다.
		 * 현재 toNode에 값을 일단 기억한다.
		 * (fromNode+toNode의 거리) vs toNode를 비교하여 더 작은 값을 weight에 넣는다.
		 * 
		 */
		
		while(true) {
			int min_node= toFindMinWeightNode();
			
			// 탈출조건
			if(min_node==-1 || weight[min_node]==2147483647) {
				break;
			}
			visited[min_node]= 1;
			
			for(int n[] : node[min_node].connected) {
				int to= n[0];
				int distance= n[1];
				
				weight[to]= Math.min(weight[min_node]+distance, weight[to]);
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		V= Integer.parseInt(st.nextToken());
		E= Integer.parseInt(st.nextToken());
		start_node= Integer.parseInt(br.readLine());
		
		node= new Node[V+1]; // 0번 인덱스 사용하지 않음
		for(int i=0; i<V+1; i++) {
			node[i]= new Node();
		}
		
		visited= new int[V+1];
		
		weight= new int[V+1];
		for(int i=0; i<V+1; i++) {
			weight[i]= 2147483647;
		}
		
		for(int i=0; i<E; i++) {
			st= new StringTokenizer(br.readLine());
			int from= Integer.parseInt(st.nextToken());
			int to= Integer.parseInt(st.nextToken());
			int weight= Integer.parseInt(st.nextToken());
			
			node[from].connected.add(new int[] {to, weight});
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
