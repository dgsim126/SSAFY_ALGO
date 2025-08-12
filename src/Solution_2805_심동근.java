import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 */
public class Solution_2805_심동근 {
	
	public static int check(int N, int[][] arr) {
		int result= 0;
		int start= N/2;
		int end= N/2;
		
		for(int y=0; y<N/2+1; y++) {
			for(int x=start; x<end+1; x++) {
				result+=arr[y][x];
				// System.out.println(y+" "+x);
			}
			start-=1;
			end+=1;
		}
		
		start+=2;
		end-=2;
		
		for(int y=N/2+1; y<N; y++) {
			for(int x=start; x<end+1; x++) { 
				result+=arr[y][x];
				// System.out.println(y+" "+x);
			}
			start+=1;
			end-=1;
		}
		
		return result;
	}

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int test_case= Integer.parseInt(br.readLine());
		
		for(int t=1; t<test_case+1; t++) {
			int N= Integer.parseInt(br.readLine());
			int arr[][]= new int[N][N];
			
			for(int i=0; i<N; i++) {
				String temp= br.readLine();
				for(int j=0; j<N; j++) {
					arr[i][j]= temp.charAt(j)-'0';
				}
			}
			
			System.out.println("#" + t + " " + check(N, arr));
			
			
		}

	}

}
