import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        System.out.print(modular(A, B, C));
    }

    public static long modular(int a, int b, int c){
        if(b == 1) return a % c;

        long temp = modular(a, b/2, c);
        temp = temp * temp % c;

        if(b % 2 == 1) return temp * a % c;
        else return temp;
    }
}
