package _250905;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Collections;

public class _01_2383_점심식사시간_SWEA {
	
	static int N;
	static int board[][];
	static int weight[][][]; // 마지막은 각 계단 별
	static List<int[]> lst;  // 사람 좌표
	
	static int first_stair_y;
	static int first_stair_x;
	static int second_stair_y;
	static int second_stair_x;
	
	static int result;

	// dfs에서 넘겨주는 first/second 리스트의 "정체"를 구분하기 위해 참조 보관
	static List<Integer> refFirstList;
	static List<Integer> refSecondList;
	
	// 한 계단에 대한 완주 시간 계산 (arrivals: 해당 계단으로 오는 사람들의 '도착시간(이동시간)')
	// 계단 규칙(용량 3명, 계단 길이=board[y][x], 도착 후 1분 대기 진입)을 반영
	static int getTime(List<Integer> arrivals) { 
		if (arrivals.isEmpty()) return 0;

		// 어떤 계단인지 식별해서 계단 길이 얻기
		int stairLen;
		if (arrivals == refFirstList) {
			stairLen = board[first_stair_y][first_stair_x];
		} else {
			stairLen = board[second_stair_y][second_stair_x];
		}

		// 도착시간 정렬
		List<Integer> a = new ArrayList<>(arrivals);
		Collections.sort(a);

		// 현재 계단 위(최대 3명)에 있는 사람들의 "내려오는 종료시각"을 담는 min-heap
		PriorityQueue<Integer> onStair = new PriorityQueue<>();
		int lastFinish = 0;

		for (int arrive : a) {
			int enter = arrive + 1; // 입구 도착 후 1분 대기 후 진입

			// enter 시점까지 내려간 사람들 제거
			while (!onStair.isEmpty() && onStair.peek() <= enter) {
				onStair.poll();
			}

			// 자리가 없으면, 가장 먼저 내려오는 사람 시간까지 대기 후 진입
			if (onStair.size() == 3) {
				int firstDone = onStair.poll();   // 한 자리 확보
				enter = Math.max(enter, firstDone);
				// enter 시각으로 갱신되었으니, 그 시각 기준으로 다시 비울 건 없음(=firstDone > enter 였다면 이미 enter를 firstDone으로 맞춤)
			}

			int finish = enter + stairLen;
			onStair.offer(finish);
			if (finish > lastFinish) lastFinish = finish;
		}

		return lastFinish;
	}
	
	static void dfs(int depth, List<Integer> first, List<Integer> second) {
		if (depth == lst.size()) {
			// 리프: 두 계단 각각의 소요시간을 구해 최댓값으로 전체 시간 계산
			int first_result = getTime(first);
			int second_result = getTime(second);
			result = Math.min(Math.max(first_result, second_result), result);
			return;
		}
		
		// 현재 사람 좌표
		int[] p = lst.get(depth);
		int y = p[0], x = p[1];

		// 1번 계단 선택 (해당 사람의 1계단 도착시간을 first에 push)
		first.add(weight[y][x][0]);
		dfs(depth + 1, first, second);
		first.remove(first.size() - 1); // 백트래킹
		
		// 2번 계단 선택
		second.add(weight[y][x][1]);
		dfs(depth + 1, first, second);
		second.remove(second.size() - 1);
	}
	
	static void getLength() {
		for (int[] temp : lst) {
			int y = temp[0];
			int x = temp[1];
			
			int first_result = Math.abs(first_stair_y - y) + Math.abs(first_stair_x - x);
			int second_result = Math.abs(second_stair_y - y) + Math.abs(second_stair_x - x);		
			
			weight[y][x][0] = first_result;
			weight[y][x][1] = second_result;
		}
	}
	
	static void solution() {
		// 0. 미리 각 사람의 두 계단까지 이동시간을 채워둔다.
		getLength();
		
		// 1. dfs로 모든 배정 경우(2^P)를 생성
		List<Integer> first = new ArrayList<>();
		List<Integer> second = new ArrayList<>();
		refFirstList = first;   // getTime에서 어떤 계단인지 식별용
		refSecondList = second;
		
		dfs(0, first, second);
		
		// 2. result는 main에서 출력
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc < T + 1; tc++) {
			N = Integer.parseInt(br.readLine());
			int flag = 1;
			lst = new ArrayList<>();
			result = Integer.MAX_VALUE;
			
			board = new int[N][N];
			weight = new int[N][N][2];
			
			for (int y = 0; y < N; y++) {
				st = new StringTokenizer(br.readLine());
				for (int x = 0; x < N; x++) {
					board[y][x] = Integer.parseInt(st.nextToken());
					if (flag == 1 && board[y][x] != 0 && board[y][x] != 1) {
						first_stair_y = y;
						first_stair_x = x;
						flag += 1;
					} else if (flag == 2 && board[y][x] != 0 && board[y][x] != 1) {
						second_stair_y = y;
						second_stair_x = x;
						flag += 1;
					}
					if (board[y][x] == 1) {
						lst.add(new int[] { y, x }); // 사람 좌표만 저장
					}
				}
			}
			
			solution();
			System.out.println("#" + tc + " " + result);
		}
	}
}
