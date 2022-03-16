import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[][] fib = new int[41][2];

        fib[0][0] = 1; fib[1][1] = 1;
        for(int i=2; i<=40; i++){
            fib[i][0] = fib[i-1][0] + fib[i-2][0];
            fib[i][1] = fib[i-1][1] + fib[i-2][1];
        }

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            bw.write(fib[n][0] + " " + fib[n][1] + "\n");
        }
        br.close();
        bw.close();
    }
}
