import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        n += 1000000;

        int[] d = new int[2000001];
        d[1000001] = 1;
        for(int i=1000002; i<=2000000; i++){
            d[i] = (d[i-1] + d[i-2]) % 1000000000;
        }

        for(int i=1000002; i>=2; i--){
            d[i-2] = (d[i] - d[i-1]) % 1000000000;
        }

        //-1 1 0 1 1 2
        if(d[n] > 0){
            System.out.println(1);
        }else if(d[n] == 0){
            System.out.println(0);
        }else{
            System.out.println(-1);
        }

        System.out.println(Math.abs(d[n]) % 1000000000);
    }
}
