package _250803;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * a-> b를 신뢰하는 경우, b를 해킹하면 a도 해킹
 * 이중 arrayList를 활용
 */
public class _06_효율적인해킹 {
	
	static int N;
	static int M;
	static List<Integer>[] graph;
	static boolean visited[];
	static int result[];
	
	static void solution() {
		for(int i=1; i<N+1; i++) {
			visited= new boolean[N+1];
			Queue<Integer> queue= new LinkedList<>();
			int cnt= 0;
			
			visited[i]= true;
			queue.offer(i);
			
			while(!queue.isEmpty()) {
				int current= queue.poll();
				
				for(int j=0; j<graph[current].size(); j++) {
					int next= graph[current].get(j);
					if(visited[next]==false) {
						visited[next]= true;
						queue.offer(next);
						cnt+=1;
					}
				}
			}
			result[i]= cnt;
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		
		graph= new ArrayList[N+1];
		result= new int[N+1];
		
		for(int i=0; i<N+1; i++) {
			graph[i]= new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int com1= Integer.parseInt(st.nextToken());
			int com2= Integer.parseInt(st.nextToken());
			
			graph[com2].add(com1);
		}
		
		solution();
		
		// 결과 출력
		int max_= 0;
		for(int i=1; i<N+1; i++) {
			if(result[i]>max_) {
				max_= result[i];
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
		    if (result[i] == max_) {
		        sb.append(i).append(" ");
		    }
		}
		System.out.println(sb.toString());
		
		

	}

}
