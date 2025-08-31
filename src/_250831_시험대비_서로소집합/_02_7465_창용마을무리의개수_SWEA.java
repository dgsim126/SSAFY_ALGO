package _250831_시험대비_서로소집합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 각각의 집합들을 묶은 후, 마지막에 parent의 개수만 구하면 되는 문제
 */

public class _02_7465_창용마을무리의개수_SWEA {
	
	static int N;
	static int M;
	static int order[][];
	static int parent[];
	
	static int find(int a) {
		if(parent[a]==a) {
			return a;
		}
		
		return parent[a]= find(parent[a]);
	}
	
	static void union(int a, int b) {
		a= find(a);
		b= find(b);
		
		if(a!=b) {
			if(a>b) {
				parent[a]= b;
			}else {
				parent[b]= a;
			}
		}
	}
	
	static int getResult() {
		Set<Integer> set= new HashSet<>();
		for(int i=1; i<N+1; i++) {
			set.add(find(i));
		}
		
		return set.size();
	}
	
	static int solution() {
		
		for(int i=0; i<M; i++) {
			int a= order[i][0];
			int b= order[i][1];
			
			union(a, b);
		}
		
		return getResult();
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			
			order= new int[M][2];
			parent= new int[N+1];
			
			for(int i=1; i<N+1; i++) {
				parent[i]= i;
			}
			
			for(int i=0; i<M; i++) {
				st= new StringTokenizer(br.readLine());
				order[i][0]= Integer.parseInt(st.nextToken());
				order[i][1]= Integer.parseInt(st.nextToken());
			}
			
			int result= solution();
			System.out.println("#" + tc + " " + result);
		}

	}

}
