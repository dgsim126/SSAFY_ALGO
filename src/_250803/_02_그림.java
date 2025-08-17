package _250803;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 전형적인 bfs문제
 * 방문여부를 확인하는 같은 사이즈의 배열을 하나 두고 문제를 완성할 것
 * 
 */

public class _02_그림 {
	
	static int n;
	static int m;
	static int board[][];
	static int is_visited[][];
	
	static int dy[]= {-1, 0, 1, 0}; // 북 동 남 서
	static int dx[]= {0, 1, 0, -1};
	
	static int cnt= 0;
	static int max_size= 0;
	
	static void solution() {
		
		Queue<Integer> queue_y= new LinkedList<>();
		Queue<Integer> queue_x= new LinkedList<>();		
		
		for(int y=0; y<n; y++) {
			for(int x=0; x<m; x++) {
				int current_size=0;
				if(board[y][x]==1 && is_visited[y][x]==0) {
					queue_y.offer(y);
					queue_x.offer(x);
					is_visited[y][x]= 1;
					cnt+=1;
					current_size+=1;
				}
				

				
				while(!(queue_y.isEmpty())) {
					int default_y= queue_y.poll();
					int default_x= queue_x.poll();
					
					for(int i=0; i<4; i++) {
						int c_y= default_y+dy[i];
						int c_x= default_x+dx[i];
						
						if(0<=c_y && c_y <n && 0<=c_x && c_x<m && board[c_y][c_x]==1 && is_visited[c_y][c_x]==0) {
							queue_y.offer(c_y);
							queue_x.offer(c_x);
							is_visited[c_y][c_x]= 1;
							current_size+=1;
						}
					}
					
					
				}
				
				if(current_size>max_size) {
					max_size= current_size;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		n= Integer.parseInt(st.nextToken());
		m= Integer.parseInt(st.nextToken());
		
		board= new int[n][m];
		is_visited= new int[n][m];
		
		for(int y=0; y<n; y++) {
			st= new StringTokenizer(br.readLine());
			for(int x=0; x<m; x++) {
				board[y][x]= Integer.parseInt(st.nextToken());
			}
		}
		
		// System.out.println(Arrays.deepToString(board));
		solution();
		
		System.out.println(cnt);
		System.out.println(max_size);
		
	}

}
