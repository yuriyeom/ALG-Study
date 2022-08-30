package boj.n2636;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int time = 0;
		int cnt = 0;
		while (true) {

			// 치즈 없으면 중단
			boolean flag = false;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 1) {
						flag = true;
						cnt++;
					}
						
				}
			}
			if (!flag)
				break;

			cnt = 0;
			
			time++;

			// 녹이기
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 0 || visited[i][j])
						continue;

					bfs(i, j);
				}
			}
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 2) {
						map[i][j] = 0;
					}
				}
			}

		}
		
		System.out.println(time + " " + cnt);
	}

	private static void bfs(int x, int y) {
		Queue<Point> queue = new LinkedList<>();

		queue.offer(new Point(x, y));
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			Point p = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] == 0) {
					map[p.x][p.y] = 2;
					continue;
				}

				visited[nx][ny] = true;
				queue.offer(new Point(nx, ny));
			}
		}
	}
}
