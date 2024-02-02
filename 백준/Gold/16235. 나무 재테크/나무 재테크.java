import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
* 13:39~
*
* NxN 땅 A[][], 모든 칸에 5씩
*
* M개 나무 심었다. 한칸에 여러개 가능
*
* 사계절 반복. 한칸에 여러나무 있으면 나이어린 나무부터 양분먹는다. 양분 부족하면 죽는다.
* 봄 : 양분 += 나이만큼, 나이++
* 여름 : 봄에 죽은 나무가 양분된다. (죽은 나무의 나이/2) 만큼
* 가을 : 나이가 5의배수이면 번식. 인접한 8칸에 나이1인 나무 추가
* 겨울 : 로봇이 돌아다니면서 A[r][c] 양분 추가
*
* K년 후 살아있는 나무 개수 구하기
* */
public class Main {
    static int N, M, K;
    static int[][] A, food;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    static ArrayList<Integer>[][] plantedTree;
    static ArrayList<Tree> deadList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // NxN 땅
        M = Integer.parseInt(st.nextToken()); // 구매한 나무
        K = Integer.parseInt(st.nextToken()); // K년

        food = new int[N][N]; // 현재 땅의 양분. 모든 칸에 5
        A = new int[N][N]; // 겨울에 추가할 양분 초기값
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                food[i][j] = 5;
            }
        }

        // 하나의 칸에 여러 나무를 심을 수 있으므로 리스트를 2차원으로 만든다.
        plantedTree = new ArrayList[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                plantedTree[i][j] = new ArrayList<>();
            }
        }

        // 초기 나무 입력받아서 plantedTree에 심기
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int age = Integer.parseInt(st.nextToken());
            plantedTree[x][y].add(age);
        }

        deadList = new ArrayList<>(); // 죽은 나무 리스트
        while(K-- > 0){
            spring();
            summer();
            fall();
            winter();
        }

        System.out.println(countTree());
    }

    public static void spring(){
        // * 봄 : 양분 += 나이만큼, 나이++
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(plantedTree[i][j].isEmpty()) continue;

                // 어린 나무부터 정렬
                Collections.sort(plantedTree[i][j]);

                boolean[] dead = new boolean[plantedTree[i][j].size()];
                for(int t=0; t<plantedTree[i][j].size(); t++){
                    int treeAge = plantedTree[i][j].get(t);
                    if(food[i][j] >= treeAge){ // 양분이 충분히 있으면
                        food[i][j] -= treeAge;
                        plantedTree[i][j].set(t, treeAge+1);
                    }else{ // 양분 못 먹으면 죽는다.
                        deadList.add(new Tree(i,j,treeAge));
                        dead[t] = true;
                    }
                }

                // 현재 땅에서 죽은 나무 제거
                for(int t=plantedTree[i][j].size()-1; t>=0; t--) {
                    if(dead[t]) plantedTree[i][j].remove(t);
                }
            }
        }
    }

    public static void summer(){
        //* 여름 : 봄에 죽은 나무가 양분된다. (죽은 나무의 나이/2) 만큼
        for(Tree tree : deadList){
            food[tree.x][tree.y] += tree.age/2;
        }
        deadList.clear();
    }

    public static void fall(){
        //* 가을 : 나이가 5의배수이면 번식. 인접한 8칸에 나이1인 나무 추가
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int tree : plantedTree[i][j]){
                    if(tree % 5 != 0) continue;

                    for(int d=0; d<8; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                        plantedTree[nx][ny].add(1);
                    }
                }
            }
        }
    }

    public static void winter(){
        //* 겨울 : 로봇이 돌아다니면서 A[r][c] 양분 추가
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                food[i][j] += A[i][j];
            }
        }
    }

    public static int countTree(){
        int cnt = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(plantedTree[i][j].isEmpty()) continue;

                cnt += plantedTree[i][j].size();
            }
        }
        return cnt;
    }
}

class Tree{
    int x, y, age;

    public Tree(int x, int y, int age){
        this.x = x;
        this.y = y;
        this.age = age;
    }
}
