import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* NxN map
* 4개의 방향 좌하우상. 좌,우가 될때마다 이동거리+1
* (0,0) 되면 종료
*
* */
public class Main {
    static int[][] map;
    static int N, ans;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] sand = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    // 좌하우상
    static int[][] sx = {{-1,1,-2,2,0,-1,1,-1,1},
            {-1,-1,0,0,2,0,0,1,1},
            {-1,1,-2,2,0,-1,1,-1,1},
            {1,1,0,0,-2,0,0,-1,-1}};
    static int[][] sy = {{1,1,0,0,-2,0,0,-1,-1},
            {-1,1,-2,2,0,-1,1,-1,1},
            {-1,-1,0,0,2,0,0,1,1},
            {1,-1,2,-2,0,1,-1,1,-1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = N/2;
        int y = N/2;
        int d = 0;
        int len = 1;
        ans = 0;
        while(x != 0 || y != 0){

            for(int i=1; i<=len; i++){
                if(x == 0 && y == 0) break;
                x += dx[d];
                y += dy[d];

                blow(x, y, d);

            }

            d = (d + 1) % 4;

            if(d == 0 || d == 2) len++;
        }

        System.out.println(ans);
    }

    public static void blow(int x, int y, int d){

        int sum = map[x][y]; // 알파에 보낼 값
        int origin = sum; // 원본 값
        map[x][y] = 0 ;

        for(int i=0; i<sand.length; i++){
            int nx = x + sx[d][i];
            int ny = y + sy[d][i];

            // 범위 밖으로 나가면
            if(!isInside(nx, ny)){
                ans += origin * sand[i] / 100;
                sum -= origin * sand[i] / 100 ;
                continue;
            }
            map[nx][ny] += origin * sand[i] / 100 ;
            sum -= origin * sand[i] / 100 ;
        }

        // 알파칸에 남은거
        if(d==0 && isInside(x, y-1)){
            map[x][y-1] += sum;
        }else if(d==1 && isInside(x+1, y)){
            map[x+1][y] += sum;
        }else if(d==2 && isInside(x, y+1)){
            map[x][y+1] += sum;
        }else if(d==3 && isInside(x-1, y)){
            map[x-1][y] += sum;
        }else{
            ans += sum;
        }

    }
    public static boolean isInside(int x, int y){
        if(x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
}
