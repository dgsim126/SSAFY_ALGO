package _250903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 하나의 꿀통을 선택하면 해당 꿀통의 모든 꿀을 채취해야 한다.
 * 가로로 연속되도록 벌통을 선택할 수 있다.
 * 
 * 이걸 dp로 푸는 건 상상조차 안된다.
 * 각 줄마다 얻을 수 있는 최대를 구해보자
 * 
 * 실제 꿀통의 값이 들어있는 board, 그리고 최댓값을 저장해둘 weight배열(가중차 값, 몇 번 이동했는지 저장)을 만든다.
 * board배열에서 특정 인덱스가 주어지면 오른쪽으로 이동하며 만들 수 있는 최대값을 구해 그 합을 weight배열 인덱스에 넣어둔다.
 * 
 * weight를 돌며 가중치값이 가장 큰 두 개를 선택한다. 그리고 몇 번 이동했는지 확인해 결과를 도출한다.
 * 
 * 
 * 
 */

public class _01_2115_벌꿀채취_SWEA {
	
	static int N; // 배열 사이즈
	static int M; // 선택할 칸의 개수
	static int C; // 최대 꿀양
	
	static int[][] board;
	static int[][][] weight;
	
	static void solution() {
		
		// 1. weight 구하기
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				int cx= x;
				int sum_= 0;
				int length= 0;
				
				while(0<=cx && cx<N) {
					int temp= sum_+board[y][cx];
					if(temp<C) {
						sum_= temp;
						length+=1;
						cx+=1;
					}else {
						break;
					}
				}
				
				weight[y][x][0]= sum_;
				weight[y][x][1]= length;
			}
		}
		
		// 2. 가장 숫자가 
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T; tc++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			C= Integer.parseInt(st.nextToken());
			
			board= new int[N][N];
			weight= new int[N][N][2];
			
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			solution();
		}

	}

}
