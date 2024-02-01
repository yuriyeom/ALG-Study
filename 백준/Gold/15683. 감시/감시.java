import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*

<놓쳤던 것>
좌표를 이동하면서 감시하고 그걸 다시 되돌릴 때
map[x][y]=-1, map[x][y]=0
이렇게 값을 딱 넣어주게 했었다.
근데 생각해보니 되돌릴 때 무조건 0으로 넣어주면 다른 cctv에 영향받는 영역도 0이 되버린다
map[x][y]-=1, map[x][y]+=1
이렇게 1씩 더하고 빼주게 바꿨다.

* */
public class Main {
    static int[][] d = {{-1,0}, {1,0}, {0,-1},{0,1}}; // 상하좌우
    static int[][] cctv1 = {{0},{1},{2},{3}};
    static int[][] cctv2 = {{0,1}, {2,3}};
    static int[][] cctv3 = {{0,3}, {3,1}, {1,2}, {2,0}};
    static int[][] cctv4 = {{2,0,3}, {0,3,1}, {3,1,2}, {1,2,0}};
    static int[][] cctv5 = {{0,1,2,3}};
    static ArrayList<CCTV> cctvList;
    static int N, M, ans = Integer.MAX_VALUE;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvList = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        watch(0);

        System.out.println(ans);
    }

    public static void watch(int cctvIdx){ // 몇 번째 카메라인지
        // 모든 CCTV 감시 완료
        if(cctvIdx == cctvList.size()){
            ans = Math.min(ans, countArea(map));
            return;
        }

        // 감시할 CCTV
        CCTV cctv = cctvList.get(cctvIdx);

        // 그 CCTV의 방향 목록 알아내기
        int[][] dir = getCCTVDir(cctvIdx);

        // 방향 정하기
        for(int dirIdx=0; dirIdx<dir.length; dirIdx++) {

            // 감시하기
            // 넘겨받은 dirIdx의 방향대로 감시
            for (int now : dir[dirIdx]) {
                int dx = d[now][0];
                int dy = d[now][1];

                int x = cctv.x;
                int y = cctv.y;
                while (true) {
                    x = x + dx;
                    y = y + dy;
                    if (x < 0 || x >= N || y < 0 || y >= M) break;
                    if (map[x][y] == 6) { // 벽
                        break;
                    } else if (map[x][y] > 0) { // CCTV면 건너뛰기
                        continue;
                    }
                    map[x][y] -= 1;
                }
            }

            // 다음 cctv 넘기기
            watch(cctvIdx + 1);

            // 감시한거 되돌리기
            for (int now : dir[dirIdx]) {
                int dx = d[now][0];
                int dy = d[now][1];

                int x = cctv.x;
                int y = cctv.y;
                while (true) {
                    x = x + dx;
                    y = y + dy;
                    if (x < 0 || x >= N || y < 0 || y >= M) break;

                    if (map[x][y] == 6) { // 벽
                        break;
                    } else if (map[x][y] > 0) { // CCTV면 건너뛰기
                        continue;
                    }
                    map[x][y] += 1;
                }
            }
        }
    }

    // CCTV의 num으로 어떤 방향 목록을 가졌는지 구해서 리턴
    public static int[][] getCCTVDir(int cctvIdx){
        CCTV cctv = cctvList.get(cctvIdx);

        int[][] dir;
        if(cctv.num == 1){
            dir = cctv1;
        }else if(cctv.num == 2){
            dir = cctv2;
        }else if(cctv.num == 3){
            dir = cctv3;
        }else if(cctv.num == 4){
            dir = cctv4;
        }else{
            dir = cctv5;
        }

        return dir;
    }

    // 사각지대 크기 구하기
    public static int countArea(int[][] map){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(map[i][j] == 0) cnt++;
            }
        }
        return cnt;
    }
}
class CCTV{
    int x, y, num;

    public CCTV(int x, int y, int num){
        this.x = x;
        this.y = y;
        this.num = num;
    }
}