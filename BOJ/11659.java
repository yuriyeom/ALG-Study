import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] nums = new int[n+1];

        for(int i=1; i<=n; i++){
            nums[i] = nums[i-1] + sc.nextInt();
        }
      
        while(m-->0) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            System.out.println(nums[j] - nums[i-1]);
        }
    }
}
