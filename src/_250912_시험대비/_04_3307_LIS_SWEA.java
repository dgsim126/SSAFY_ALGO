package _250912_시험대비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * for문을 돌면서 현재 위치를 dp[]에서 나보다 큰 값 바로 앞에서 변경하면 된다.
 */

public class _04_3307_LIS_SWEA {
	
	static int N;
	static int dp[];
	static int size;
	
	/**
	 * 두 가지 경우를 고려해야한다.
	 * 1. 나보다 큰 값이 없어서 맨 뒤에 num을 넣는 경우
	 * 2. 나보다 큰 값이 존재해서 현재 dp의 값을 변경해야하는 경우
	 * 
	 * 만약 현재 배열의 size를 지속적으로 트래킹할 수 있다면 1번의 경우는 해결 가능하다. 맨 마지막 값을 확인해서 num보다 작으면 뒤에 넣으면 되기 때문이다.
	 * 그러고 size+=1을 하면 된다.
	 * 
	 * 위의 조건에서 걸러지지 않는다면 중간에 삽입되는 경우이다. 
	 * mid값을 구해 num<mid -> start ~ mid-1
	 *            mid<num -> mid+1 ~ end
	 * start의 값이 end보다 커지면 해당 start위치에 num을 넣으면 된다.
	 * @param num
	 * @param size
	 */
	static void toFindDP(int num) {
		int start= 0;
		int end= size-1;
		int mid;
		
		// 첫 번째 값
		if(size==0) {
			dp[start]= num;
			size+=1;
			return;
		}
		
		// 맨 뒤에 값이 추가되는 경우
		if(dp[end]<num) {
			dp[end+1]= num;
			size+=1;
			return;
		}
		
		// 맨 뒤에 값이 추가되지 않는 경우
		while(start<=end) {
			mid= (start+end)/2;
			
			if(dp[mid]<num) {
				start= mid+1;
			}else {
				end= mid-1;
			}
		}
		
		dp[start]= num;
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			N= Integer.parseInt(br.readLine());
			dp= new int[N];
			size= 0;
			
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				toFindDP(Integer.parseInt(st.nextToken()));
			}
			
			System.out.println("#" + tc + " " + (size));
		}
		
		
		
	}

}
