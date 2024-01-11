import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n, m, answer;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        adjList = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            adjList[i] = new ArrayList<>();
        }

        int a = sc.nextInt();
        int b = sc.nextInt();

        m = sc.nextInt();
        for(int i=0; i<m; i++){
            int x = sc.nextInt();
            int y = sc.nextInt();

            adjList[x].add(y);
            adjList[y].add(x);
        }

        visited = new boolean[n+1];
        dfs(b, a, 0);
        System.out.println(answer == 0 ? -1 : answer);
    }

    static void dfs(int goal, int now, int cnt){

        if(now == goal){
            answer = cnt;
            return;
        }

        visited[now] = true;
        for(int adj : adjList[now]){
            if(visited[adj]) continue;
            dfs(goal, adj, cnt+1);
        }
    }
}