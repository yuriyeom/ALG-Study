import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
* NxN에 파이어볼 M개
* 파이어볼 위치 (r,c), 질량 m, 방향은 d, 속력은 s
* 행과 열은 1부터
* 방향은 인접한 8칸
*
* 1. 모든 파이어볼이 방향 d로 속력 s칸만큼 이동, 같은 칸에 여러개 가능
* 2. 이동 후 2개 이상이라면
*   2-1. 하나로 합쳐짐
*   2-2. 4개로 나눠짐
*   2-3. 나눠진 파이어볼의 질량, 방향 속력
*       질량은 (합친 파이어볼 질량 합)/5
*       속력 (합친 파이어볼 속력 합)/(합친 파이어볼 개수)
*       합친 파이어볼의 방향이 모두 홀수or짝수이면 방향이 0,2,4,6 아니면 1,3,5,7
*       질량이 0이면 소멸
*
* K번 반복 후 남아있는 파이어볼 질량 합은?
*
* */
public class Main {
    static int N,M,K;
    static ArrayList<Fireball>[][] map;
    static ArrayList<Fireball> fireballs;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //  한 칸에 여러 개 가능하니까 2차원 list로 관리
        map = new ArrayList[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                map[i][j] = new ArrayList<Fireball>();
            }
        }

        // 현재 유효한 파이어볼 리스트
        fireballs = new ArrayList<>();
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            Fireball fireball = new Fireball(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            fireballs.add(fireball);
            map[fireball.r][fireball.c].add(fireball);
        }

        // 이동 K번
        while(K-- > 0){
            // 모든 파이어볼 이동
            moveFireball();

            // 2개 이상 파이어볼 나누기
            processFireball();
        }

        // 남아있는 파이어볼 질량 합
        int answer = 0;
        for(Fireball fireball : fireballs)
            answer += fireball.m;

        System.out.println(answer);
    }

    public static void moveFireball(){
        for(int i=0; i<fireballs.size(); i++){
            // 이동할 파이어볼
            Fireball fireball = fireballs.get(i);

            // 기존 위치에서 삭제
            map[fireball.r][fireball.c].remove(fireball);

            // 모든 파이어볼이 방향 d로 속력 s칸만큼 이동, 같은 칸에 여러개 가능
            int nx = fireball.r + dx[fireball.d] * fireball.s;
            int ny = fireball.c + dy[fireball.d] * fireball.s;

            // 1번과 N번 행,열이 연결되어있음
            if(nx < 0) {
                while(nx < 0){
                    nx += N;
                }
            }
            else if(nx >= N) {
                while(nx >= N){
                    nx -= N;
                }
            }
            if(ny < 0) {
                while(ny < 0){
                    ny += N;
                }
            }
            else if(ny >= N) {
                while(ny >= N){
                    ny -= N;
                }
            }

            // 새 위치로 이동
            fireball.r = nx;
            fireball.c = ny;

            map[nx][ny].add(fireball);
        }
    }

    public static void processFireball(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j].size() >= 2){ // 파이어볼이 2개 이상이면
                    // 새로운 질량, 속도
                    int newM = 0;
                    int newS = 0;

                    // 홀수, 짝수가 섞여있는지 판별
                    // start : 1번째 파이어볼이 짝수면 t 홀수면 f. 기준 역할
                    // end : 홀짝이 start와 같으면 t 다르면 f
                    boolean start = map[i][j].get(0).d % 2 == 0 ? true : false;
                    boolean end = true;

                    // 질량합, 속력합, 방향 홀짝 섞여있는지 구하기
                    for(Fireball fireball : map[i][j]){
                        newM += fireball.m;
                        newS += fireball.s;
                        if((start && fireball.d % 2 != 0) || (!start && fireball.d % 2 == 0)){
                            end = false;
                        }
                        fireballs.remove(fireball);
                    }

                    // 질량은 질량합/5
                    newM = newM / 5;
                    // 질량이 0이면 소멸
                    if(newM == 0) {
                        map[i][j].clear();
                        continue;
                    }

                    // 속력은 속력합/개수
                    newS = newS / map[i][j].size();

                    // 나눠졌으니 현재 위치 clear
                    map[i][j].clear();

                    // 나눠진 파이어볼 저장
                    for(int k=0; k<4; k++){
                        Fireball fireball = new Fireball(i, j, newM, newS, end ? k*2 : k*2+1);
                        map[i][j].add(fireball);
                        fireballs.add(fireball);
                    }

                }
            }
        }

    }
}

class Fireball{
    int r, c, m, s, d;

    public Fireball(int r, int c, int m, int s, int d){
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }

    @Override
    public String toString() {
        return "Fireball{" +
                "r=" + r +
                ", c=" + c +
                ", m=" + m +
                ", s=" + s +
                ", d=" + d +
                '}';
    }
}