import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
/*
* 5x5에 1~25
* 하나씩 숫자 부르다가
* 같은 가로,세로,대각선 5개가 지워지면 한줄 긋기
* 3줄 이상이면 빙고!
*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[5][5];
        for(int i=0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        boolean find = false;
        for(int i=0; i<5; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<5; j++){
                int num = Integer.parseInt(st.nextToken());
                cnt++;

                // 숫자 지우기
                for(int p=0; p<5; p++){
                    for(int q=0; q<5; q++){
                        if(map[p][q] == num){
                            map[p][q] = -1;
                        }
                    }
                }

                // 지워진 줄 세기
                int bingo = checkBingo(map);
                if(bingo >= 3){
                    find = true;
                    break;
                }
            }
            if(find) break;
        }

        System.out.println(cnt);
    }

    public static int checkBingo(int[][] map){
        int cnt = 0;
        int crossSum1 = 0;
        int crossSum2 = 0;
        for(int i=0; i<5; i++){
            // 세로
            int colSum = 0;
            for(int j=0; j<5; j++){
                colSum += map[j][i];
            }
            if(colSum == -5) cnt++;

            // 가로
            int rowSum = 0;
            for(int j=0; j<5; j++){
                rowSum += map[i][j];
            }
            if(rowSum == -5) cnt++;

            // 대각선 \
            crossSum1 += map[i][i];
            if(crossSum1 == -5) cnt++;

            // 대각선 /
            crossSum2 += map[i][4-i];
            if(crossSum2 == -5) cnt++;

        }
        return cnt;
    }
}
