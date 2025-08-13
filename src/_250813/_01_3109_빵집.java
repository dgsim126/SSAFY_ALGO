package _250813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 왼쪽에서 출발해서 오른쪽으로 이동하면 된다.
 * 이때 무조건 오른쪽 위 대각선 > 오른쪽 > 오른쪽 아래 대각선 순서로 움직여서 오른쪽 끝에 도달해야 한다.
 * 
 * 이미 갔던 곳을 다시 해지해야 하는데 어려움 dfs로 방법 변경
 * 
 * 
 */

public class _01_3109_빵집 {
	
	
	static int R; // y
	static int C; // x
	static int[][] map;
	static int[][] occupied;
	static int[] d_y= {-1, 0 ,1}; // 북동, 동, 남동
	static int result;
	
	static boolean success;
	
	static void dfs(int y, int x) {
		
		if(success==true) {
			return;
		}
		
		if(x==C-1) {
			result+=1;
			success= true;
			return;
		}
		
		for(int i=0; i<3; i++) {
			int n_y= y+d_y[i];
			int n_x= x+1;
			
			if(0<=n_y && n_y<R && 0<=n_x && n_x<C && occupied[n_y][n_x]==0 && map[n_y][n_x]!=1) {
				occupied[n_y][n_x]= 1;
				
				dfs(n_y, n_x);
				
				if(success==true) {
					return;
				}
			}
		}
	}
 	
	static void solution() {
		
		for(int y=0; y<R; y++) {
			success= false;
			dfs(y, 0);
		}
		
		
		
		// System.out.println(Arrays.deepToString(occupied));
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		R= Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		map= new int[R][C];
		occupied= new int[R][C];
		result= 0;
		
		for(int y=0; y<R; y++) {
			String line= br.readLine();
			for(int x=0; x<C; x++) {
				char temp= line.charAt(x);
				if(temp=='x') {
					map[y][x]= 1;
				}
			}
		}
		
		// System.out.println(Arrays.deepToString(map));
		solution();
	}

}
