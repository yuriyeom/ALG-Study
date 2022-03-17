import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n+1];
        int[] d = new int[n+1];

        for (int i = 1; i <= n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            d[i] = arr[i];
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=i; j++){
                if(arr[j] < arr[i]){
                    d[i] = Math.max(d[i], d[j] + arr[i]);
                }
            }
        }

        int max = d[1];
        for(int tmp : d){
            if(max < tmp) max = tmp;
        }
        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}
