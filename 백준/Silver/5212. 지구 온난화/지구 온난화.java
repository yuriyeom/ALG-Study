import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        for(int i=0; i<r; i++){
            String line = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = line.charAt(j) == '.' ? 0 : 1;
            }
        }

        boolean[][] check = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 1) {
                    if (isDown(i, j) && !isLast(i, j)) {
                        check[i][j] = true;
                    }
                }
            }
        }

        int maxX=0; int maxY=0; int minX=r-1; int minY=c-1;
        for(int i=0; i<r; i++) {
            for (int j = 0; j < c; j++) {
                if(check[i][j]) map[i][j] = 0;
                if(map[i][j] == 1){
                    maxX = Math.max(i, maxX);
                    maxY = Math.max(j, maxY);
                    minX = Math.min(i, minX);
                    minY = Math.min(j, minY);
                }
            }
        }

        for(int i=minX; i<=maxX; i++){
            for(int j=minY; j<=maxY; j++){
                System.out.print(map[i][j] == 1 ? 'X' : '.');
            }
            System.out.println();
        }
    }

    public static boolean isDown(int i, int j){
        int cnt = 0;
        for(int d=0; d<4; d++){
            int nx = i + dx[d];
            int ny = j + dy[d];

            if(nx < 0 || nx >= r || ny < 0 || ny >= c) cnt++;
            else if(map[nx][ny] == 0) cnt++;
        }

        if(cnt >= 3) return true;
        return false;
    }

    public static boolean isLast(int i, int j){
        int cnt = 0;
        for(int x=0; x<r; x++){
            for(int y=0; y<c; y++){
                if(map[x][y] == 1) cnt++;
            }
        }

        if(cnt > 1) return false;
        return true;
    }
}
