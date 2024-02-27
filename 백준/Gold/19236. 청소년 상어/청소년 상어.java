import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int ans;
    static int[] dx = {-1,-1,0,1,1,1,0,-1};
    static int[] dy = {0,-1,-1,-1,0,1,1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = a;
                fishes[a] = new Fish(i, j, a, b);
            }
        }

        // 첫번째 물고기 먹기
        int d = fishes[map[0][0]].d;
        int num = fishes[map[0][0]].num;
        fishes[map[0][0]] = null;
        map[0][0] = 0;

        ans = 0;

        moveShark(0, 0, d, num, map, fishes);

        System.out.println(ans);
    }

    public static void moveFish(int sx, int sy, int[][] map, Fish[] fishes){
        for(int i=1; i<=16; i++){
            if(fishes[i] == null) continue;

            Fish fish = fishes[i];
            for(int j=0; j<8; j++){

                int nx = fish.x + dx[fish.d];
                int ny = fish.y + dy[fish.d];

                if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4 || (nx == sx && ny == sy)){
                    fish.d = (fish.d + 1) % 8;
                    continue;
                }

                // 이동
                if(map[nx][ny] != 0){ // 다음 칸에 물고기가 있으면
                    Fish from = fish;
                    Fish to = fishes[map[nx][ny]];

                    map[fish.x][fish.y] = to.num;
                    map[nx][ny] = from.num;

                    fishes[from.num] = new Fish(to.x, to.y, from.num, from.d);
                    fishes[to.num] = new Fish(from.x, from.y, to.num, to.d);

                }else{ // 다음 칸에 물고기가 없으면
                    map[nx][ny] = map[fish.x][fish.y];
                    map[fish.x][fish.y] = 0;

                    fish.x = nx;
                    fish.y = ny;
                }
                break;
            }
        }
    }

    public static void moveShark(int sx, int sy, int sd, int sum, int[][] map, Fish[] fishes){

        ans = Math.max(ans, sum);

        moveFish(sx, sy, map, fishes);

        for(int i=1; i<=3; i++){

            int nx = sx + dx[sd] * i;
            int ny = sy + dy[sd] * i;

            if(nx < 0 || nx >= 4 || ny < 0 || ny >= 4) return;
            if(map[nx][ny] == 0) continue;

            // 배열 복사
            int[][] tempMap = new int[4][4];
            for(int k=0; k<4; k++){
                for(int j=0; j<4; j++){
                    tempMap[k][j] = map[k][j];
                }
            }
            Fish[] tempFishes = new Fish[17];
            for(int k=0; k<17; k++){
                tempFishes[k] = fishes[k] == null ? null : new Fish(fishes[k].x, fishes[k].y, fishes[k].num, fishes[k].d);
            }

            int num = tempMap[nx][ny];
            int d = tempFishes[tempMap[nx][ny]].d;
            tempFishes[num] = null;
            tempMap[nx][ny] = 0;

            moveShark(nx, ny, d, sum + num, tempMap, tempFishes);
        }

    }

    static class Fish{
        int x, y, num, d;

        public Fish(int x, int y, int num, int d){
            this.x = x;
            this.y = y;
            this.num = num;
            this.d = d;
        }

        public String toString(){
            return x + " " + y + " " + num + " " + d;
        }
    }
}
