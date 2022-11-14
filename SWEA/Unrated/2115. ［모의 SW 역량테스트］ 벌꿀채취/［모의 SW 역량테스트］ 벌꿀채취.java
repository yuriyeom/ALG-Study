import java.util.ArrayList;
import java.util.Scanner;
/*
    2명의 일꾼이 가로로 연속된 M개 벌통 채취, 겹치면 안됨

     벌통들의 크기 N=4
     선택할 수 있는 벌통의 개수 M=2
     채취할 수 있는 꿀의 최대 양 C=13 
     
    A가 먼저 (C 이상 채취할 수 있는?) M개 골라서 넘기고  B가 나머지에서 똑같이 고른다
    B는 A가 고른 곳 뒤부터 고르면 된다
    
    for 행
        for 열 0부터 N-M까지
            시작점 잡고 M개 고르기. 선택했다는 표시.
            
 */
public class Solution {

    static int N, M, C, finalans;
    static int[] ans;
    static int[][] map, honey;
    static int[][] check;
    static ArrayList<Integer> honeyList;
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int tc = sc.nextInt();
        
        for(int t=1; t<=tc; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            C = sc.nextInt();
            
            map = new int[N][N];
            check = new int[N][N];
            honeyList = new ArrayList<>();
            
            honey = new int[2][M];
            
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            
            ans = new int[2];
            finalans = 0;
            
            pickHoneyJar(0,0,0);
            
            System.out.println("#" + t + " "  + finalans);
        }
        
    }
    
    private static void pickHoneyJar(int x, int y, int idx) {
        
        if(idx == 2) {
            
            // 고른 벌통으로 채취하러 간다
            getHoney();
            
//            System.out.println(ans[0] + ans[1]);
            finalans = Math.max(finalans, ans[0]+ans[1]);
            return;
        }
        
        // A가 고른 벌통 뒤에서부터 B가 고르게 하고 싶은데 안됨 머임
        // A와 같은 행에서는 A 뒤부터
        // A 행 이후부터는 0부터
        
        for(int i=x; i<N; i++) {
            for(int j=y; j<=N-M; j++) {
//                if(check[i][j] > 0) continue;
                
//                int[] honey = new int[M];
                
                // map[i][j]가 시작점
                // M개 저장
                for(int m=0; m<M; m++) {
//                    check[i][j+m] = idx+1;
                    honey[idx][m] = map[i][j+m];
                }
                
                // 여기서 수익을 구해라
                
                pickHoneyJar(i, j+M, idx+1);
                
//                for(int m=0; m<M; m++) {
//                    check[i][j+m] = 0;
//                }
            }
            y=0;
        }
        
    }

    private static void getHoney() {
    	
    	// 현재 채취 벌통에서의 최대값이 ans[]
    	
    	ans = new int[2];
    	
        for(int n=1; n<=2; n++) { // 일꾼 돌려
            // 일꾼마다 부분집합으로 최대 벌꿀 구하기
            
            honeySubset(n-1, honey[n-1], new boolean[M], 0, 0, 0);
        }
        
    }
    
    private static void honeySubset(int an, int[] arr, boolean[] sel, int idx, int sum, int valuesum) {
    	
    	if(idx == M) {
    		ans[an] = Math.max(ans[an], valuesum);
    		return;
    	}
    	
    	if(sum + arr[idx] <= C) {
    		sel[idx] = true;
    		honeySubset(an, arr, sel, idx+1, sum + arr[idx], valuesum + arr[idx]*arr[idx]);    		
    	}
    	
    	sel[idx] = false;
    	honeySubset(an, arr, sel, idx+1, sum, valuesum);
    	
    }

}