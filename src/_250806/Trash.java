//
//package _250806;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.StringTokenizer;
//
///**
// * 양방향 그래프
// * 1. Node 클래스를 생성하고, 해당 클래스 내부에 현재 값, 연결된 Node를 저장해두는 ArrayList 생성
// * 2. 입력을 받을 때, Node가 없으면 생성
// * 3. 뒤에 따라오는 값 Node에 연결
// * 
// * 이렇게 해놓고, 각 Node를 확인하며 ArrayList의 사이즈가 가장 큰 것을 출력하면 된다!
// */
//
//class Node{
//	
//	public Node(int val) {
//		this.val= val;
//	}
//	
//	int val;
//	List<Node> nodeList= new ArrayList<>();
//}
//
//public class Trash {
//	
//	static int N;
//	static int M;
//	static Node arr[];
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st= new StringTokenizer(br.readLine());
//		
//		N= Integer.parseInt(st.nextToken());
//		M= Integer.parseInt(st.nextToken());
//		arr= new Node[N+1];
//		
//		for(int i=0; i<M; i++) {
//			st= new StringTokenizer(br.readLine());
//			int com1= Integer.parseInt(st.nextToken());
//			int com2= Integer.parseInt(st.nextToken());
//			
//			if(arr[com1]==null) {
//				arr[com1]= new Node(com1);
//			}
//			arr[com1].nodeList.add(new Node(com2));
//			
//			if(arr[com2]==null) {
//				arr[com2]= new Node(com1);
//			}
//		}
//		
//		int max_val= 0;
//		int max_net= 0;
//		
//		for(int i=0; i<arr.length; i++) {
//			if(arr[i]!=null) {
//				if(arr[i].nodeList.size()>max_net) {
//					max_val= i;
//					max_net= arr[i].nodeList.size();
//				}
//			}
//		}
//		
//		System.out.println(max_val + " " + max_net);
//		
//	}
//
//}
