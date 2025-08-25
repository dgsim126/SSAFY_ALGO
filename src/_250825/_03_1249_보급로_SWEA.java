package _250825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * N=100 이므로 완전탐색 불가
 * 
 * weight를 저장할 n*n 배열을 생성(시작 위치는 0으로 인접 노드들만 값을 가중치로 넣어두기)
 * 방문처리를 할 visited n*n 배열을 생성
 * 
 * 방문처리를 확인해야 하니 백트래킹 dfs
 * 
 * 각각의 인덱스를 노드라고 생각해보자.
 * 처음 시작 지점의 가중치는 0이다.
 * 해당 노드와 연결된 노드들을 방문한다. (북 동 남 서)
 * 해당 값들을 queue에 넣고 최소힙으로 가장 작은 값을 찾아낸다.
 * 가장 작은 노드에 접근하여 중첩된 가중치와 weight 배열에 저장된 가중치를 비교한다.
 * 이때 (중첩된 가중치 + arr[해당 인덱스]) vs weight[해당 인덱스] 를 비교하여 더 작은 값으로 해당 인덱스의 weight를 최신화한다.
 */

public class _03_1249_보급로_SWEA {
	
	static int N;
	static int[][] board;
	static int[][] weight;
	
	static int[] dy= {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dx= {0, 1, 0, -1};
	
	static void solution() {
		PriorityQueue<int[]> queue= new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0])); // 앞의 값을 기준으로 우선순위
		queue.add(new int[] {0, 0, 0}); // 누적, y, x
		weight[0][0]= 0;
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			int cum= temp[0];
			int y= temp[1];
			int x= temp[2];
			
			if(cum>weight[y][x]) { // 누적값이 이미 현재 존재하는 값보다 클 때
				continue;
			}
			
			for(int i=0; i<4; i++) {
				int ny= y+dy[i];
				int nx= x+dx[i];
				
				if(0<=ny && ny<N && 0<=nx && nx<N) {
					if(cum+board[ny][nx] < weight[ny][nx]) {
						weight[ny][nx]= cum+board[ny][nx];
						queue.add(new int[] {cum+board[ny][nx], ny, nx});
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			board= new int[N][N];
			weight= new int[N][N];
			
			for(int y=0; y<N; y++) {
				String temp= br.readLine();
				for(int x=0; x<N; x++) {
					board[y][x]= temp.charAt(x)-'0';
					weight[y][x]= 2147483647;
				}
			}
			
			solution();
			
			System.out.println("#" + i + " " + weight[N-1][N-1]);
		}
	}
}
