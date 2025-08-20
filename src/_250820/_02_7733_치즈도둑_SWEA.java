package _250820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * N의 최대 길이는 100
 * 매일 새롭게 bfs로 개수를 확인해야 하는가?
 * 시간 내 충분
 * 
 */

public class _02_7733_치즈도둑_SWEA {
	
	static int N;
	static Set<Integer> set;
	static int board[][];
	static int visited[][];
	static int result;
	
	static int[] dy= {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dx= {0, 1, 0, -1};
	
	static void eatCheeze(int val) {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(board[y][x]==val) {
					board[y][x]= -1;
				}
			}
		}
	}
	
	static int check() {
		visited= new int[N][N];
		int cnt= 0;
		Queue<Integer> queue_y= new LinkedList<>();
		Queue<Integer> queue_x= new LinkedList<>();
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(board[y][x]!=-1 && visited[y][x]!=1) {
					queue_y.add(y);
					queue_x.add(x);
					cnt+=1;
					visited[y][x]= 1;
				}
				
				while(!queue_y.isEmpty()) {
					int cy= queue_y.poll();
					int cx= queue_x.poll();
					
					for(int i=0; i<4; i++) {
						int ny= cy+dy[i];
						int nx= cx+dx[i];
						
						if(0<=ny && ny<N && 0<=nx && nx<N && visited[ny][nx]==0 && board[ny][nx]!=-1) {
							visited[ny][nx]= 1;
							queue_y.add(ny);
							queue_x.add(nx);
						}
					}
				}
			}
		}
		
		
		
		
		return cnt;
	}

	
	static void solution() {
		
		result= Math.max(result, check()); // 초기 상태가 정답일수도 있음
		
		for(int i=1; i<101; i++) {
			if(set.contains(i)) {
				eatCheeze(i);
				
				int temp= check();
				
				if(temp>result) {
					result= temp;
				}
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			board= new int[N][N];
			result= 0;
			set= new HashSet<>();
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
					set.add(board[y][x]);
				}
			}
			
			solution();
			
			System.out.println("#" + i + " " + result);
		}

	}

}
