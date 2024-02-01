import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] a = new int[n];
        for(int i=0; i<n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(a);

        int x = Integer.parseInt(br.readLine());

        int left = 0;
        int right = n-1;
        int ans = 0;
        while(left < right){
            int sum = a[left] + a[right];
            if(sum == x) ans++;
            if(sum > x){
                right--;
            }else {
                left++;
            }
        }
        System.out.println(ans);
    }
}
