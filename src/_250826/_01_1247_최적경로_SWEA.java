package _250826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 노드를 다 방문해야 한다.
 * 그렇다면 그냥 dfs로 구현하면 되는건가?
 * 
 * 회사가 시작 지점, 집이 종료 지점이다.
 * 시작 지점에서 dfs로 다음 노드를 방문한다. 이때 다음 노드의 거리가 구해져 있다면 그것을 사용하고 구해져 있지 않다면 새롭게 추가한다.
 * 해당 거리는 2차원 배열에 저장하면 될 것 같다. y좌표는 출발노드, x좌표는 도착 노드
 * 
 * 그렇기에 dfs로 다음 노드를 방문할 때, 1번노드에서 2번노드로 이동하는 것이라면 해당 이차원 배열에 접근하여 arr[1번노드][2번노드]값이 존재하는지
 * 확인하고 있다면 바로 사용, 없다면 연산 후 arr에 해당 값을 넣어주면 된다.
 * 
 * 그렇게 모든 연산을 마무리한 후, 마지막 노드에서 집까지의 거리를 구하면 된다.
 * 그렇기에 입력을 받을 때, 집의 위치는 배열에 넣지 않고 따로 관리하는 것이 좋아 보인다.
 */

public class _01_1247_최적경로_SWEA {
	
	static int N; // 고객의 수

	static int start_y;
	static int start_x;
	static int end_y;
	static int end_x;
	
	static int[][] home; // y, x
	static int[][] weight; // y축이 이전 노드, x축이 이전 노드
	static int[] visited;
	
	static int result; // 최단거리
	
	static void dfs(int before_node, int depth, int sum_) {
		
		if(depth==N) {
			int temp= sum_ + (Math.abs(home[before_node][0]-end_y) + Math.abs(home[before_node][1]-end_x));
			
			if(temp<result) {
				result= temp;
			}
			
			return;
		}
		
		if(sum_>result) {
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]==0) { // 해당 노드에 방문한 적이 없다면
				if(before_node==-1) { // 회사에서 처음 방문한 노드라면(첫 연결인 경우)
					int temp= Math.abs(home[i][0]-start_y) + Math.abs(home[i][1]-start_x);
					visited[i]= 1;
					
					dfs(i, depth+1, sum_+temp);
					
					visited[i]= 0;
				}else { // 집과 집 관계라면
					if(weight[before_node][i]!=0) { // 이전에 계산된 적이 있다면
						visited[i]= 1;
						
						dfs(i, depth+1, sum_+weight[before_node][i]);
						
						visited[i]= 0;
					}else { // 처음 계산된다면
						int temp= Math.abs(home[i][0]-home[before_node][0]) + Math.abs(home[i][1]-home[before_node][1]);
						
						weight[before_node][i]= temp;
						
						visited[i]= 1;
						
						dfs(i, depth+1, sum_+temp);
						
						visited[i]= 0;
					}
				}
			}
		}
	}
	
	static void solution() {
		dfs(-1, 0, 0);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int tc=1; tc<T+1; tc++) {
			N= Integer.parseInt(br.readLine());
			
			home= new int[N][2];
			weight= new int[N][N];
			visited= new int[N];
			result= 2147483647;
			
			st= new StringTokenizer(br.readLine());
			start_y= Integer.parseInt(st.nextToken());
			start_x= Integer.parseInt(st.nextToken());
			end_y= Integer.parseInt(st.nextToken());
			end_x= Integer.parseInt(st.nextToken());
			
			
			for(int i=0; i<N; i++) {
				home[i][0]= Integer.parseInt(st.nextToken());
				home[i][1]= Integer.parseInt(st.nextToken());
			}
			
			solution();
			System.out.println("#" + tc + " " + result);
		}

	}

}
