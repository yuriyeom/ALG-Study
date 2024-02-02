import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
22:06~
3 6
anta rc tica
anta hello tica
anta car tica
* */
public class Main {
    static int N, K, ans;
    static String[] strs;
    static boolean[] chCheck;
    static Character[] basicWord = {'a', 'n', 't', 'i', 'c'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K < basicWord.length) {  // 기본 단어 수보다 K가 작으면 읽을 수 없다.
            System.out.println(0);
            return;
        }

        strs = new String[N];
        for(int i=0; i<N; i++){
            strs[i] = br.readLine().replace("anta", "").replace("tica", "");
        }

        chCheck = new boolean[26];
        for(Character ch : basicWord)
            chCheck[ch - 'a'] = true;

        ans = 0;

        pickCh(basicWord.length, 0);

        System.out.println(ans);
    }

    public static void pickCh(int k, int idx){

        // 글자 다 골랐으면 읽을 수 있는 단어 세기
        if(k == K){
            int cnt = 0;
            for(int i=0; i<N; i++){
                boolean read = true;
                for(int j=0; j<strs[i].length(); j++){
                    if(!chCheck[strs[i].charAt(j) - 'a']) {
                        read = false;
                        break;
                    }
                }
                if(read) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }

        for(int i=idx; i<26; i++){ // 알파벳 선택
            if(chCheck[i]) continue;
            chCheck[i] = true;
            pickCh(k+1, i+1);
            chCheck[i] = false;
        }
    }
}
