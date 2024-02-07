import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, ans;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                map[i][j] = line.charAt(j) - '0';
            }
        }

        ans = 0;
        cut(0,0,new boolean[N][M]);

        System.out.println(ans);

    }

    public static void cut(int x, int y, boolean[][] visited){

        if(x >= N){ // 마지막 행까지 탐색했으면 종료
            count(visited);
            return;
        }

        if(y >= M) { // 마지막 열까지 탐색했으면 다음 행으로 이동
            cut(x+1, 0, visited);
            return;
        }
        
        // 가로는 true 세로는 false
        visited[x][y] = true;
        cut(x,y+1, visited);

        visited[x][y] = false;
        cut(x, y+1, visited);

    }

    public static void count(boolean[][] visited){
        int sum = 0;

        // 가로
        for(int i=0; i<N; i++){
            int temp = 0;
            for(int j=0; j<M; j++){
                if(visited[i][j]){ // 가로면 숫자 계산
                    temp = temp * 10 + map[i][j];
                }else{
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        // 세로
        for(int j=0; j<M; j++){
            int temp = 0;
            for(int i=0; i<N; i++){
                if(!visited[i][j]){ // 세로면 숫자 계산
                    temp = temp * 10 + map[i][j];
                }else{
                    sum += temp;
                    temp = 0;
                }
            }
            sum += temp;
        }

        ans = Math.max(ans, sum);
    }
}
