package _251111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 크게 for문을 돌면서 방문하지 않은 곳의 배추가 있다면 해당 위치에서 bfs를 실행
 * bfs 실행 후, 다시 for문 반복
 * 총 몇 번의 bfs에 진입하게 되는지를 cnt하면 된다.
 *
 */

public class _01_1012_유기농배추 {
	
	static int[][] board;
	static int[][] is_visited;
	static int cnt;
	static int K; // 남은 배추의 개수
	static int m; // 가로
	static int n; // 세로
	
	static int[] dy= {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dx= {0, 1, 0, -1};
	
	public static void bfs(int y, int x) {
		Queue<int[]> queue= new LinkedList<int[]>();
		
		queue.add(new int[] {y, x});
		K-=1; 
		is_visited[y][x]= 1;
		
		while(!queue.isEmpty()) {
			int current[]= queue.poll(); 
			
			for(int i=0; i<4; i++) {
				int n_y= current[0]+dy[i];
				int n_x= current[1]+dx[i];
				
				if(0<=n_y && n_y<n && 0<=n_x && n_x<m 
						&& board[n_y][n_x]==1 && is_visited[n_y][n_x]==0) {
					queue.add(new int[] {n_y, n_x});
					K-=1;
					is_visited[n_y][n_x]= 1;
				}
			}
		}
		
		
		
	}
	
	public static void solution() { 
		
		for(int y=0; y<n; y++) {
			for(int x=0; x<m; x++) {
				
				// 배추가 더 이상 존재하지 않는 경우
				if(K<=0) {
					return;
				}
				
				if(board[y][x]==1 && is_visited[y][x]==0) {
					cnt+=1;
					bfs(y, x);
				}
			}
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			m= Integer.parseInt(st.nextToken()); // 가로
			n= Integer.parseInt(st.nextToken()); // 세로
			K= Integer.parseInt(st.nextToken()); // 배추의 개수
			
			board= new int[n][m];
			is_visited= new int[n][m];
			cnt= 0;
			
			for(int j=0; j<K; j++) {
				st= new StringTokenizer(br.readLine());
				int x= Integer.parseInt(st.nextToken());
				int y= Integer.parseInt(st.nextToken());
				
				board[y][x]= 1;
			}
			
			solution();
			
			System.out.println(cnt);
		}
	}

}
