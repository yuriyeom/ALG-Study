import java.util.Scanner;

public class Main {

	static int[][] map;
	static boolean[] visited;
	static int ans;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for(int t=1; t<=tc; t++) {
			
			map = new int[11][11];
			
			for(int i=0; i<11; i++) {
				for(int j=0; j<11; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			ans = 0;
			visited = new boolean[11];
			
			dfs(0, 0); 
			
			System.out.println(ans);
		}

	}
	
	private static void dfs(int player, int sum) {
		if(player == 11) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i=0; i<11; i++) {
			if(visited[i]) continue;
			if(map[player][i] == 0) continue;
			
			visited[i] = true;
			dfs(player+1,  sum + map[player][i]);
			visited[i] = false;
		}
		
	}
}