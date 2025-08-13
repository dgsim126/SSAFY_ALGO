package A형학습_SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 누적값이 있는지 확인한다.
 * 
 * 누적값이 없다면 다음 위치를 방문하고, bfs를 무한반복
 * 마지막에 도달했다면 첫 방문 위치에 누적값을 저장한다. 
 * 
 * 누적값이 있다면 더 이상 bfs를 반복하지 않고
 * 현재까지 방문한 개수와 누적값을 더한 후 첫 방문 위치에 더해진 값을 저장한다.
 */

public class _05_1861_정사각형방 {
	
	static int N;
	static int[][] board;
	static int[][] visited;
	static int[][] cum; // 해당 위치의 누적값을 저장해둔다.
	static int num;
	static int len_;
	
	static void solution() {
		
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				Queue<Integer[]> queue= new LinkedList<>();
				queue.offer({0, y, x}); // 여기 개발하다 멈춤!!!!!!!!!!!!!!!!
				
			}
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			board= new int[N][N];
			visited= new int[N][N];
			cum= new int[N][N];
			num= 0;
			len_= 0;
			
			for(int y=0; y<N; y++) {
				st= new StringTokenizer(br.readLine());
				
				for(int x=0; x<N; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			solution();
			
			System.out.println("#" + " " + num + " " + len_);
		}

	}

}
