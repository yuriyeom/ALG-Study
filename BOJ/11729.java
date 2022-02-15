import java.util.Scanner;

public class Main {

    static int move = 0;
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sb = new StringBuilder();
        int N = sc.nextInt();

        hanoi(1, 3, N);
        System.out.println(move);
        System.out.print(sb);
    }

    public static void hanoi(int a, int b, int n){
        move++;
        if(n == 1){
            sb.append(a + " " + b + "\n");
            return;
        }
        hanoi(a, 6-a-b, n-1);
        sb.append(a + " " + b + "\n");
        hanoi(6-a-b, b, n-1);
    }
}
