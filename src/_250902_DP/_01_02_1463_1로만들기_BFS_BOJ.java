package _250902_DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * bfs로 풀어야 할 것 같이 생겼다.
 * bfs에 현재 값과 depth를 넣어둔다.
 * 현재값이 1이되는 순간 해당 depth를 리턴한다.
 */



public class _01_02_1463_1로만들기_BFS_BOJ {
	
	static int solution(int X) {
		Queue<int []> queue= new LinkedList<>();
		
		queue.add(new int[] {X, 0});
		
		while(!queue.isEmpty()) {
			int temp[]= queue.poll();
			
			if(temp[0]==1) {
				return temp[1];
			}
			
			if(temp[0]%3==0) {
				queue.add(new int[] {temp[0]/3, temp[1]+1});
			}
			if(temp[0]%2==0) {
				queue.add(new int[] {temp[0]/2, temp[1]+1});
			}
			queue.add(new int[] {temp[0]-1, temp[1]+1});
		}
		
		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int X= Integer.parseInt(br.readLine());
		
		System.out.println(solution(X));
	}

}
