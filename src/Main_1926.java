import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * BFS
 * 같은 사이즈 배열의 is_visited를 두고 방문 시 1로 바꿀 것
 * 배열을 돌다가 1을 마주치면 bfs 시작 
 */
public class Main_1926 {
	
	public static int dy[]= {-1, 0, 1, 0}; // 북 동 남 서
	public static int dx[]= {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int y_size= Integer.parseInt(st.nextToken());
		int x_size= Integer.parseInt(st.nextToken());
		
		int board[][]= new int[y_size][x_size];
		int is_visted[][]= new int[y_size][x_size];
		int count= 0;
		int most_biggest= 0;
		int size_temp = 0;
		
		for(int y = 0; y < y_size; y++) {
		    st = new StringTokenizer(br.readLine());
		    for(int x = 0; x < x_size; x++) {
		        board[y][x] = Integer.parseInt(st.nextToken());
		    }
		}

		List<Integer[]> queue= new ArrayList<>();
		
		for(int y=0; y<y_size; y++) {
			for(int x=0; x<x_size; x++) {
				if(board[y][x]==1 && is_visted[y][x]==0) {
					queue.add(new Integer[]{y,x});
					is_visted[y][x]= 1;
					count+=1;
					size_temp= 1;
					
					while(!queue.isEmpty()) {
						Integer[] temp= queue.remove(0);
						for(int i=0; i<4; i++) {
							int n_y= temp[0]+dy[i];
							int n_x= temp[1]+dx[i];
							
							if(n_y>=0 && n_y<y_size && n_x>=0 && n_x<x_size && board[n_y][n_x]==1 && is_visted[n_y][n_x]==0) {
								queue.add(new Integer[] {n_y, n_x});
								is_visted[n_y][n_x]= 1;
								size_temp+=1;
							}
						}
					}
					if(size_temp>most_biggest) {
						most_biggest= size_temp;
					
					}
				}
			}
		}
		System.out.println(count);
		System.out.println(most_biggest);
	}
}
