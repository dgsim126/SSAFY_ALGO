package _250812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * check() == 보호필름 통과 여부 확인
 * 
 * 아 몰라 그냥 무지성 dfs
 */

public class _2112 {
	
	static int D; // y
	static int W; // x
	static int K;
	static int board[][];
	static int max_depth;
	
	static int check() {
		for(int x=0; x<W; x++) {
	        int sameCount= 1; 
	        boolean isPass= false;

	        for (int y=1; y<D; y++) {
	            if (board[y][x]==board[y-1][x]) {
	                sameCount++;
	            } else {
	                sameCount= 1;
	            }

	            if(sameCount>=K) {
	                isPass= true;
	                break;
	            }
	        }


	        if(!isPass) {
	        	return 0;
	        }
	    }

	    return 1; 
	}
	
	static void dfs(int index, int depth) {
		
		if(depth>max_depth) {
			return;
		}
		
		if(check()==1) {
			max_depth= depth;
			return;
		}
		
		for(int y=index; y<D; y++) {
			int temp[]= new int[W];
			for(int x=0; x<W; x++) {
				temp[x]= board[y][x];
			}
			
			// 변경 1
			for(int x=0; x<W; x++) {
				board[y][x]= 1;
			}
			
			dfs(y+1, depth+1);
			
			// 변경 2
			for(int x=0; x<W; x++) {
				board[y][x]= 0;
			}
			
			dfs(y+1, depth+1);
			
			// 원상복귀
			for(int x=0; x<W; x++) {
				board[y][x]= temp[x];
			}
			
		}
		
	}
	
	static void solution() {
		
		dfs(0, 0);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int i=1; i<T+1; i++) {
			st= new StringTokenizer(br.readLine());
			D= Integer.parseInt(st.nextToken());
			W= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			max_depth= 2147483647;
			
			board= new int[D][W];
			
			for(int y=0; y<D; y++) {
				st= new StringTokenizer(br.readLine());
				for(int x=0; x<W; x++) {
					board[y][x]= Integer.parseInt(st.nextToken());
				}
			}
			
			if(K==1) {
				max_depth= 0;
			}else {
				if(check()!=1) {
					solution();
				}else {
					max_depth= 0;
				}
			}
			
			
			System.out.println("#" + i + " " + max_depth);
			
		}
		
		
		
			

	}

}
