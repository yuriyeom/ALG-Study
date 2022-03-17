import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        long[] d = new long[102];
        d[1] = 1; d[2] = 1; d[3] = 1; d[4] = 2; d[5] = 2;
        for(int i=6; i<=100; i++){
            d[i] = d[i-1] + d[i-5];
        }

        while(t-- > 0){
            int n = Integer.parseInt(br.readLine());
            bw.write(d[n] + "\n");
        }

        br.close();
        bw.close();
    }
}
