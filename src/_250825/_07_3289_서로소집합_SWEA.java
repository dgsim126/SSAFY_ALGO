package _250825;

import java.io.*;
import java.util.*;

/**
 * 작은 노드가 무조건 상위 노드가 되도록 설정
 */

public class _07_3289_서로소집합_SWEA  {
	
	static int N;
	static int M;
	static int[] parent;
	
	static int find(int node) { // 가장 상위 루트 노드 반환
		while(node!=parent[node]) {
			node= parent[node];
		}
		
		return node;
	}
	
	static void union(int node1, int node2) {
		int node1_root= find(node1);
		int node2_root= find(node2);
		
		if(node1_root<node2_root) {
			parent[node2_root]= node1_root;
		}else if(node1_root>node2_root) {
			parent[node1_root]= node2_root;
		}else {
			return;
		}
	}
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T= Integer.parseInt(br.readLine());
        
        for(int i=1; i<T+1; i++) {
        	System.out.print("#" + i + " ");
        	st= new StringTokenizer(br.readLine());
        	
        	N= Integer.parseInt(st.nextToken());
        	M= Integer.parseInt(st.nextToken());
        	
        	parent= new int[N+1];
        	
        	for(int j=1; j<N+1; j++) {
        		parent[j]= j;
        	}
        	
        	for(int j=0; j<M; j++) {
        		st= new StringTokenizer(br.readLine());
        		int flag= Integer.parseInt(st.nextToken());
        		int node1= Integer.parseInt(st.nextToken());
        		int node2= Integer.parseInt(st.nextToken());
        		
        		
        		
        		if(flag==0) {
        			union(node1, node2);
        		}else {
        			if(find(node1)==find(node2)) {
        				System.out.print(1);
        			}else {
        				System.out.print(0);
        			}
        		}
        	}
        	System.out.println();
        }
    }
}

