import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] d = new int[n+1];
        int[] pre = new int[n+1];

        d[1] = 0;
        for(int i=2; i<=n; i++){
            d[i] = d[i-1] + 1;
            pre[i] = i-1;
            if(i % 3 == 0 && d[i] > d[i/3] + 1){
                d[i] = d[i/3] + 1;
                pre[i] = i/3;
            }
            if(i % 2 == 0 && d[i] > d[i/2] + 1){
                d[i] = d[i/2] + 1;
                pre[i] = i/2;
            }

        }
        System.out.println(d[n]);
        int idx = n;
        while(idx > 0){
            System.out.print(idx + " ");
            idx = pre[idx];
        }
    }
}
