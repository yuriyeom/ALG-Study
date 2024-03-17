import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int[][] d = new int[str1.length()+1][str2.length()+1];

        for(int i=1; i<=str1.length(); i++){
            for(int j=1; j<=str2.length(); j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                }else{
                    d[i][j] = Math.max(d[i-1][j], d[i][j-1]);
                }

            }
        }

        System.out.println(d[str1.length()][str2.length()]);
    }
}
