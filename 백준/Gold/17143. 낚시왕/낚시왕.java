import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, M, ans;
    static List<Shark> sharks;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if(M == 0) {
            System.out.println(0);
            return;
        }

        sharks = new ArrayList<>();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            Shark shark = new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken()));
            sharks.add(shark);
        }

        ans = 0;

        // 낚시왕 위치
        int x = 0, y = 0;
        while(y < C){
            // 낚시왕 오른쪽으로 한 칸 이동
            y++;

            // 낚시왕 열에서 행이 가장 작은 상어 잡기
            Collections.sort(sharks);
            for(Shark shark : sharks){
                // r,c로 정렬했으니 같은 열에서 처음 나오는 상어 잡기
                if(shark.c == y){
                    sharks.remove(shark);
                    ans += shark.z;
                    break;
                }

                // 잡을 상어가 없어서 다음 열로 넘어갔으면 break
                if(shark.c > y) {
                    break;
                }
            }

            // 상어 없으면 종료
            if(sharks.isEmpty()) break;

            // 상어 이동
            move();

            // 한 칸에 강한 상어만 살아남기
            survive();
        }

        System.out.println(ans);

    }
    public static void move(){
        int size = sharks.size();
        ArrayList<Shark> temp = new ArrayList<>();
        for(int i=0; i<size; i++){
            Shark shark = sharks.remove(0);
            int nr = shark.r;
            int nc = shark.c;
            for(int k=1; k<=shark.s; k++){
                nr += dx[shark.d];
                nc += dy[shark.d];

                // 범위 넘어가면 튕기고 방향 변경
                if(nr <= 0){
                    nr = 2;
                    shark.d = 1;
                }else if(nr > R){
                    nr = R-1;
                    shark.d = 0;
                }else if(nc <= 0){
                    nc = 2;
                    shark.d = 2;
                }else if(nc > C){
                    nc = C-1;
                    shark.d = 3;
                }

            }
            shark.r = nr;
            shark.c = nc;
            temp.add(shark);
        }
        sharks = temp;
    }

    public static void survive(){
        Shark[][] survive = new Shark[R+1][C+1];
        ArrayList<Shark> dead = new ArrayList<>();
        for(Shark shark : sharks){
            if(survive[shark.r][shark.c] == null){
                survive[shark.r][shark.c] = shark;
            }else{
                if(survive[shark.r][shark.c].z < shark.z){
                    dead.add(survive[shark.r][shark.c]);
                    survive[shark.r][shark.c] = shark;
                }else{
                    dead.add(shark);
                }
            }
        }

        for(Shark shark : dead){
            sharks.remove(shark);
        }
    }
}

class Shark implements Comparable<Shark>{
    int r, c, s, d, z; // 위치(r,c), 속력, 방향, 크기

    public Shark(int r, int c, int s, int d, int z){
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }

    public int compareTo(Shark s){
        if(this.c == s.c){
            return Integer.compare(this.r, s.r);
        }
        return Integer.compare(this.c, s.c);
    }

    public String toString(){
        return "(" + this.r + "," + this.c + ") " + this.s + " " + this.d + " " + this.z;
    }
}
