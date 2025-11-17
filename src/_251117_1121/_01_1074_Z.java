//package _251117_1121;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
////https://ilmiodiario.tistory.com/140
//
///**
// * N을 15로 받으면 배열의 크기가 말도 안되게 커진다.
// * 즉 배열을 직접 만들어서 해당 위치를 찾아가는 방식은 아닐 것이다.(5000천만번 내 연산해야함)
// * 
// * 가장 크게 보았을 때의 z의 시작점, 해당 인덱스
// * N=1일 때 0 1 2 3 (0,0) (0,1) (1,0) (1,1) == 1(하나의 섹터의 길이)
// * N=2일 때 0 4 8 12 (0,0) (0,2) (0,2) (2,2) == 2
// * N=3일 때 0 16 32 48 (0,0) (0,4) (4,0) (4,4) == 4
// * N=4일 때 0 64 128 192 (0,0) (0,8) (8,0) (8,8)
// * 
// * 만약 N=이 15일 경우 0 268435456 536870912 805306368 1073741824 (0,0) (0,16384) (16384,0) (16384,16384)
// * 
// * 
// * N=3일 때 0 16 32 48 (0,0) (0,4) (4,0) (4,4) == 8
// * 해당 경우라고 생각해보자
// * 내가 찾고자 하는 값이(2,3 == 13) 라고 가정하자.
// * 
// * 그렇다면 나는 가장 첫 번째 섹터를 선택하게 된다.
// * 첫 번째 섹터를 선택하게 되었으므로 (0,0)을 가져오고 현재 길이의 절반을 활용해 새로운 섹터를 나눈다. (기존 섹터의 길이가 4이므로 현재 새로운 섹터의 길이는 2)
// * (0,0) (0,2) (2,0) (2,2)
// * 
// * 이번엔 내가 찾고자 하는 값이 4번째 섹터에 있으므로 새롭게 섹터를 나눈다
// * (2,2) (2,3) (3,2) (3,3) 이때 내가 원하는 (2,3) 섹터가 등장했기에 해당 값을 구하면 된다.
// * 
// * 
// *  
// * 
// * 
// */
//
//public class _01_1074_Z {
//	
//	static int N; // 2^N을 한 변으로 같는 배열 생성(1<=N<=15)
//	static int r; // r행
//	static int c; // c열
//	
//	static void getSector(int N) {
//		
//	}
//	
//	static void solution(int N, int r, int c) {
//		int sector[][]= getSector(N);
//	}
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st= new StringTokenizer(br.readLine());
//		
//		N= Integer.parseInt(st.nextToken());
//		r= Integer.parseInt(st.nextToken());
//		c= Integer.parseInt(st.nextToken());
//		
//		solution(N, r, c);
//		
//
//	}
//
//}
