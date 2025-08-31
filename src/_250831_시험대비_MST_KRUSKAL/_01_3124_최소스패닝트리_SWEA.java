package _250831_시험대비_MST_KRUSKAL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 모든 노드를 연결하는 최소한의 간선을 구해야한다.
 * 
 * UNION-FIND를 응용하자.
 * 일단 Queue에 (가중치, A번 정점, B번 정점)의 형태로 모든 값을 넣어놓는다.
 * 가중치를 기준으로 정렬한다.
 * 
 * 해당 큐에서 값을 하나 뺀다.
 * 빼낸 값 A, B를 find하여 같은 루트를 바라보는지 확인한다.
 * 같은 루트를 바라보지 않는다면 해당 값을 연결시킨다.(연결시킨다는 의미는 더 큰 값의 parnet를 작은 값으로 설정하겠다는 의미)
 * 
 * 이렇게 사이클을 방지하며 크기가 작은 순서대로 연결하면 MST를 만들 수 있다.
 */

public class _01_3124_최소스패닝트리_SWEA {
	
	static int V; 
	static int E;
	static int parent[];
	static PriorityQueue<long[]> queue;
	static long result;
	
	static int find(int a) {
		if(a==parent[a]) {
			return a;
		}
		
		return parent[a]= find(parent[a]);
	}
	
	static void union(int a, int b) {
		a= find(a);
		b= find(b);
		
		if(a>b) {
			parent[a]= b;
		}else {
			parent[b]= a;
		}
	}
	
	static void solution() {
		result= 0;
		
		while(!queue.isEmpty()) {
			long[] current= queue.poll();
			int a= (int) current[0];
			int b= (int) current[1];
			long weight= current[2];
			
			if(find(a)==find(b)) { // 사이클 발생
				continue;
			}
			
			union(a, b);
			result+=weight;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			V= Integer.parseInt(st.nextToken());
			E= Integer.parseInt(st.nextToken());
			
			parent= new int[V+1];
			queue= new PriorityQueue<>((a, b)->Long.compare(a[2], b[2]));
			
			for(int i=1; i<V+1; i++) {
				parent[i]= i;
			}
			
			for(int i=0; i<E; i++) {
				st= new StringTokenizer(br.readLine());
				
				int temp1= Integer.parseInt(st.nextToken());
				int temp2= Integer.parseInt(st.nextToken());
				int temp3= Integer.parseInt(st.nextToken());
				
				queue.add(new long[] {temp1, temp2, temp3});
			}
			
			solution();
			
			System.out.println("#"+tc+" "+result);
		}

	}

}
