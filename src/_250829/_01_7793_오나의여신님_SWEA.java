package _250829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 주인공 이동(S) == 10
 * - 1초에 한 칸 이동 가능
 * - 돌이 있다면 이동 불가
 * - 악마의 손아귀에 닿으면 안 된다.
 * 
 * 악마의 손아귀(*) == -1
 * - 1초마다 상하좌우 이동
 * - 돌이 있다면 확장 안함
 * 
 * 목적지: D == 100
 * 돌: X == 1
 * 평지 == 0
 * 
 * 어떻게 풀어야하나
 * 이걸 완전탐색으로 풀 수 있나?
 * 그렇다면 백트래킹으로 맵을 이전 상태로 되돌려야 하는데 괜찮나?
 * 
 * 악마의 손아귀의 1초마다를 계속 연산하면 시간이 너무 오래 걸리지 않나? 모든 위치에서 번져 나가는데?
 * 악마의 손아귀를 모두 구할까?
 * 딱 한 번 매 초마다의 악마의 손아귀를 모두 구해놓고, dfs로 현재 수연위의 위치배열만 넘기면서 백트래킹하면 괜찮지 않나?
 * 어차피 악마의 손아귀는 50초 내에 끝난다.
 * 
 * 그렇다면 악마의 손아귀는 어떻게 구할것인가?
 * 일단 2차원 배열을 ArrayList에 넣는다. 각 arrayList의 인덱스는 날짜를 나타내고, arrayList 안의 2차원 배열은 현재의 악마의 손아귀 board를 나타낸다.
 * default board를 하나 만든다.
 * 첫 위치를 queue에 넣고(y,x,0) queue에서 뺀 후, visited 처리를 하고, 인접 노드들을 방문한다.
 * queue에서 인접 노드 4곳을 처음에 방문할텐데 새롭게 (y,x,1)을 넣는다.(인접 노드들의 visited를 확인하고 넣자!, 
 * 
 * 다시 queue에서 계속 빼는데 만약 뺴낸 값의 3번째 인자가 바뀌었을경우(0->1)현재까지의 defaultboard를 복사하여 ArrayList에 넣는다.
 * 
 */

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
		
		for(int y=0; y<Y; y++) {
			for(int x=0; x<X; x++) {
				copy[y][x]= board[y][x];
			}
		}
		
		return copy;
	}
	
	static void getDevilTimeLine() {
		devil= new ArrayList<>();
		int[][] devilBoard= new int[Y][X];
		
		Queue<int[]> queue= new LinkedList<>();
		queue.add(new int[] {firstDevil_Y, firstDevil_X, 0});
		
		devilBoard[firstDevil_Y][firstDevil_X]= 1;
	    devil.add(copyBoard(devilBoard));
		
		int index= 0;
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			
			for(int i=0; i<4; i++) {
				int ny= temp[0]+dy[i];
				int nx= temp[1]+dx[i];
				
				if(0<=ny && ny<Y && 0<=nx && nx<X && board[ny][nx]!=1 && devilBoard[ny][nx]!=1 && board[ny][nx]!=1 && board[ny][nx]!=100) {
					devilBoard[ny][nx]= 1;
					queue.add(new int[] {ny, nx, temp[2]+1});
				}
			}
			
			if(index!=temp[2]) {
				devil.add(copyBoard(devilBoard));
				index+=1;
			}
		}
		devil.add(copyBoard(devilBoard));
	}
	
	static int bfs() {
		Queue<int[]> queue= new LinkedList<>();
		int[][] visited= new int[Y][X];
		
		queue.add(new int[] {firstMy_Y, firstMy_X, 0});
		visited[firstMy_Y][firstMy_X]= 1;
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			visited[temp[0]][temp[1]]= 1;
			
			for(int i=0; i<4; i++) {
				int ny= temp[0]+dy[i];
				int nx= temp[1]+dx[i];
				int nt= temp[2]+1;
				
				if(0<=ny && ny<Y && 0<=nx && nx<X) {
					if(visited[ny][nx]==1) {
						continue;
					}
		
					if(board[ny][nx]==100) { // 목적지 도착
						return nt;
					}
					
	
					if(devil.get(nt)[ny][nx]==1) {
						continue;
					}
					visited[ny][nx]= 1;
					queue.add(new int[] {ny, nx, temp[2]+1});
				}
			}
		}
		
		return -1;
	}
	
	static int solution() {
		
		// 매 시간마다의 악마의 손아귀 상태를 만든다.
		getDevilTimeLine();
		
		// bfs로 최단거리를 찾는다.
		return bfs();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			Y= Integer.parseInt(st.nextToken());
			X= Integer.parseInt(st.nextToken());
			
			board= new int[Y][X];
			
			for (int y = 0; y < Y; y++) {
			    String line = br.readLine();
			    for (int x = 0; x < X; x++) {
			        char c = line.charAt(x);
			        if (c == '*') {
			            board[y][x] = -1;  // 악마
			            firstDevil_Y = y;
			            firstDevil_X = x;
			        } else if (c == 'X') {
			            board[y][x] = 1;   // 돌
			        } else if (c == 'D') {
			            board[y][x] = 100; // 목적지
			        } else if (c == 'S') {
			            board[y][x] = 10;  // 주인공
			            firstMy_Y = y;
			            firstMy_X = x;
			        } else { // '.' 등
			            board[y][x] = 0;   // 평지
			        }
			    }
			}

			
			int result= solution();
			
			if(result!=-1) {
				System.out.println("#"+tc+" "+result);
			}else {
				System.out.println("#"+tc+" GAME OVER");
			}
			
		}
	}

}
