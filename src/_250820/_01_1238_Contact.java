package _250820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Node 클래스를 하나 만들어야 할 거 같음
 * Node 클래스는 본인의 값을 하나 가질 것이고, Node들을 가진 SET, 그리고 몇 번째 방문인지 확인할 day
 * 
 * BFS 개념을 활용해야 할 것 같음
 * 첫 노드가 주어지면 그걸 노드에 넣고 해당 노드의 ArrayList에 속한 값들을 큐에 넣는다.
 * 큐의 day를 확인하여 이미 제일 오래 걸리는 day는 알고 있으니 해당 값을 가진 Node를 찾아낸다.
 */

class Node{
	Set<Integer> connected= new HashSet<>();
	int time;
}

public class _01_1238_Contact {
	
	static int N;
	static int startNode;
	static Node[] node;
	static int result;
	
	static void getTime() {
		Queue<Integer> queue= new LinkedList<>();
		
		node[startNode].time= 1;
		queue.add(startNode);
		
		int from= 0;
		while(!queue.isEmpty()) {
			from= queue.poll();
			
			for(int to : node[from].connected) {
				if(node[to].time==0) {
					node[to].time= node[from].time+1;
					queue.offer(to);
				}
			}
		}
	}
	
	static int getMostBigTime() {
		int max_= 0;
		
		for(int i=1; i<101; i++) {
			if(node[i].time>max_) {
				max_= node[i].time;
			}
		}
		
		return max_;
	}
	
	static int getMostBigNode(int max_) {
		for(int i=100; i>0; i--) {
			if(node[i].time==max_) {
				return i;
			}
		}
		
		return -1;
	}
	
	static void solution() {
		
		// 큐에 time 최신화하기
		getTime();
		
		// 가장 큰 time 구하기
		int max_= getMostBigTime();
		
		// node번호 중 가장 큰 node번호 반환
		result= getMostBigNode(max_);
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int T=1; T<11; T++) {
			st= new StringTokenizer(br.readLine());
			
			N= Integer.parseInt(st.nextToken());
			startNode= Integer.parseInt(st.nextToken());
			
			node= new Node[101];
			for(int i=0; i<101; i++) {
				node[i]= new Node();
			}
			
			st= new StringTokenizer(br.readLine());
			int from= 0;
			int to= 0;
			for(int i=0; i<N/2; i++) {
				from= Integer.parseInt(st.nextToken());
				to= Integer.parseInt(st.nextToken());
				
				node[from].connected.add(to);
			}
			
			result= 0;
			solution();
			
			System.out.println("#" + T + " " + result);
		}

	}

}
