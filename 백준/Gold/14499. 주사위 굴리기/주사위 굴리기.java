import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* 주사위는 입체적인데 굴렸을때 좌표를 어떻게 알아내지
* 1. 6개 면의 동서남북 좌표를 다 저장?
*    => 하나하나 다 저장할라했더니 굴려질때마다 동서북남이 바뀐다
* 2. 동서북남으로 굴릴때 전개도를 비교해보자
*   2
* 4 1 3
*   5
*   6
*
* <동>     <서>    <북>    <남>
*   2      2       6       1
*  136    641     423     453
*   5      5       1       6
*   4      3       5       2
*
* 방향마다 바뀌는 면이 4개씩이다. 직접 값을 옮겨준다
*
* */
public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] orders;
    static int[] dice = new int[7];
    static int[][] dir = {{0,1}, {0,-1}, {-1,0}, {1,0}}; // 동 서 북 남

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        orders = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }

        int d;
        for(int i=0; i<K; i++){
            int order = orders[i];

            // 명령의 방향 찾기
            if(order == 1){
                d = 0;
            }else if(order == 2){
                d = 1;
            }else if(order == 3){
                d = 2;
            }else{
                d = 3;
            }

            // 주사위 바닥면의 위치 이동
            x += dir[d][0];
            y += dir[d][1];

            // 범위 벗어나면 명령 무시
            if(x < 0 || x >= N || y < 0 || y >= M){
                x -= dir[d][0];
                y -= dir[d][1];
                continue;
            }

            // 주사위 굴리기
            if(d == 0){ // 동
                moveEast();
            }else if(d == 1){ // 서
                moveWest();
            }else if(d == 2){ // 북
                moveNorth();
            }else{ // 남
                moveSouth();
            }

            // 숫자 복사
            if(map[x][y] == 0){ // 주사위->map으로 복사
                map[x][y] = dice[6];
            }else{ // map->주사위로 복사
                dice[6] = map[x][y];
                map[x][y] = 0;
            }

            // 주사위 윗면 출력
            System.out.println(dice[1]);
        }

    }

    public static void moveEast(){
        int temp = dice[6];
        dice[6] = dice[4];
        dice[4] = dice[1];
        dice[1] = dice[3];
        dice[3] = temp;
    }

    public static void moveWest(){
        int temp = dice[1];
        dice[1] = dice[4];
        dice[4] = dice[6];
        dice[6] = dice[3];
        dice[3] = temp;
    }

    public static void moveNorth(){
        int temp = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[6];
        dice[6] = dice[5];
        dice[5] = temp;
    }

    public static void moveSouth(){
        int temp = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[6];
        dice[6] = dice[2];
        dice[2] = temp;
    }
}
