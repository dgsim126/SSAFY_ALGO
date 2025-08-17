package _250803;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * arr2의 순서에 따른 모든 결과를 비교해야함.
 * 순열
 */
public class _05_규영이와 {
	
	static int arr1[];
	static int arr2[];
	static int is_visited[];
	static int current[];
	static int result1;
	static int result2;
	
	static void getArr2() {
		int temp[]= new int[18];
		
		for(int i=0; i<9; i++) {
			temp[arr1[i]-1]= 1;
		}
		
		int index= 0;
		for(int i=0; i<18; i++) {
			if(temp[i]==0) {
				arr2[index]= i+1;
				index+=1;
			}
		}
		
		// System.out.println(Arrays.toString(arr1));
		// System.out.println(Arrays.toString(arr2));
	}
	
	static void whoWin() {
		int a= 0;
		int b= 0;
		
		for(int i=0; i<9; i++) {
			if(arr1[i]>current[i]) {
				a+=(arr1[i]+current[i]);
			}else if(arr1[i]<current[i]) {
				b+=(arr1[i]+current[i]);
			}
		}
		
		if(a>b) {
			result1+=1;
		}else if(a<b) {
			result2+=1;
		}
		
	}
	
	static void dfs(int depth) {
		if(depth==9) {
			whoWin();
			return;
		}
		
		for(int i=0; i<9; i++) {
			if(is_visited[i]==0) {
				current[depth]= arr2[i];
				is_visited[i]= 1;
				
				dfs(depth+1);
				
				is_visited[i]= 0;
			}
		}
	}
	
	static void solution() {
		// arr2 완성
		getArr2();
		
		// arr2를 이용해 순열 만들어서 비교(dfs로 구현)
		dfs(0);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<=T; i++) {
			st= new StringTokenizer(br.readLine());
			arr1= new int[9];
			arr2= new int[9];
			is_visited= new int[9];
			current= new int[9];
			result1= 0;
			result2= 0;
			for(int j=0; j<9; j++) {
				arr1[j]= Integer.parseInt(st.nextToken());
			}
			solution();
			
			System.out.println("#" + i + " " + result1 + " " + result2);
		}
		
		

	}

}
