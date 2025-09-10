package _250910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 물고기 M마리와 아기상어 한 마리가 존재
 * 아기상어 처음 크기는 2, 1초에 한 칸 이동
 * 자기보다 큰 물고기 칸 이동 불가, 나머지 칸 이동 가능
 * 아기상어는 본인 사이즈보다 작은 물고기 섭취 가능
 * 
 * 다음 이동 결정
 * 1. 더 이상 먹을 수 있는 물고기가 없다면, 엄마 상어 (게임 종료)
 * 2. 먹을 수 있는 물고기가 1마리인 경우, 해당 위치로 이동
 * 3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 이동
 * 
 * - 이동과 동시에 물고기를 먹을 수 있다.
 * - 본인 크기만큼의 물고기를 먹을 때마다 사이즈가 1 커진다. 
 * 
 * 1. 현 위치에서 나보다 작은 물고기가 있는지
 * 2. 작은 물고기가 여러 마리라면 가장 가까운 물고기가 어디인지
 * 3. 해당 물고기까지 이동할 수 있는지
 * 
 * === 설계 1
 * 1. 우선순위큐에 (물고기 사이즈, y, x)를 넣어둔다.
 * 2. 우선순위큐의 가장 앞의 물고기 사이즈를 확인하고 나의 사이즈와 비교한다.(이때, 맨 앞의 물고기 크기가 아기상어보다 크다면 종료)
 * 3. 먹을 수 있는 물고기 전체를 우선순위큐에서 빼서 tempList에 저장한다. (아기상어와의 거리(default=0), 물고기 사이즈, y, x),, tempList도 우선순위큐
 * 4. tempList에서 하나 빼서 해당 위치까지 이동할 수 있는지 확인한다.
 * 4-1. 이동할 수 없다면 temp2List에 넣는다.
 * 4-2. 이동할 수 있다면 현재 빼낸 값을 제거하고, temp2List와 tempList에 남은 값들 전부를 다시 우선순위큐에 넣는다.
 * 5. 해당 작업을 반복한다.
 * 
 * 그렇다면 현재 아기 상어의 위치에서 특정 위치까지 장애물 없이 도달할 수 있는지는 어떻게 판단할 것인가?
 * 그냥 bfs로 전체를 탐색해야하나? 그러하다 방법이 없다 다행히 20이 공간의 최대이기에 여기서 완전탐색이 발생해도 무리가 없다.
 * 
 * -> 어차피 bfs쓸거면 먼저쓰는게 낫지 않나??
 * 
 * 
 * === 설계 2 (이걸로 구현!)
 * 1. 현 아기 상어 위치에서 bfs를 활용해 먹을 수 있는(아기상어보다 작고, 도달 가능한) 물고기를 모두 찾는다.
 * 2. 우선순위큐에 (상어와 물고기 거리, y, x, 물고기 사이즈)를 넣는다. 
 * 3-1. 우선순위큐에 아무 값이 없다면 return.
 * 3-2. 우선순위큐의 맨 앞의 값들을 이용하여 상어의 위치를 y,x로 이동시키고 물고기 배채우기(물고기 먹은 개수만큼 배 채우기)를 실행한다.
 * 4. 물고기 배채우기와 현재 상어 사이즈가 같아졌다면 배채우기를 0으로 바꾸고, 상어 크기를 +1한다.
 * 
 * 
 */

public class _02_16236_아기상어_BOJ {
	
	static int board[][];
	static int result;
	
	static int[] dy= {-1, 0, 1, 0}; // 북 동 남 서
	static int[] dx= {0, 1, 0, -1};
	
	static PriorityQueue<int[]> queue; // {상어와 물고기 거리, y, x}
	static PriorityQueue<int[]> can_eat_queue; // {y, x}
	
	static int solution() {
		int shark_size= 2;
		int shark_hungry= 0;
		int fish= 0;
		
		while(true) {
			queue= new PriorityQueue<>((a, b)-> Integer.compare(a[0], b[0]));
			can_eat_queue= new PriorityQueue<>((a, b)-> {
				int compare= Integer.compare(a[0], b[0]);
				
				if(compare==0) {
					return Integer.compare(a[1], b[1]);
				}
				
				return compare;
			});
			
			// 1. 우선순위큐에 현 아기상어가 도달할 수 있는 모든 물고기를 저장한다.
			toFindAllFishes();
			
			// 2. 우선순위큐가 비어있는지 확인한다.
			int queue_size= queue.size();
			if(queue_size==0) {
				return fish;
			}
			
			// 3. 우선순위큐에서 맨 앞 물고기를 뺸다.
			int first_fish[]= queue.poll();
			can_eat_queue.add(new int[] {first_fish[1], first_fish[2]});
			
			int next_shark_y= first_fish[1];
			int next_shark_x= first_fish[2];
			
			
			if(queue_size>1) {
				while(!queue.isEmpty() && first_fish[0]==queue.peek()[0]) { // 거리가 같은 물고기가 있다면
					int second_fish[]= queue.poll();
					can_eat_queue.add(new int[] {second_fish[1], second_fish[2]});
				}
				
				
			}
			
			
			
		}
		
		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N= Integer.parseInt(br.readLine());
		board= new int[N][N];
		result= 0;
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				board[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		solution();

	}

}
