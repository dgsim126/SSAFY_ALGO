package _1_A형기출_SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최대한 긴 등산로
 * 시작은 가장 큰 숫자부터 작은 곳으로(가로, 세로만)
 * 
 * 긴 등산로를 만들기 위해 딱 한 곳을 정해 최대 k 깊이만큼 지형 깎기 가능
 * 
 * 1. 가장 높은 곳들을 모두 찾아 저장
 * 2. dfs를 활용해 4방향으로 탐색(이때, 만약 크기가 더 크다면 k만큼 잘라 이동 가능한지 확인)
 * 
 * 
 */

public class _03_1949_등산로조성 {
	
	static int N;
	static int K;
	static int[][] board;
	static int[][] visited;
	
	static int max_height; // 가장 높은 산봉우리 높이
	static int cnt;
	static int max_length;
	
	static int[] start_y;
	static int[] start_x;
	
	static int[] dy= {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dx= {0, 1, 0, -1};
	
	static void toFindStart() {
		int index= 0;
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(board[y][x]==max_height) {
					start_y[index]= y;
					start_x[index]= x;
					index+=1;
				}
			}
		}
	}
	
	static void dfs(int y, int x, int len, int current, int haveChance) { // y, x, 현재 길이, 현재 높이, 공사가능여부(1==공사 가능)
		
		if(len>max_length) {
			max_length= len;
		}
		
		for(int i=0; i<4; i++) {
			int n_y= y+dy[i];
			int n_x= x+dx[i];
			
			if(0<=n_y && n_y<N && 0<=n_x && n_x<N) {
				
				if(visited[n_y][n_x]==-1) { // 이미 방문한 곳인 경우
					continue;
					
				}else if(current>board[n_y][n_x]) { // 더 작은 곳으로 가는 경우
					visited[n_y][n_x]= -1;
					
					dfs(n_y, n_x, len+1, board[n_y][n_x], haveChance);
					
					visited[n_y][n_x]= 0;
					
				}else { // 더 높은 곳이거나 같은 높이인 경우
					if(haveChance==1 && current>board[n_y][n_x]-K) { // 공사가 가능한 경우
						visited[n_y][n_x]= -1;
						
						dfs(n_y, n_x, len+1, current-1, 0);
						
						visited[n_y][n_x]= 0;
					}
				}
			}
		}
	}
	
	static void solution() {
		
		start_y= new int[cnt];
		start_x= new int[cnt];
		
		toFindStart();
		
		for(int i=0; i<cnt; i++) {
			visited= new int[N][N];
			visited[start_y[i]][start_x[i]]= -1;
			dfs(start_y[i], start_x[i], 1, max_height, 1);
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			board= new int[N][N];
			max_height= 0;
			max_length= 0;
			cnt= 0;
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
					if(board[y][x]>max_height) {
						max_height= board[y][x];
						cnt= 1;
					}else if(board[y][x]==max_height) {
						cnt+=1;
					}
				}
			}
			
			solution();
			
			System.out.println("#" + i + " " + max_length);
		}
	}

}
