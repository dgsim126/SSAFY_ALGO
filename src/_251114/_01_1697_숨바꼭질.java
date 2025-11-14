package _251114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1차원 배열을 만든다.
 * 현재 내 위치 N에서 3가지 방법으로 이동한다. 
 * 이동한 해당 index 위치에 값을 +1 시킨다.
 * 만약 이동한 위치 index에 값이 존재한다면 현재의 가중치(시간)과 비교해 작은 값을 넣는다.
 * 
 * 그럴 필요가 없다. bfs의 경우는 도착한 그 순간이 최단거리이기 떄문이다.
 * 
 * 
 * 
 */

public class _01_1697_숨바꼭질 {
	
	static int N; 
	static int K;
	static int result;
	static int[] is_visited;
	
	static void solution() {
		
		Queue<int[]> queue= new LinkedList<>(); 
		queue.add(new int[] {N, 0}); // {현재 위치, 시간}
		is_visited[N]= 1;
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			
			// 탈출 조건
			if(temp[0]==K) {
				result= temp[1];
				return;
			}
			
			if(0<=temp[0]+1 && temp[0]+1 <= 100000 && is_visited[temp[0]+1]==0) {
				queue.add(new int[] {temp[0]+1, temp[1]+1});
				is_visited[temp[0]+1]= 1;
			}
			
			if(0<=temp[0]-1 && temp[0]-1<= 100000 &&is_visited[temp[0]-1]==0) {
				queue.add(new int[] {temp[0]-1, temp[1]+1});
				is_visited[temp[0]-1]= 1;
			}
			
			if(0<=temp[0]*2 && temp[0]*2 <= 100000 &&is_visited[temp[0]*2]==0) {
				queue.add(new int[] {temp[0]*2, temp[1]+1});
				is_visited[temp[0]*2]= 1;
			}
		}
				
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		is_visited= new int[100000];
		
		solution();
		
		System.out.println(result);
		
	}

}
