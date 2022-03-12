import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[][] cost = new int[n+1][4];
        int[][] d = new int[n+1][4];

        for(int i=1; i<=n; i++){
            cost[i][1] = sc.nextInt();
            cost[i][2] = sc.nextInt();
            cost[i][3] = sc.nextInt();
        }

        d[1][1] = cost[1][1]; d[1][2] = cost[1][2]; d[1][3] = cost[1][3];
        for(int i=2; i<=n; i++){
            d[i][1] = Math.min(d[i-1][2], d[i-1][3]) + cost[i][1];
            d[i][2] = Math.min(d[i-1][1], d[i-1][3]) + cost[i][2];
            d[i][3] = Math.min(d[i-1][1], d[i-1][2]) + cost[i][3];
        }
        System.out.println(Math.min(Math.min(d[n][1], d[n][2]), d[n][3]));
    }
}
