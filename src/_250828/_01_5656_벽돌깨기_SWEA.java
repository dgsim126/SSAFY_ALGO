package _250828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 구슬은 깨질 때, 벽돌의 숫자만큼 사방으로 벽돌을 깨트린다.
 * 이때 사방으로 터진 벽돌들도 해당 범위만큼 추가로 벽돌을 깨트린다.
 * 연쇄적으로 작동할 떄 depth가 중요하다. 1번 연쇄가 전부 처리된 다음에 2번 연쇄가 작동되어야한다.
 * depth를 포함한 heapq를 만들어 제거하는 과정이 필요해 보인다.
 * 
 * 아니다! depth가 필요가 없다.
 * 보니까 현재 맵을 고정한 후, 모든 연쇄를 다 터트리는 방식이다.
 * 
 * 그렇다면 방식을 변경해야 한다.
 * 아니 변경할 필요 없이 굳이 heapq를 사용할 필요가 없어진다.
 * 그냥 queue에 넣으면 된다.
 * 
 * 그렇다면 어떻게 구현하면 되는가?
 * 1. 특정 위치(y, x)를 큐에 넣는다.
 * 2. 큐에서 위치(y, x)를 뺀 후, visited==0인지 방문여부를 확인한다. 만약 visited==1인 경우는 이미 공이 터진 경우이므로 패스한다.
 * 3. visited==0이라면 visited==1로 변경한다.
 * 4. 해당 위치에서 터지는 주변 인덱스를 전부 큐에 넣는다. (큐에서 뺄 때, 방문처리를 할 것이기에 굳이 visited 건드릴 필요 없다)
 * 
 * 그렇다면 최적의 경우를 구하기 위해서는 모든 경우를 다 탐색해봐야 하는것인가?
 * W는 최대 12, N은 최대 4이다. 12*12*12*12 =~ 20000 가능
 */

public class _01_5656_벽돌깨기_SWEA {
	
	static int N; // 공의 수
	static int W; // x
	static int H; // y
	static int[][] board; // 게임판
	static int[][] board_save;
	static int[][] visited; // 게임판 처리 여부
	static int result; 
 	
	static int toFindY(int x) {
		for(int i=0; i<H; i++) {
			if(board[i][x]!=0) {
				return i;
			}
		}
		return -1; // 해당 y축에 공이 없을 경우
	}
	
	static void bomb(int y, int x) {
		
		Queue<Integer> queue_y= new LinkedList<>();
		Queue<Integer> queue_x= new LinkedList<>();
		
		queue_y.add(y);
		queue_x.add(x);
		
		while(!queue_y.isEmpty()) {
			int cy= queue_y.poll();
			int cx= queue_x.poll();
			int bomb_size= board[cy][cx]-1; // 
			
			if(visited[cy][cx]==1 || board[cy][cx]==0) { // 이미 방문했던 곳이라면(해당 위치를 기점으로 연쇄폭발이 일어난 곳이라면) or 해당 위치 0이라면
				continue;
			}
			
			visited[cy][cx]= 1; // 방문처리
			board[cy][cx]= 0; // 해당 위치는 이제 터졌기에 블록이 존재하지 않는다.
			
			// bomb_size만큼 queue에 넣으면 된다.
			int temp_y= cy;
			int temp_x= cx;
			for(int i=0; i<bomb_size; i++) { // 북
				temp_y+=-1;
				temp_x+=0;
				
				if(0<=temp_y && temp_y<H && 0<=temp_x && temp_x<W) {
					queue_y.add(temp_y);
					queue_x.add(temp_x);
				}else {
					break;
				}
			}
			
			temp_y= cy;
			temp_x= cx;
			for(int i=0; i<bomb_size; i++) { // 동
				temp_y+=0;
				temp_x+=1;
				
				if(0<=temp_y && temp_y<H && 0<=temp_x && temp_x<W) {
					queue_y.add(temp_y);
					queue_x.add(temp_x);
				}else {
					break;
				}
			}
			
			temp_y= cy;
			temp_x= cx;
			for(int i=0; i<bomb_size; i++) { // 남
				temp_y+=1;
				temp_x+=0;
				
				if(0<=temp_y && temp_y<H && 0<=temp_x && temp_x<W) {
					queue_y.add(temp_y);
					queue_x.add(temp_x);
				}else {
					break;
				}
			}

			temp_y= cy;
			temp_x= cx;
			for(int i=0; i<bomb_size; i++) { // 서
				temp_y+=0;
				temp_x+=-1;
				
				if(0<=temp_y && temp_y<H && 0<=temp_x && temp_x<W) {
					queue_y.add(temp_y);
					queue_x.add(temp_x);
				}else {
					break;
				}
			}
		}
		
	}
	
	// 0을 제거하고 밑으로 내린다.
	static void gravity() {
		for(int x=0; x<W; x++) {
			Deque<Integer> lst= new ArrayDeque<>();
			for(int y=0; y<H; y++) {
				if(board[y][x]!=0) {
					lst.addLast(board[y][x]);
				}
				board[y][x]= 0;
			}
			
			int index= H-1;
			while(!lst.isEmpty()) {
				board[index][x]= lst.removeLast();
				index--;
			}
		}
	}
	
	static int cntBlock() {
		int cnt= 0;
		for(int y=0; y<H; y++) {
			for(int x=0; x<W; x++) {
				if(board[y][x]!=0) {
					cnt+=1;
				}
			}
		}
		
		return cnt;
	}
	
	static void game(int balls_order_index[]) { // 인자로 받은 것은 공이 떨어지는 위치
		
		for(int i=0; i<balls_order_index.length; i++) {
			int x= balls_order_index[i];
			int y= toFindY(x); // balls_order_index에서 x좌표 값을 하나 꺼내 y좌표를 구한다.
			
			if(y==-1) { // 해당 공 떨구는 위치에 블록이 없을 경우
				continue;
			}
			
			visited= new int[H][W]; // 방문여부 초기화는 배먼 해줘야 한다.
			bomb(y,x); // 구해낸 y,x좌표를 이용해 연쇄로 블록을 부순다.
			
			gravity(); // 중력 on
		}
		
		int current= cntBlock(); // 현재 남은 블록의 개수를 cnt한다.
		result= Math.min(current, result);
		
	}
	
	static void sync() {
		for(int y=0; y<H; y++) {
			for(int x=0; x<W; x++) {
				board[y][x]= board_save[y][x];
			}
		}
	}
 	
	static void dfs(int depth, int current[]) {
		
		if(depth==N) {
			// System.out.println(Arrays.toString(current));
			sync();
			game(current); // 공을 떨어뜨리는 위치가 정해졌을 때마다 game()을 호출한다.
			return;
		}
		
		for(int i=0; i<W; i++) {
			current[depth]= i;
				
			dfs(depth+1, current);
		}
	}
	
	static void solution() {
		int[] temp= new int[N]; // 어디에 공을 떨굴지를 저장하는 함수(참고로 순열을 넣어놓아야 한다)
		
		dfs(0, temp); // 공을 떨구는 모든 경우의 수를 구하기 위해
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			W= Integer.parseInt(st.nextToken());
			H= Integer.parseInt(st.nextToken());
			
			board= new int[H][W];
			board_save= new int[H][W];
			result= 2147483647;
			
			for(int i=0; i<H; i++) {
			    st= new StringTokenizer(br.readLine());
			    for(int j=0; j<W; j++) {
			        board[i][j]= Integer.parseInt(st.nextToken());
			        board_save[i][j]= board[i][j];
			    }
			}
			
			solution();
			
			System.out.println("#"+tc+" "+result);
		}

	}

}
