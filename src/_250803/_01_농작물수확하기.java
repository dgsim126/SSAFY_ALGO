package _250803;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * y축만 염두하면 된다(무조건 홀수)
 * 즉, N/2가 첫 인덱스
 * 0까지 보냈다가 다시 N/2로 돌리면 된다.
 */

public class _01_농작물수확하기 {
	
	static int N;
	static int arr[][];
	
	static int solution() {
		int start= N/2;
		int end= N/2;
		int flag= 1;
		int result= 0;
		
		for(int i=0; i<N; i++) {
			for(int p=start; p<=end; p++) {
				result+=arr[i][p];
			}
			
			if(start==0) {
				flag= -1;
			}
			start-=flag;
			end+=flag;
		}
		
		return result;
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			N= Integer.parseInt(br.readLine());
			arr= new int[N][N];
			
			for(int j=0; j<N; j++) {
				String temp= br.readLine();
				for(int k=0; k<N; k++) {
					arr[j][k]= temp.charAt(k)-'0';
				}
			}
			
			// System.out.println(Arrays.deepToString(arr));
			int result= solution();
			System.out.println("#" + i + " " + result);
		}

	}

}
