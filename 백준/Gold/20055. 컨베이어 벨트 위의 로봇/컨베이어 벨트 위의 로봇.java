import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, robotIdx;
    static int[] belt;
    static int[] robots;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        belt = new int[N*2];
        for(int i=0; i<N*2; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        robots = new int[N*2];
        robotIdx = 1;
        int time = 0;

        while(!finish()){
            rotate();

            moveRobots();

            putRobots();

            time++;
        }

        System.out.println(time);
    }

    public static void rotate(){
        // 1. 회전

        // 벨트 회전
        int temp = belt[N*2-1];
        for(int j=N*2-1; j>=1; j--){
            belt[j] = belt[j-1];
        }

        belt[0] = temp;

        // 로봇 회전
        for(int j=N*2-1; j>=1; j--){
            robots[j] = robots[j-1];
        }
        robots[0] = 0;
    }

    public static void moveRobots(){
        // 2. 로봇 이동
        // 가장 먼저 올라간 로봇부터 회전하는 방향으로 한 칸 이동
        // 이동하려는 칸에 로봇이 없고, 내구도 1이상이어야 이동 가능

        robots[N-1] = 0;
        for(int i=N-1; i>0; i--){
            if(belt[i] >= 1 && robots[i-1] > 0 && robots[i] == 0){
                robots[i] = robots[i-1];
                robots[i-1] = 0;
                belt[i] -= 1;
            }
        }

    }

    public static void putRobots(){
        // 3. 로봇 올리기
        if(belt[0] > 0){
            robots[0] = robotIdx;
            belt[0] -= 1;
        }
    }

    public static boolean finish(){
        // 4. 종료 체크

        int cnt = 0;
        for(int i=0; i<N*2; i++){
            if(belt[i] == 0) cnt++;
        }

        return cnt >= K;
    }
}
