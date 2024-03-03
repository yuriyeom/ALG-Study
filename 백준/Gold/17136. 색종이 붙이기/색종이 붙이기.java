import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[10][10];

        StringTokenizer st;
        for(int i=0; i<10; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<10; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] papers = {0, 5, 5, 5, 5, 5};

        ans = Integer.MAX_VALUE;

        attach(0, 0, map, papers, 0);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static void attach(int x, int y, int[][] map, int[] papers, int sum){

        if(x >= 10){
            if(isFinish(map)){
                ans = Math.min(ans, sum);
            }
            return;
        }

        if(y >= 10){
            attach(x+1, 0, map, papers, sum);
            return;
        }

        if(map[x][y] == 1){
            // 종이별로 붙이기
            for(int i=5; i>=1; i--){
                if(x+i > 10 || y+i > 10) continue;

                if(papers[i] > 0 && possible(x, y, i, map)){
                    // 붙이기
                    for(int p=0; p<i; p++){
                        for(int q=0; q<i; q++){
                            map[x+p][y+q] = 0;
                        }
                    }
                    papers[i]--;

                    attach(x, y+i, map, papers, sum+1);

                    // 되돌리기
                    for(int p=0; p<i; p++){
                        for(int q=0; q<i; q++){
                            map[x+p][y+q] = 1;
                        }
                    }
                    papers[i]++;
                }
            }
        }else{
            attach(x, y+1, map, papers, sum);
        }

    }

    public static boolean isFinish(int[][] m){
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(m[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static boolean possible(int x, int y, int paperIdx, int[][] map){
        for(int i=0; i<paperIdx; i++){
            for(int j=0; j<paperIdx; j++){
                if(map[x+i][y+j] == 0) return false;
            }
        }
        return true;
    }
}
