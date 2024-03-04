import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int[][] blue, green;
    static int score = 0, tile = 0;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        blue = new int[4][6];
        green = new int[6][4];

        while(N-- > 0){
//            System.out.println("@@@@@@@@@@@@@@ " + (N+1));
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // blue, green으로 블록 이동
            moveBlock(t, x, y);

            // blue, green 열,행 터진다 (없을때까지)
            bombLine();

            // blue 0,1열 체크, green 0,1행 체크
            checkZeroAndOne();

        }

        for(int i=0; i<4; i++){
            for(int j=0; j<6; j++){
                if(blue[i][j] == 1) tile++;
            }
        }
        for(int i=0; i<6; i++){
            for(int j=0; j<4; j++){
                if(green[i][j] == 1) tile++;
            }
        }

        System.out.println(score);
        System.out.println(tile);
    }

    public static void moveBlock(int t, int x, int y){
        int col = 0;
        if (t == 1) {
            // 1x1
            for (int j = 0; j < 6; j++) {
                if(blue[x][j] != 0) break;
                col = j;
            }
            blue[x][col] = 1;
        } else if (t == 2) {
            // 1x2
            for (int j = 1; j < 6; j++) {
                if (blue[x][j - 1] != 0 || blue[x][j] != 0) break;
                col = j;
            }
            blue[x][col - 1] = 1;
            blue[x][col] = 1;
        } else {
            // 2x1
            for (int j = 0; j < 6; j++) {
                if(blue[x][j] != 0 || blue[x+1][j] != 0) break;
                col = j;
            }
            blue[x][col] = 1;
            blue[x + 1][col] = 1;
        }

        int row = 0;
        if (t == 1) {
            // 1x1
            for (int i = 0; i < 6; i++) {
                if(green[i][y] != 0) break;

                row = i;
            }
            green[row][y] = 1;
        } else if (t == 2) {
            // 1x2
            for (int i = 0; i < 6; i++) {
                if (green[i][y] != 0 || green[i][y + 1] != 0) break;

                row = i;
            }
            green[row][y] = 1;
            green[row][y + 1] = 1;
        } else {
            // 2x1
            for (int i = 1; i < 6; i++) {
                if(green[i-1][y] != 0 || green[i][y] != 0) break;

                row = i;
            }
            green[row - 1][y] = 1;
            green[row][y] = 1;
        }
    }

    public static void bombLine(){
        // 한 열 체크하고 지우고 당기기
//        System.out.println("bombLine");
        // blue
        while (true) {
            boolean flag = true;
            for (int j = 0; j < 6; j++) {
                int cntOfBlock = 0;
                for (int i = 0; i < 4; i++) {
                    if (blue[i][j] == 1) cntOfBlock += 1;
                    else break;
                }

                if (cntOfBlock == 4) {
                    flag = false;
                    score += 1;
                    for (int i = 0; i < 4; i++) blue[i][j] = 0;
                    for (int k = j - 1; k >= 0; k--) {
                        for (int i = 0; i < 4; i++) {
                            blue[i][k + 1] = blue[i][k];
                        }
                    }
                    for (int i = 0; i < 4; i++) blue[i][0] = 0;
                    break;
                }
            }
            if(flag) break;
        }

        while (true) {
            boolean flag = true;
            for (int i = 5; i > 1; i--) {
                int cntOfBlock = 0;
                for (int j = 0; j < 4; j++) {
                    if (green[i][j] == 1) cntOfBlock += 1;
                    else break;
                }

                if (cntOfBlock == 4) {
                    flag = false;
                    score += 1;
                    for (int j = 0; j < 4; j++) green[i][j] = 0;
                    for (int k = i - 1; k >= 0; k--) {
                        for (int j = 0; j < 4; j++) {
                            green[k + 1][j] = green[k][j];
                        }
                    }
                    for (int j = 0; j < 4; j++) {
                        green[0][j] = 0;
                    }
                    break;
                }
            }
            if(flag) break;
        }
    }

    public static void checkZeroAndOne(){
        // blue,green 특별한 칸에 타일 있으면 타일 당기기
//        System.out.println("checkZeroAndOne");
        // blue
        while(countSpecial("blue") > 0){
            int cnt = countSpecial("blue");
            for(int j=5; j>5-cnt; j--){
                for(int i=0; i<4; i++){
                    blue[i][j] = 0;
                }
            }
            for(int i=0; i<4; i++){
                for(int j=5; j>=1; j--){
                    int temp = blue[i][j];
                    blue[i][j] = blue[i][j-1];
                    blue[i][j-1] = temp;
                }
            }
        }

        // green
        while(countSpecial("green") > 0){
            int cnt = countSpecial("green");
            for(int i=5; i>5-cnt; i--){
                for(int j=0; j<4; j++){
                    green[i][j] = 0;
                }
            }

            for(int j=0; j<4; j++){
                for(int i=5; i>=1; i--){
                    int temp = green[i][j];
                    green[i][j] = green[i-1][j];
                    green[i-1][j] = temp;
                }
            }
        }
    }

    public static boolean possible(String area, int[][] blocks){
        // blue, green에 블록을 놓을 수 있는 위치인지 확인
        for(int i=0; i<blocks.length; i++){
            if(area.equals("blue")){
                if(blocks[i][0] < 0 || blocks[i][0] >= 4 || blocks[i][1] < 0 || blocks[i][1] >= 6) return false;
                if(blue[blocks[i][0]][blocks[i][1]] == 1) return false;
            }else{
                if(blocks[i][0] < 0 || blocks[i][0] >= 6 || blocks[i][1] < 0 || blocks[i][1] >= 4) return false;
                if(green[blocks[i][0]][blocks[i][1]] == 1) return false;
            }
        }
        return true;
    }

    public static int countSpecial(String area){
        // blue의 0,1열 green의 0,1행에 있는 타일의 개수
        int cnt = 0;
        if(area.equals("blue")){
            for(int j=0; j<2; j++){
                for(int i=0; i<4; i++){
                    if(blue[i][j] == 1){
                        cnt++;
                        break;
                    }
                }
            }
        }else{
            for(int i=0; i<2; i++){
                for(int j=0; j<4; j++){
                    if(green[i][j] == 1){
                        cnt++;
                        break;
                    }
                }
            }
        }
        return cnt;
    }

    public static void printBlue(){
        System.out.println("===== blue");
        for(int i=0; i<4; i++){
            for(int j=0; j<6; j++){
                System.out.print(blue[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printGreen(){
        System.out.println("===== green");
        for(int i=0; i<6; i++){
            for(int j=0; j<4; j++){
                System.out.print(green[i][j]+ " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
