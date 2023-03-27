import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[][] schedule = new int[N][2];
        int[] day = new int[367];

        for(int k=0; k<N; k++){
            schedule[k][0] = sc.nextInt();
            schedule[k][1] = sc.nextInt();

            for(int i=schedule[k][0]; i<=schedule[k][1]; i++){
                day[i] += 1;
            }
        }

        // 2 ~ 9
        // 1 1 2 3 2 2 1 1
        // 길이 * max
        int len = 0;
        int max = 0;
        int area = 0;
        for(int k=1; k<=366; k++){
            if(day[k] > 0 ){
                len++;
                max = Math.max(day[k], max);
            }else if(day[k] == 0){
                area += len * max;
                len = 0;
                max = 0;
            }
        }

        System.out.println(area);
    }
}
