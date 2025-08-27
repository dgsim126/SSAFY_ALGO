package _250827;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 특별한 알고리즘이 사용되지 않는 구현 문제로 추정
 * 시뮬레이션을 돌려야 할 것 같다.
 * 
 * 다만, 여러개의 군집이 한 번에 움직이기에 움직임이 모두 완료된 후, 처분을 고려해야 한다.
 * 예를 들어 3개의 군집이 아직 안 만나지만 1시간 뒤에 만나는 경우 3개를 모두 이동시킨 후 처리해야한다.(3개의 경우 만남과 동시에 뭔가를 결정한다면 혼란 발생)
 * 다행히 N은 100이다.
 * 
 * 2차원 ArrayList를 만든다.
 * 그래서 각 군집들을 이동시킨 후, ArrayList에 넣는다.
 * 
 * 모든 이동이 끝난 후, 하나의 셀에서 이벤트가 중복되었다면 해당 좌표의 ArrayList의 길이는 1이 아니다.
 * 그런 경우 해당 ArrayList의 값들을 빼서 결론을 내린다.
 * 
 * 
 * 
 */

public class _02_2382_미생물격리_SWEA {
	
	static int N; // board의 크기
	static int M; // 시간
	static int K; // 군집의 수
	
	static Queue<int[]>[][] board; // y, x의 인덱스로 들어가면 {미생물 수, 이동방향, 변경 여부} 배열이 존재한다.
	static int dy[]= {0, -1, 1, 0, 0}; // -, 상, 하, 좌, 우
	static int dx[]= {0, 0, 0, -1, 1};
	static int flag;
	
	// 주어진 인덱스의 위치에서 하나를 빼서 주어진 방향으로 이동시킨다. 이동시킨 후 변경 여부를 반드시 변경한다! +1 하면 된다.
	static void move(int y, int x) {
		int temp[]= board[y][x].poll();
		
		int ny= y+dy[temp[1]];
		int nx= x+dx[temp[1]];
		
	    if(0<=ny && ny<N && 0<=nx && nx<N) {
	        if(ny==0 || ny==N-1 || nx==0 || nx==N-1) { // 약품에 닿은 경우
	            int half= temp[0]/2;
	            if(half>0) {
	                int ndir;
	                if(temp[1]==1) ndir= 2;       
	                else if(temp[1]==2) ndir= 1; 
	                else if(temp[1]==3) ndir= 4; 
	                else ndir= 3;                  
	                board[ny][nx].add(new int[] {half, ndir, temp[2]+1});
	            }
	        } else { // 약품에 안 닿은 경우
	            board[ny][nx].add(new int[] {temp[0], temp[1], temp[2]+1});
	        }
		}
	}
	
	static void moveAll() {
	    for(int y=0; y<N; y++) {
	        for(int x=0; x<N; x++) {
	            while(!board[y][x].isEmpty() && board[y][x].peek()[2]==flag) {
	                move(y, x);
	            }
	        }
	    }
	}

	
	// 문제가 발생한 부분의 인덱스를 가져와 정리한다.
	static void solve(int y, int x) {
		int max_direct= -1;
		int max_cnt= -1;
		
		int sum_= 0;
		int cum= -1;
		
		int sz= board[y][x].size(); 
		for(int i=0; i<sz; i++) {
			int temp[]= board[y][x].poll();
			
			if(cum==-1) {
				cum= temp[2];
			}
			
			if(max_cnt<temp[0]) {
				max_cnt= temp[0];
				max_direct= temp[1];
			}
			sum_+=temp[0];
		}
		
		board[y][x].add(new int[] {sum_, max_direct, cum});
	}
	
	// ArrayList에 여러 값이 들어가 있는 경우를 찾아 solve로 넘긴다.
	static void solveMulti() {
		for(int y=0; y<N; y++) {
			for(int x=0; x<N; x++) {
				if(board[y][x].size()>1) {
					solve(y, x);
				}
			}
		}
	}
	
	
	static int cntResult() {
	    int result= 0;
	    for(int y=0; y<N; y++) {
	        for (int x=0; x<N; x++) {
	            while (!board[y][x].isEmpty()) {    // 모두 합산
	                result+=board[y][x].poll()[0];
	            }
	        }
	    }
	    return result;
	}

	
	
	static int solution() {
		flag= 1;
		for(int i=0; i<M; i++) {
			// 모든 미생물 군집을 이동시킨다.
			moveAll();
			flag+=1;
			
			// board에서 arrayList의 값이 여러개인 경우를 해결한다.
			solveMulti();
		}
		
		
		return cntResult();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			
			board= new LinkedList[N][N];
			for(int y=0; y<N; y++) {
				for(int x=0; x<N; x++) {
					board[y][x]= new LinkedList<>();
				}
			}
			
			for(int i=0; i<K; i++) {
				st= new StringTokenizer(br.readLine());
				
				int y= Integer.parseInt(st.nextToken());
				int x= Integer.parseInt(st.nextToken());
				int cnt= Integer.parseInt(st.nextToken());
				int direction= Integer.parseInt(st.nextToken());
				
				board[y][x].add(new int[] {cnt, direction, 1});
			}
			
			int result= solution();
			System.out.println("#"+tc+" "+result);
		}

	}

}
