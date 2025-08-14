package _250814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 배열을 돌면서 코어를 발견
 * dfs를 통해 계속 내려가려면, 각 노드들의 위치를 저장한 배열을 미리 만들어야 한다.
 * 그래서 dfs 속에서 dfs를 호출할 때 index+1을 하여 바로 다음 노드로 이동하도록 해야할 듯 하다.
 * 
 */
public class _01_1767_프로세서연결하기 {
	
	static int N;
	static int[][] board;
	static int[][] visited;
	
	static List<Integer> Y;
	static List<Integer> X;
	
	static int dy[]= {-1, 0, 1, 0}; // 북 동 남 서
	static int dx[]= {0, 1, 0, -1};
	
	static int totalCore;
	
	static int bestConnected;
	static int bestLength;
	
	
	static void convertVisit(int direction, int y, int x, int length) {
		int n_y= y+dy[direction];
		int n_x= x+dx[direction];
		for(int i=0; i<length; i++) {
			visited[n_y][n_x]= 1;
			n_y+=dy[direction];
			n_x+=dx[direction];
			
		}
	}
	
	static void convertREVisit(int direction, int y, int x, int length) {
		int n_y= y+dy[direction];
		int n_x= x+dx[direction];
		for(int i=0; i<length; i++) {
			visited[n_y][n_x]= 0;
			n_y+=dy[direction];
			n_x+=dx[direction];
			
		}
	}
	
	static int check(int direction, int y, int x) {
		int n_y= y+dy[direction];
		int n_x= x+dx[direction];
		int check= 0;
		
		while(0<=n_y && n_y<N && 0<=n_x && n_x<N) {
			if(board[n_y][n_x]!=1 && visited[n_y][n_x]!=1) {
				check+=1;
				n_y+=dy[direction];
				n_x+=dx[direction];
			}else {
				return 0;
			}
					
		}
		return check;
	}
	
	static void dfs(int connected, int current, int wireLen) { // 현재 연결한 코어 개수, 현재 코어 인덱스, 현재 전선 길이 총합
		// 가지치기: 남은 코어 전부 연결해도 최적 갱신 불가
		if(connected + (totalCore - current) < bestConnected) {
			return;
		}
			
		// 동률에서 길이가 이미 더 길거나 같으면 컷
		if(connected == bestConnected && wireLen >= bestLength) {
			return;
		}

		// 탈출조건
		if(current==totalCore) {
			if (connected > bestConnected) {
				bestConnected = connected;
				bestLength = wireLen;
			} else if (connected == bestConnected && wireLen < bestLength) {
				bestLength = wireLen;
			}
			return;
		}
		

		int y= Y.get(current);
		int x= X.get(current);
		

		for(int i=0; i<4; i++) {
			int temp_length= check(i, y, x);
			if(temp_length>0) {
				convertVisit(i, y, x, temp_length);
				dfs(connected + 1, current+1, wireLen+temp_length);
				convertREVisit(i, y, x, temp_length);
			}
		}
		
		// 2) 연결하지 않고 넘어가기 (필수)
		dfs(connected, current + 1, wireLen);
	}

	
	static void findCore() {
		for(int y=1; y<N-1; y++) {
			for(int x=1; x<N-1; x++) {
				if(board[y][x]==1) {
					Y.add(y);
					X.add(x);
				}
			}
		}
		totalCore= Y.size();
	}
	
	static int solution() {
		
		// 맨 끝 연결된 코어를 제외하고 나머지 코어들의 위치를 저장하는 함수 호출
		findCore();
		bestConnected= -1;
		bestLength= 2147483647;
		
		// dfs 호출
		dfs(0, 0, 0);
		
		return bestLength;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			board= new int[N][N];
			visited= new int[N][N];
			totalCore= 0;
			Y= new LinkedList<>();
			X= new LinkedList<>();
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			int result= solution();
			System.out.println("#" + i + " " + result);
		}
	}

	
}
