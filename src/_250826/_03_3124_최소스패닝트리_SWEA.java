package _250826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A, B, C를 가진 arr를 활용한다.
 * 그렇게 arr를 만든 후, 가중치인 C를 기준으로 C가 작은 순서대로 선택한다.
 * 
 * parent[]를 만들고, A, B의 루트를 찾아 두 개가 다르다면 Union을 한다.
 * 같다면 continue를 진행한다.
 * 
 * 
 */

public class _03_3124_최소스패닝트리_SWEA {
	
	static int V; // 정점
	static int E; // 간선
	static int arr[][]; // start end length
	static int parent[];
	static long result;
	
	static void setParent() {
		for(int i=1; i<V+1; i++) {
			parent[i]= i;
		}
	}
	
	static int find(int node) {
		while(node!=parent[node]) {
			node= parent[node];
		}
		return node;
	}
	
	static void union(int node1, int node2) {
		int node1_parent= find(node1);
		int node2_parent= find(node2);
		
		if(node1_parent>node2_parent) {
			parent[node1_parent]= node2_parent;
		}else if(node1_parent<node2_parent) {
			parent[node2_parent]= node1_parent;
		}
	}
	
	static void solution() {
		
		// 각 노드의 부모를 본인으로 초기화한다.
		setParent();
		
		// 정렬
		Arrays.sort(arr, (a, b) -> Integer.compare(a[2], b[2]));
		
		int current= 0;
		result= 0;
		// arr를 돌며 갱신한다.
		for(int i=0; i<arr.length; i++) {
			
			if(current==V-1) {
				break;
			}
			
			int start_node= arr[i][0];
			int end_node= arr[i][1];
			int length= arr[i][2];
			
			if(find(start_node)==find(end_node)) { // 같은 집합에 속하는 경우(사이클이 생기는 경우)
				continue;
			}else { // 사이클이 생기지 않는 경우
				union(start_node, end_node);
				result+=length;
				current+=1;
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			
			V= Integer.parseInt(st.nextToken());
			E= Integer.parseInt(st.nextToken());
			
			arr= new int[E][3];
			parent= new int[V+1];
			
			for(int j=0; j<E; j++) {
				st= new StringTokenizer(br.readLine());
				
				arr[j][0]= Integer.parseInt(st.nextToken());
				arr[j][1]= Integer.parseInt(st.nextToken());
				arr[j][2]= Integer.parseInt(st.nextToken());
			}
			
			solution();
			
			System.out.println("#" + i + " " + result);
			
		}

	}

}
