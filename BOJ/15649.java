import java.io.*;

public class Main {

    public static boolean[] isused;
    public static int[] arr;
    public static int n, m;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        isused = new boolean[n+1];
        arr = new int[n+1];

        fun(0);

        br.close();
    }

    public static void fun(int k) throws IOException {
        if(m == k){
            for(int i=0; i<m; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }
        for(int i=1; i<=n; i++){
            if(!isused[i]){
                arr[k] = i;
                isused[i] = true;
                fun(k+1);
                isused[i] = false;
            }
        }
    }
}
