import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 두 용액을 섞어서 0에 가깝게
        int N = Integer.parseInt(br.readLine());

        int[] values = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            values[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(values);

        int left = 0;
        int right = N-1;
        int ans1 = -1;
        int ans2 = -1;
        int sum = Integer.MAX_VALUE;
        while(left < right){
            int temp = values[left] + values[right];
            if(sum > Math.abs(temp)){
                sum = Math.abs(temp);
                ans1 = values[left];
                ans2 = values[right];
            }
            if(temp > 0){
                right--;
            }else{
                left++;
            }
        }

        System.out.println(ans1 + " " + ans2);
    }
}
