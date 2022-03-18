import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] t = new int[16];
        int[] p = new int[16];

        int[] d = new int[17]; // 크기 : n+1

        for (int i = 1; i <= n ; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            // i + t[i] : 수익 정산일

            if(i + t[i] <= n + 1){ // 날짜 범위 체크
                d[i + t[i]] = Math.max(d[i + t[i]], d[i]+ p[i]);
            }
            
            d[i+1] = Math.max(d[i+1], d[i]);
        }

        bw.write(String.valueOf(d[n+1]));

        br.close();
        bw.close();
    }
}
