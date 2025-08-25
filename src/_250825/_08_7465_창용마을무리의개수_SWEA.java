package _250825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 아까와 같은 서로소 문제이다.
 * 몇 개의 무리가 존재하는지를 파악해야 한다.
 */

public class _08_7465_창용마을무리의개수_SWEA {
	
	static int N;
	static int M;
	static int[] parent;
	
	static int find(int node) {
		while(node!=parent[node]) {
			node= parent[node];
		}
		
		return node;
	}
	
	static void union(int node1, int node2) {
		int node1_root= find(node1);
		int node2_root= find(node2);
		
		if(node1_root>node2_root) {
			parent[node1_root]= node2_root;
		}else if(node1_root<node2_root) {
			parent[node2_root]= node1_root;
		}else {
			return;
		}
	}
	
	static int solution() {
		// 이 안에는 몇 개의 그룹이 있는지는 파악하면 된다.
		
		Set<Integer> set= new HashSet<>();
		
		for(int i=1; i<N+1; i++) {
			set.add(find(i));
		}
		
		return set.size();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			
			parent= new int[N+1];
			for(int j=1; j<N+1; j++) {
				parent[j]= j;
			}
			
			for(int j=0; j<M; j++) {
				st= new StringTokenizer(br.readLine());
				int node1= Integer.parseInt(st.nextToken());
				int node2= Integer.parseInt(st.nextToken());
				
				union(node1, node2);
			}
			
			int result= solution();
			System.out.println("#" + i + " " + result);
		}
	}

}
