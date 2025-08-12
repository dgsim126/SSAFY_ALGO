package _250806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 규영이가 내는 카드의 순서는 고정되어 있음
 * 규영이가 가지지 않은 카드를 순열로 모두 구하면 된다.
 * -> 시간단축 가능? == 나는 모르겄다..
 */

public class Solution {
	
	static int win= 0;
	static int lose= 0;
	static boolean[] is_visited= new boolean[9];
	static int[] current= new int[9];
	
	public static void whoWin(int a[], int b[]) {
		int a_score= 0;
		int b_score= 0;
		
		for(int i=0; i<9; i++) {
			if(a[i]>b[i]) {
				a_score+=(a[i]+b[i]);
			}else if(a[i]<b[i]) {
				b_score+=(a[i]+b[i]);
			}
		}
		if(a_score>b_score) {
			win+=1;
		}
		if(a_score<b_score) {
			lose+=1;
		}
	}
	
	public static int[] other(int arr[]) {
		// 카드는 1~18까지
		int temp[]= new int[18];
		int arr2[]= new int[9];
		
		for(int i=0; i<9; i++) {
			temp[arr[i]-1]= 1;
		}
		
		int index=0;
		for(int i=0; i<18; i++) {
			if(temp[i]==0) {
				arr2[index]= i+1;
				index+=1;
			}
		}
		
		return arr2;
	}
	
	// 모든 순열을 구하기 위한
	public static void dfs(int depth, int arr[], int arr2[]) {
		
		if(depth==9) {
//			for(int i=0; i<9; i++) {
//				System.out.print(current[i]+ " ");
//			}
//			System.out.println();
			
			whoWin(arr, current);
			
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(is_visited[i]==false) {
				current[depth]= arr2[i];
				is_visited[i]= true;
				
				dfs(depth+1, arr, arr2);
				
				is_visited[i]= false;
			}
		}
		
		return;
	}
	
	
	// 규영이가 이기는 경우의 수를 반환
	public static void solution(int arr[]) { 
		
		// 인영이의 arr 구하기
		int arr2[]= other(arr);
//		for(int i=0; i<9; i++) {
//			System.out.print(arr2[i]+ " ");
//		}
		
		// 인영이의 arr의 모든 순열을 구해서 whoWin에 넘기기
		dfs(0, arr, arr2);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			int arr[]= new int[9];
			for(int j=0; j<9; j++) {
				arr[j]= Integer.parseInt(st.nextToken());
			}
			solution(arr);
			
			System.out.println("#"+i+" "+win+" "+lose);
			win=0;
			lose=0;
		}

	}

}
