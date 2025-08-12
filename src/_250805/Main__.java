package _250805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node{
	char value;
	Node right_child;
	Node left_child;
	
	public Node(char value) {
		this.value= value;
	}
}

public class Main__ {
	
	static Node[] tree= new Node[27];
	
	public static void preOrder(Node node) {
		if(node==null) {
			return;
		}
		System.out.print(node.value);
		preOrder(node.left_child);
		preOrder(node.right_child);
		
	}
	
	public static void inOrder(Node node) {
		if(node==null) {
			return;
		}
		inOrder(node.left_child);
		System.out.print(node.value);
		inOrder(node.right_child);
		
	}
	
	public static void postOrder(Node node) {
		if(node==null) {
			return;
		}
		postOrder(node.left_child);
		postOrder(node.right_child);
		System.out.print(node.value);
	}
	
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int times= Integer.parseInt(br.readLine());
		
		for(int i=0; i<times; i++) {
			st= new StringTokenizer(br.readLine());
			char me= st.nextToken().charAt(0);
			char left= st.nextToken().charAt(0);
			char right= st.nextToken().charAt(0);
			
			if(tree[me-'A']==null) { // 내가 처음인지
				tree[me-'A']= new Node(me);
			}
			
			if(left!='.') { // 왼쪽 자식이 존재하는 경우
				tree[left-'A']= new Node(left);
				tree[me-'A'].left_child= tree[left-'A'];
			}
			
			if(right!='.') { // 오른쪽 자식이 존재하는 경우
				tree[right-'A']= new Node(right);
				tree[me-'A'].right_child= tree[right-'A'];
			}
			
		}
		// 전위 순회
		preOrder(tree[0]);
		System.out.println();
		// 중위 순회
		inOrder(tree[0]);
		System.out.println();
		// 후위 순회 
		postOrder(tree[0]);
		System.out.println();

	}

}
