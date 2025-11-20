package _251117_1121;

import java.util.*;
import java.io.*;

/**
 * 익은 토마토는 매일 인접한 6방향의 토마토를 익게 만든다
 * 
 * 일단 입력을 받을 때, 익은 토마토, 익지 않은 토마토, 비어있는 공간 각각의 개수를 확인한다(그래야 나중에 익은 개수 확인이 쉬워짐)
 * bfs를 활용한다.
 * 
 * for문을 돌며, 익은 토마토의 인덱스를 queue에 넣는다.
 * queue에서 값을 빼며 인접한 토마토를 익게 만든 후 큐에 넣는다(이때, 익지 않았어야하고, 비어있지 않는 경우만)
 * queue가 비었을 때, 모든 토마토가 익었는지 확인한다.
 */

public class _03_7569_토마토 {
	
	static int M; // 가로
	static int N; // 세로
	static int H; // 높이
	
	static int tomato;      // 안 익은 토마토 개수 (0)
	static int tomato_good; // 익은 토마토 개수 (1)
	
	static int[][][] box;
	static int[][][] visited;
	
	static int dm[] = {0, 0, 1, 0, -1, 0}; // 동, 서, 남, 북, 위, 아래 (순서는 상관없음)
	static int dn[] = {0, -1, 0, 1, 0, 0};
	static int dh[] = {-1, 0, 0, 0, 0, 1};
	
	static Queue<int[]> queue = new LinkedList<>();
	
	static int result = 0;
	
	static void solution() {
		
		// 처음부터 안 익은 토마토가 하나도 없으면 0일
		if (tomato == 0) {
			System.out.println(0);
			return;
		}
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int m = temp[0];
			int n = temp[1];
			int h = temp[2];
			int time = temp[3];
			
			for (int i = 0; i < 6; i++) {
				int nm = m + dm[i];
				int nn = n + dn[i];
				int nh = h + dh[i];
				
				if (0 <= nm && nm < M && 0 <= nn && nn < N && 0 <= nh && nh < H) {
					// 안 익은 토마토(0)만 익힐 수 있음
					if (box[nh][nn][nm] == 0 && visited[nh][nn][nm] == 0) {
						int ntime = time + 1;
						queue.add(new int[] {nm, nn, nh, ntime});
						
						box[nh][nn][nm] = 1;      // 익었으니 1로 변경
						visited[nh][nn][nm] = 1;  // 방문 처리
						
						tomato--;                 // 안 익은 토마토 하나 줄어듦
						result = Math.max(result, ntime);
					}
				}
			}
		}
		
		// 안 익은 토마토가 남아있지 않다면 result, 남아있다면 -1
		if (tomato == 0) {
			System.out.println(result);
		} else {
			System.out.println(-1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 가로
		N = Integer.parseInt(st.nextToken()); // 세로
		H = Integer.parseInt(st.nextToken()); // 높이
		
		tomato = 0;
		tomato_good = 0;
		
		box = new int[H][N][M];
		visited = new int[H][N][M];
		
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					int temp = Integer.parseInt(st.nextToken());
					
					if (temp == 1) {              // 익은 토마토
						tomato_good++;
						queue.add(new int[] {m, n, h, 0}); // 시간 0부터 시작
						visited[h][n][m] = 1;
					} else if (temp == 0) {       // 안 익은 토마토
						tomato++;
					}
					box[h][n][m] = temp;
				}
			}
		}
		
		solution();
	}
}
