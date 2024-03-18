import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }

        int[] d = new int[N];

        for(int i=0; i<N; i++){
            d[i] = 1;
            for(int j=0; j<=i; j++){
                if(num[j] < num[i])
                    d[i] = Math.max(d[i], d[j] + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        int max = 0;
        for(int i=0; i<N; i++)
            max = Math.max(d[i], max);

        System.out.println(max);

        int[] ans = new int[max];
        for(int i=N-1; i>=0; i--){
            if(d[i] == max){
                ans[max-1] = num[i];
                max--;
            }
        }

        for (int a : ans)
            System.out.print(a + " ");
    }
}
