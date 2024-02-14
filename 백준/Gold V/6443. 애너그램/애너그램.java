import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++){
            String str = br.readLine();

            int[] alphabetCnt = new int[26];
            for(int j=0; j<str.length(); j++){
                alphabetCnt[str.charAt(j) - 'a']++;
            }

            makeAnagram(str,0, alphabetCnt, new StringBuilder());
        }

    }

    public static void makeAnagram(String str, int depth, int[] alphabetCnt, StringBuilder sb){
        if(depth == str.length()){
            System.out.println(sb.toString());
            return;
        }

        for(int i=0; i<26; i++){
            if(alphabetCnt[i] > 0){
                alphabetCnt[i]--;
                sb.append((char) (i + 'a'));
                makeAnagram(str, depth+1, alphabetCnt, sb);
                alphabetCnt[i]++;
                sb.deleteCharAt(sb.length()-1);
            }
        }

    }
}
