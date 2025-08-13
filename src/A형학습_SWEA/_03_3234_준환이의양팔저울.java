package A형학습_SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 왼쪽 총합 > 오른쪽 총합
 * 
 * 조합에 넣는 경우를 왼쪽에 넣는 경우라고 생각해보자
 * 
 * N개의 모든 무게를 더한 총합을 저장해두고, 조합으로 뽑은 값들의 합을 계속 구한다.
 * 이때 (총합-조합에포함된합)을 이용해 반대쪽 무게추를 구하고 이를 통해 판단
 * 
 * -- 수정
 * 일단 조합을 구한 후
 * 그 조합으로 만들 수 있는 경우를 구하는게 좋을 듯
 * 예를 들어 left=1, 2 right= 3이라고 가정할 경우
 * - N개의 칸 중 cnt만큼 자리 점유 = N C cnt
 * - cnt들을 이용해 순열을 만들기 = cnt!
 * 
 * -- 수정
 * 매 순간 단 한 번이라도 오른쪽이 커지면 안된다.
 * 즉, 위에 방식 모두 기각
 * 그냥 두개 만들고 하나씩 다 넣어봐야할 듯
 * 안풀래
 * 
 */

public class _03_3234_준환이의양팔저울 {
	
	static int N;
	static int[] arr;
	static int total_sum;
	static int cal;
	
	static int nCr(int n, int r) {
		int result= 1;
		
		if(r==0 || r==n) {
			return 1;
		}
		
		r= Math.min(n-r, r);
		
		for(int i=1; i<r+1; i++) {
			result= result*(n-r+i)/i;
		}
		
		return result;
	}
	
	static int facto(int n) {
		int result= 1;
		
		for(int i=n; i>0; i--) {
			result*=i;
		}
		
		return result;
	}
	
	static void dfs(int index, int sum_, int cnt) {
		
		int left= sum_;
		int right= total_sum-sum_;
		
		if(left>=right) {
			
			
			cal+=(nCr(N, cnt)*facto(cnt));
		}
		
		if(index==N) {
			return;
		}
		
		for(int i=index; i<N; i++) {

			dfs(i+1, sum_+arr[i], cnt+1);
				
			
		}
	}
	
	static int solution() {
		
		dfs(0, 0, 0);
		
		return cal;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			arr= new int[N];
			cal= 0;
			total_sum= 0;
			st= new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				arr[j]= Integer.parseInt(st.nextToken());
				total_sum+=arr[j];
			}
			
			int result= solution();
			
			System.out.println("#" + i + " " + result);
			
		}

	}

}
