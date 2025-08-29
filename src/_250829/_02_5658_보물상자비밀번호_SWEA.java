package _250829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 이것도 별로 어렵지 않다.
 * 일단 입력받은 숫자들을 덱에다가 넣는다.
 * 
 * 1. 덱에서 N/4개씩 빼서(앞쪽에서) 해당 숫자를 set에 넣는다. 총 4개의 숫자가 들어갈 것이다.
 * - 이떄 다시 뒤에다가 넣어놓는다.
 * 
 * 2. 덱의 맨 뒤 숫자를 빼서 맨 앞에다가 넣는다.
 * 다시 1번을 반복한다.
 * 
 * 3. 2번 동작은 N/4번 반복하면 된다.(다시 원상복구 되기에)
 */

public class _02_5658_보물상자비밀번호_SWEA {
	
	static int N; // 인자의 수
	static int K; // K번째로 큰 수를 출력한다. (1번부터인 것을 명심하자)
	static Deque<Character> queue;
	static Set<Integer> set;
	
	
	// 16진수를 10진수로 변환하여 set에 저장한다.
	static void convertQueue(char[][] lst) {
		// 1. 배열에서 빼서 String으로 변환
		
		// 2. String의 길이를 활용해서 10진수로 변환
		
		// 3. set에 넣기
	}
	
	static void sliceQueue() {
		char temp[][]= new char[4][N/4];
		// 1. 4개로 나눈다. 
		for(int y=0; y<4; y++) {
			for(int x=0; x<N/4; x++) {
				temp[y][x]= queue.removeFirst();
				queue.addLast(temp[y][x]); // 원본을 수복한다.
			}
		}
		
		
		// 2. 구한 4개를 정수로 변환한다.
		convertQueue(temp);
	}
	
	static void solution() {
		for(int i=0; i<N/4; i++) { // 회전시키는 횟수
			
			// 1. 현재 덱으로 모든 경우를 다 구한다.
			sliceQueue();
			
			// 2. 다음 덱을 만들기 위해 한 칸씩 이동시킨다.
			queue.addFirst(queue.removeLast());
			
			// 3. set을 lst로 변환한 후 정렬한다.
			
			// 4. 정렬 후 K번째 값을 찾아낸다.
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T= Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<T+1; tc++) {
			st= new StringTokenizer(br.readLine());
			N= Integer.parseInt(st.nextToken());
			K= Integer.parseInt(st.nextToken());
			
			queue= new ArrayDeque<>();
			set= new HashSet<>();
			
			String temp= br.readLine();
			for(int i=0; i<N; i++) {
				queue.add(temp.charAt(i));
			}
			
			solution();
			
		}
	}

}
