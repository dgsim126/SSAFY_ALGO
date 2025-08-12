package _250807;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 위치별 누적합 구하기= (현 위치) + (위) + (왼쪽) - (북서 대각선)
 * 
 */

public class _11660 {
	
	static int N;
	static int M;
	static int[][] arr;
	
	static void cal() {
		for(int y=1; y<N+1; y++) {
			for(int x=1; x<N+1; x++) {
				arr[y][x]= arr[y][x] + arr[y][x-1] + arr[y-1][x] - arr[y-1][x-1];
			}
		}		
	}
	
	static void solution(int start_y, int start_x, int end_y, int end_x) {
		System.out.println(arr[end_y][end_x]
		           			-arr[start_y-1][end_x]
		    	            -arr[end_y][start_x-1]
		    	            +arr[start_y-1][start_x-1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		arr= new int[N+1][N+1];
		
		for(int i=1; i<N+1; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		// 누적합 배열 계산
		cal();
		
		// 범위별 결과 계산
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			int start_y= Integer.parseInt(st.nextToken());
			int start_x= Integer.parseInt(st.nextToken());
			int end_y= Integer.parseInt(st.nextToken());
			int end_x= Integer.parseInt(st.nextToken());
			solution(start_y, start_x, end_y, end_x);
		}

	}

}
