package _250829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _01_7793_오나의여신님_SWEA {
	
	static int Y;
	static int X;
	static int board[][];
	static List<int[][]> devil;
	
	static int firstDevil_Y;
	static int firstDevil_X;
	static int firstMy_Y;
	static int firstMy_X;
	
	static int dy[]= {-1, 0, 1, 0}; // 북 동 남 서
	static int dx[]= {0, 1, 0, -1};
	
	static int[][] copyBoard(int [][] board){
		int[][] copy= new int[Y][X];
		for(int y= 0; y<Y; y++) {
			for(int x= 0; x<X; x++) {
				copy[y][x]= board[y][x];
			}
		}
		return copy;
	}
	
	static void getDevilTimeLine() {
		devil= new ArrayList<>();
		int[][] devilBoard= new int[Y][X];
		
		Queue<int[]> queue= new LinkedList<>();
		
		// 여러 악마 시작점 모두 큐에 추가 + 즉시 표시
		for(int y= 0; y<Y; y++) {
			for(int x= 0; x<X; x++) {
				if(board[y][x]== -1) {
					queue.add(new int[] {y, x, 0});
					devilBoard[y][x]= 1;
				}
			}
		}
		// 0초 스냅샷
		devil.add(copyBoard(devilBoard));
		
		int index= 0;
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			
			for(int i= 0; i<4; i++) {
				int ny= temp[0]+dy[i];
				int nx= temp[1]+dx[i];
				
				// 범위, 돌(X), 목적지(D) 차단, 중복 차단(devilBoard로 대체)
				if(0<=ny && ny<Y && 0<=nx && nx<X 
				   && board[ny][nx]!= 1 
				   && board[ny][nx]!= 100 
				   && devilBoard[ny][nx]!= 1) {
					
					devilBoard[ny][nx]= 1; // enqueue 전에 표시(중복 방지)
					queue.add(new int[] {ny, nx, temp[2]+1});
				}
			}
			
			// 초가 바뀌면 스냅샷 저장(깊은 복사)
			if(index!= temp[2]) {
				devil.add(copyBoard(devilBoard));
				index+= 1;
			}
		}
		// 마지막 상태 한 번 더 저장(안전)
		devil.add(copyBoard(devilBoard));
	}
	
	static int bfs() {
		Queue<int[]> queue= new LinkedList<>();
		int[][] visited= new int[Y][X];
		
		queue.add(new int[] {firstMy_Y, firstMy_X, 0});
		visited[firstMy_Y][firstMy_X]= 1;
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			
			for(int i= 0; i<4; i++) {
				int ny= temp[0]+dy[i];
				int nx= temp[1]+dx[i];
				int nt= temp[2]+1;
				
				if(0<=ny && ny<Y && 0<=nx && nx<X) {
					if(visited[ny][nx]== 1) continue;
					if(board[ny][nx]== 1) continue; // 돌은 못 감
					
					if(board[ny][nx]== 100) { // 목적지 도착
						return nt;
					}
					
					// 스냅샷 인덱스 보정
					int snapIdx= nt<devil.size()? nt: devil.size()-1;
					// 다음 초(nt)에 악마가 점령한 칸이면 이동 불가
					if(devil.get(snapIdx)[ny][nx]== 1) continue;
					
					visited[ny][nx]= 1;
					queue.add(new int[] {ny, nx, nt});
				}
			}
		}
		return -1;
	}
	
	static int solution() {
		getDevilTimeLine();
		return bfs();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc= 1; tc<T+1; tc++) {
			String[] wh= br.readLine().split(" ");
			Y= Integer.parseInt(wh[0]);
			X= Integer.parseInt(wh[1]);
			
			board= new int[Y][X];
			
			for(int y= 0; y<Y; y++) {
				String line= br.readLine();
				for(int x= 0; x<X; x++) {
					char c= line.charAt(x);
					if(c=='*') { 
						board[y][x]= -1; // 악마 위치
						firstDevil_Y= y; // (여러 개여도 마지막 값만 보관; 로직엔 영향 없음)
						firstDevil_X= x;
					}else if(c=='X') {
						board[y][x]= 1; // 돌
					}else if(c=='D') {
						board[y][x]= 100; // 목적지
					}else if(c=='S') {
						board[y][x]= 10; // 주인공
						firstMy_Y= y;
						firstMy_X= x;
					}else {
						board[y][x]= 0; // 평지
					}
				}
			}
			
			int result= solution();
			
			if(result!= -1) {
				System.out.println("#"+tc+" "+result);
			}else {
				System.out.println("#"+tc+" GAME OVER");
			}
		}
	}
}
