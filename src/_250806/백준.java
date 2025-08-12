package _250806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * A가 B를 신뢰 (B->A)
 * 이중 list를 활용해 각 노드가 해킹 시 연계되는(길이 1) 값들을 저장한다.
 * 해당 list에서 하나씩 뺴서 queue에 넣고 그 밑에 값들을 계속 탐색한다.
 * 이때, 루프에 빠지지 않도록 방문여부처리를 진행
 */

public class 백준 {
	
	static List<Integer>[] graph;
	static int N;
	static int M;
	static int[] result;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		graph= new ArrayList[N+1];
		result= new int[N+1];
		
		for(int i=0; i<graph.length; i++) {
			graph[i]= new ArrayList<>();
		}
		
		// 네트워크 길이 1
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int com1= Integer.parseInt(st.nextToken());
			int com2= Integer.parseInt(st.nextToken());
			
			graph[com2].add(com1);
		}
		
		for(int i=1; i<N+1; i++) { // i는 노드 위치
			boolean[] visited= new boolean[N+1];
			Queue<Integer> queue= new LinkedList<>();
			
			visited[i]= true;
			queue.offer(i);
			int cnt= 0;
			
			while(!queue.isEmpty()) {
				int current= queue.poll();
				
				for(int next : graph[current]) {
					if(visited[next]==false) {
						visited[next]= true;
						queue.offer(next);
						cnt+=1;
					}
				}
			}
			
			result[i]= cnt;
		}
		
		int max_= 0;
		for(int i=1; i<N+1; i++) {
			if(result[i]>max_) {
				max_= result[i];
			}
		}
		
//		for(int i=1; i<N+1; i++) {
//			if(result[i]==max_) {
//				System.out.print(i + " ");
//			}
//		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
		    if (result[i] == max_) {
		        sb.append(i).append(" ");
		    }
		}
		System.out.println(sb.toString());

		
	}

}
