package _250803;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 조합을 다 구해보자
 * 하나 이상 포함된 조합을 모두 구하면 된다.
 */

public class _08_도영이 {
	
	static int N;
	static int arr[][];
	static int diff_min= 2147483647;
	
	static void checkDiff(int s, int b) {
		int temp= Math.max(s, b) - Math.min(s, b);
		diff_min= Math.min(temp, diff_min);
	}
	
	static void dfs(int index, int sumS, int sumB) {
		
		if(index>0) {
			checkDiff(sumS, sumB);
		}
		
		if(index==N) {
			return;
		}
		
		for(int i=index; i<N; i++) {
			int c_S= arr[i][0];
			int c_B= arr[i][1];
			
			dfs(i+1, sumS*c_S, sumB+c_B);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N= Integer.parseInt(br.readLine());
		arr= new int[N][2];
		
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			arr[i][0]= Integer.parseInt(st.nextToken());
			arr[i][1]= Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 1, 0);
		
		System.out.println(diff_min);
	}

}
