import java.util.Scanner;

// d[i][j] : i번째 계단까지의 점수 최댓값. j개의 계단을 연속으로 밟은 상태

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] scores = new int[n+1];
        int[][] d = new int[n+1][3];

        for(int i=1; i<=n; i++){
            scores[i] = sc.nextInt();
        }
        
        if(n == 1){
            System.out.println(scores[1]);
            return;
        }

        d[1][1] = scores[1];
        d[1][2] = 0;
        d[2][1] = scores[2];
        d[2][2] = scores[1] + scores[2];
        for(int i=3; i<=n; i++){
            d[i][1] = Math.max(d[i-2][1], d[i-2][2]) + scores[i];
            d[i][2] = d[i-1][1] + scores[i];
        }
        System.out.println(Math.max(d[n][1], d[n][2]));
    }
}
