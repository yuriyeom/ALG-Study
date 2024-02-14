import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,ans;
    static int[][] map;
    static int[][] shapeX = {{0,0,1},{0,0,-1},{0,-1,0},{0,0,1}};
    static int[][] shapeY = {{0,-1,0},{0,-1,0},{0,0,1},{0,1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        cntStrength(0, 0, new boolean[N][M], 0);
        System.out.println(ans);
    }

    public static void cntStrength(int x, int y, boolean[][] visited, int sum){
        // 마지막 행이면 종료
        if(x >= N){
            ans = Math.max(ans, sum);
            return;
        }

        // 마지막 열이면 다음 행으로 이동
        if(y >= M){
            cntStrength(x+1, 0, visited, sum);
            return;
        }

        // 이미 체크된 곳이면 다음 위치로 이동
        if(visited[x][y]){
            cntStrength(x, y+1, visited, sum);
            return;
        }

        // 부메랑 모양 4개 돌면서
        for(int d=0; d<4; d++){
            // 현재 위치에서 부메랑 가능한지 체크
            boolean possible = true;
            for(int k=0; k<3; k++){
                // 좌표가 범위 밖이면 불가능
                if(!isInside(x+shapeX[d][k], y+shapeY[d][k])) {
                    possible = false;
                    break;
                }
                // 이미 체크한 곳이면 불가능
                if(visited[x+shapeX[d][k]][y+shapeY[d][k]]){
                    possible = false;
                    break;
                }
            }

            // 가능하면 강도의 합 구하기
            if(possible){
                int tempSum = 0;
                for(int k=0; k<3; k++) {
                    visited[x+shapeX[d][k]][y+shapeY[d][k]] = true;

                    // 중심 위치면 2배
                    if(k == 0){
                        tempSum += map[x+shapeX[d][k]][y+shapeY[d][k]] * 2;
                    }else{
                        tempSum += map[x+shapeX[d][k]][y+shapeY[d][k]];
                    }
                }

                // 다음 위치로 이동
                cntStrength(x, y+1, visited, sum + tempSum);

                // 체크한 거 되돌리기
                for(int k=0; k<3; k++) {
                    visited[x + shapeX[d][k]][y + shapeY[d][k]] = false;
                }
            }
        }

        cntStrength(x, y+1, visited, sum);

    }

    public static boolean isInside(int x, int y){
        if(x < 0 || x >= N || y < 0 || y >= M) return false;
        return true;
    }
}
