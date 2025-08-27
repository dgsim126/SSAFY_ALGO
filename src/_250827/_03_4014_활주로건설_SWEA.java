package _250827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. arr의 모든 가로 세로를 Queue에 저장한다.
 * queue에서 값을 하나씩 빼면서 가능한지 확인해야한다.
 * 
 * 2. 경사로 설치가 가능한지 어떻게 확인할 것인가?
 * 꺼내온 배열을 돌면서 값이 변하는 지점이 발생했다! 3 3 3 !4! 4 4 
 * 
 * - 일단 지금 당장 설치가 가능한지 확인해야한다!
 * 오르막길 : 현재 인덱스 == 이전 인덱스+1 -> 현재 인덱스 뒤 x만큼이 같은 숫자인지 확인한다(build==0인지도). 내가 설치한 위치의 build를 1로 변경한다.
 * 내리막길 : 현재 인덱스+1 == 이전 인덱스 -> 현재 인덱스부터 x만큼 앞 부분이 같은 숫자인지 확인한다. 설치한 후 설치된 위치를 build 1로 변경한다.
 * 
 */

public class _03_4014_활주로건설_SWEA {
	
	static int N;
	static int X;
	static int[][] board;
	static int[] builded;
	
	// 가능하면 1리턴, 불가능할 경우 0리턴
	static int check(int[] line) {
		builded= new int[N];
		
		// System.out.print(Arrays.toString(line) + " === ");
		
		for(int i=1; i<N; i++) {
			int current= line[i]; // 현재 i 위치에서의 값
			int before= line[i-1]; // i-1 위치에서의 값
			
			if(current==before+1) { // 오르막길
				int check_index= i-X;
				
				if(0>check_index) { // 인덱스 밖에서부터 경사로를 설치해야하기에 return 0
					return 0;
				}
				
				for(int j=check_index; j<i; j++) { // 경사로를 설치해야 하는 범위만큼이 평탄하지 않다면 
					if(line[j]!=before) {
						return 0;
					}
					
					if(builded[j]==1) { // 이미 경사로가 설치된 곳이라면
						return 0;
					}
				}
				
				for(int j=check_index; j<i; j++) { // 해당 위치는 경사로를 설치한다.
					builded[j]= 1;
				}
				
			}else if(current+1==before) {  // 내리막길
				int check_index= i+X-1;
				
				if(check_index>=N) { // 인덱스 밖이라면
					return 0;
				}
				
				for(int j=i; j<=check_index; j++) {
					if(line[j]!=current) {
						return 0;
					}
					
					if(builded[j]==1) {
						return 0;
					}
				}
				
				for(int j=i; j<=check_index; j++) {
					builded[j]= 1;
				}
				
			}else if(current!=before) {
				return 0;
			}else { // 평지
				continue;
			}
		}
		
		// System.out.print(1);
		return 1;
	}
	
	static int solution() {
		int result= 0;
		
		// System.out.println("X==" + X);
		// 가로 계산
		for(int y=0; y<N; y++) {
			result+=check(board[y]);
			// System.out.println();
		}
		
		// 세로 계산
		for(int x=0; x<N; x++) {
			int temp[]= new int[N];
			for(int y=0; y<N; y++) {
				temp[y]= board[y][x];
			}
			result+=check(temp);
			// System.out.println();
		}
		
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			X= Integer.parseInt(st.nextToken());
			
			board= new int[N][N];
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			
			System.out.println("#"+tc+" "+solution());
		}
	}

}
