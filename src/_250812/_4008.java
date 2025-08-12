package _250812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * dfs로 oper의 앞부터 돌면서 값이 있다면 적용한다.
 */

public class _4008 {
	
	static int N;
	static int[] oper;
	static int[] numbers;
	static int max_;
	static int min_;
	
	static void dfs(int depth, int current) {
		
		if(depth==N-1) {
			max_= Math.max(max_, current);
			min_= Math.min(min_, current);
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i]>0) {
				oper[i]-=1;
				if(i==0) {
					dfs(depth+1, current+numbers[depth+1]);
				}else if(i==1) {
					dfs(depth+1, current-numbers[depth+1]);
				}else if(i==2) {
					dfs(depth+1, current*numbers[depth+1]);
				}else {
					dfs(depth+1, current/numbers[depth+1]);
				}
				oper[i]+=1;
			}
		}
	}
	
	static int solution() {
		dfs(0, numbers[0]);
		
		return max_- min_;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			oper= new int[4];
			numbers= new int[N];
			max_= -2147483648;
			min_= 2147483647;
			
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<4; j++) {
				oper[j]= Integer.parseInt(st.nextToken());
			}
			
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				numbers[j]= Integer.parseInt(st.nextToken());
			}
			
			int result= solution();
			
			System.out.println("#" + i + " " + result);
		}

	}

}
