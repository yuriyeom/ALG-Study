import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, ans;
    static int[][] map, popul;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        popul = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                popul[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = Integer.MAX_VALUE;

        for(int x=0; x<N; x++){
            for(int y=0; y<N; y++){
                for(int d2=1; y+d2<=N; d2++){
                    for(int d1=1; x+d1+d2<N; d1++){
                        if(y - d1 < 0) continue;

                        map = new int[N][N];
                        int[][] lineStart = {{x,y}, {x,y}, {x+d1, y-d1}, {x+d2, y+d2}};
                        int[][] lineEnd = {{x+d1, y-d1}, {x+d2, y+d2}, {x+d1+d2, y-d1+d2}, {x+d2+d1, y+d2-d1}};
                        int[][] add = {{1,-1}, {1,1}, {1,1}, {1,-1}};

                        // 5번 선거구 경계선
                        // 예시 N=7,x = 2, y = 4, d1 = 2, d2 = 2일때 경계선
                        // (1,3)~(3,1)
                        // (1,3)~(3,5)
                        // (3,1)~(5,3)
                        // (3,5) ~ (5,3)
                        for(int d=0; d<4; d++){
                            int i = lineStart[d][0];
                            int j = lineStart[d][1];

                            while(true){
                                if(!isInside(i, j)){
                                    break;
                                }
                                map[i][j] = 5;

                                if(i == lineEnd[d][0] && j == lineEnd[d][1]) break;

                                i += add[d][0];
                                j += add[d][1];
                            }
                        }

                        // 5번 선거구 채우기
                        for(int i=0; i<N; i++){
                            int s = 0, e = N-1;
                            while(true){
                                if(map[i][s] != 5) s++;
                                if(map[i][e] != 5) e--;

                                if(s >= e) break;
                                if(map[i][s] == 5 && map[i][e] == 5) break;
                            }

                            if(map[i][s] == 5 && map[i][e] != 5) e = N-1;
                            if(map[i][s] != 5 && map[i][e] == 5) s = 0;

                            for(int j=s+1; j<e; j++){
                                map[i][j] = 5;
                            }
                        }


                        // 1~4번 선거구
                        for(int i=0; i<x+d1; i++){
                            for(int j=0; j<=y; j++){
                                if(map[i][j] == 5) break;
                                map[i][j] = 1;
                            }
                        }

                        for(int i=0; i<=x+d2; i++){
                            for(int j=N-1; j>y; j--){
                                if(map[i][j] == 5) break;
                                map[i][j] = 2;
                            }
                        }

                        for(int i=x+d1; i<N; i++){
                            for(int j=0; j<y-d1+d2; j++){
                                if(map[i][j] == 5) break;
                                map[i][j] = 3;
                            }
                        }

                        for(int i=x+d2+1; i<N; i++){
                            for(int j=N-1; j>=y-d1+d2; j--){
                                if(map[i][j] == 5) break;
                                map[i][j] = 4;
                            }
                        }

                        ans = Math.min(ans, countArea());
                    }
                }
            }
        }

        System.out.println(ans);
    }

    public static int countArea(){
        int[] cnt = new int[6];

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                cnt[map[i][j]] += popul[i][j];
            }
        }

        int min=Integer.MAX_VALUE, max=0;
        for(int i=1; i<6; i++){
            min = Math.min(min, cnt[i]);
            max = Math.max(max, cnt[i]);
        }
        return max-min;
    }

    public static boolean isInside(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
