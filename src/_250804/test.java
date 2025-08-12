package _250804;
import java.util.*;
import java.io.*;

public class test {
    public static int T;
    public static int N;
    public static int[][] board;
    public static int total;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        
        for(int test = 1; test < T+1; test++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            
            for(int i = 0; i < N; i++) {
                String line = br.readLine();
                for(int j = 0; j < N; j++) {
                    board[i][j] = line.charAt(j) - '0';
                }
             }
            
            int mid = (N-1)/2;
            
            total = 0;
            int index = 0;
            
            for(int i = 0; i < (N-1)/2 + 1; i++) { // 시작 ~ 중간 지점
                for(int j = mid-i; j < mid+i+1; j++) {
//                    System.out.println(board[j][index]);
                    total += board[j][index];
                }
                index++;
             }
            
            for(int i = (N-1)/2-1; i > -1; i--) { // 중간+1 ~ 끝 지점
                for(int j = mid-i; j < mid+i+1; j++) {
//                    System.out.println(board[j][index]);
                    total += board[j][index];
                }
                index++;
             }
        
            System.out.printf("#%d %d", test, total);
        }
    }

}
