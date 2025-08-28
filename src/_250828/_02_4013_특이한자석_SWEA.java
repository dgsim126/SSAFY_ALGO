package _250828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 자석을 회전시킬 때, 두 자석의 자성이 다를 경우 옆의 자석은 반대 방향으로 1칸 회전한다.
 * 각 자석은 8개의 자성을 가지고 있다.
 * 
 * 0번 자성: 화살표 
 * 2번 자성: 오른쪽과 연결된 자성 
 * 6번 자성: 왼쪽과 연결된 자성
 * 
 * 자석의 회전(1: 시계방향   -1: 반시계방향)
 * 
 * 자석이 선택된다. 그리고 그 자석의 방향이 선택된다.
 * 
 * 현 위치에서 주변 자석들로 넘어가며 자성이 연결되어 있는지 확인한다.(양 옆으로 뻗어가다가 자성이 일치하면 더 이상 탐색하지 않는다)
 * 연결탐색이 종료되면 회전한다.
 * 
 * 
 */

public class _02_4013_특이한자석_SWEA {
	
	static int K; // 자석 돌리는 횟수
	static int magnet[][]; // 자석 4개 ( N:0, S:1 )
	static int[] point; // 몇 번째 자석을 돌릴 것인지 순서대로 저장된 리스트
	static int[] direction; // Point에 해당하는 방향
	
	static int checkPoint() {
		int result= 0;
		
		if(magnet[1][0]==1) {
			result+=1;
		}
		if(magnet[2][0]==1) {
			result+=2;
		}
		if(magnet[3][0]==1) {
			result+=4;
		}
		if(magnet[4][0]==1) {
			result+=8;
		}
		
		return result;
	}
	
	static int convertFlag(int flag) {
		if(flag==1) {
			return -1;
		}else {
			return 1;
		}
	}
	
	/**
	 * 현재 위치를 기준으로 양 옆으로 뻗어 나간다
	 * 왼쪽의 경우 현재 magent[current][6] magnet[왼쪽][2] 두 개가 다르면 또 왼쪽으로 이동한다.
	 * 
	 * 오른쪽의 경우 현재 magnet[currnet][2] magnet[오른쪽][6] 두 개가 다르면 또 왼쪽으로 이동한다.
	 * 
	 * 처음 default를 넣어준다.
	 * 이동과 동시에 이동되는 녀석(현재 current가 아닌 point)와 방향을 함께 넣어준다.
	 * 
	 * @param default_magnet_point
	 * @param default_point_direction
	 */
	static int splash(int default_magnet_point, int default_point_direction) {
		
		Queue<int[]> queue= new LinkedList<>(); // {point, direction}
		queue.add(new int[] {default_magnet_point, default_point_direction});
		
		// 왼쪽 끝까지
		int point= default_magnet_point;
		int flag= default_point_direction; // 1 or -1
		while(1<point) {
			if(magnet[point][6]==magnet[point-1][2]) {
				break;
			}
			point-=1;
			flag= convertFlag(flag);
			queue.add(new int[] {point, flag});
		}
		
		// 오른쪽 끝까지
		point= default_magnet_point;
		flag= default_point_direction;
		while(point<4) {
			if(magnet[point][2]==magnet[point+1][6]) {
				break;
			}
			point+=1;
			flag= convertFlag(flag);
			queue.add(new int[] {point, flag});
		}
		
		// queue에서 하나씩 꺼내서 방향에 맞게 회전시킨다.
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			int index= temp[0];
			int move= temp[1];
			
			if(move==1) { // 시계 방향(맨 뒤의 값을 맨 앞에 넣어야 한다) 뒤로 민다
				int back= magnet[index][7];
				
				for(int i=7; 0<i; i--) {
					magnet[index][i]= magnet[index][i-1];
				}
				magnet[index][0]= back;
				
				
			}else { // 반시계 방향(맨 앞의 값을 맨 뒤에 넣어야 한다) 앞으로 땡긴다
				int front= magnet[index][0];
				
				for(int i=0; i<7; i++) {
					magnet[index][i]= magnet[index][i+1];
				}
				magnet[index][7]= front;
			}
		}
		
		// 결과 호출
		return checkPoint();
	}
	
	static int solution() {
		
		for(int i=0; i<K; i++) {
			int current_point= point[i];
			int current_direction= direction[i];
			
			splash(current_point, current_direction);
		}
		
		return checkPoint();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			K= Integer.parseInt(br.readLine());
			magnet= new int[5][8];
			point= new int[K];
			direction= new int[K];
			
			for(int y=1; y<5; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<8; x++) {
					magnet[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i=0; i<K; i++) {
				st= new StringTokenizer(br.readLine());
				point[i]= Integer.parseInt(st.nextToken());
				direction[i]= Integer.parseInt(st.nextToken());
			}
			
			System.out.println("#" + tc + " " + solution());
		}
		
		

	}

}
