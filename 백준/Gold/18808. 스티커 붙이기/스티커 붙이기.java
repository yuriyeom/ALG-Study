import java.util.Scanner;
/*
 * 
모눈종이에 있는 스티커를 노트북에 붙여야한다.
모눈종이에 남는 행이나 열이 없다.
모눈종이의 스티커들이 하나로 연결되어있다.

먼저 받았던 스티커부터 붙인다.
1. 스티커를 그대로 떼어낸다.
2. 다른 스티커와 겹치지 않고, 노트북을 벗어나지 않는 붙일 수 있는 위치를 찾는다.
 	가장 위쪽, 가장 왼쪽을 우선으로 붙인다.
3. 선택한 위치에 스티커를 붙인다.
	스티커 붙일 위치가 없으면 시계 방향으로 90도 회전한 뒤 2번을 반복한다.
4. 스티커를 회전시킨 4가지 모양으로도 못 붙였다면 붙이지 않고 버린다.

스티커를 차례대로 붙인 후 몇 개의 칸에 붙여졌는지 구한다.

순서대로 붙이니까 입력받고 붙이고 입력받고 붙이고 ...
백트래킹할건 위치찾고 회전 ...?

back(스티커, map, idx)

idx == 5 이면 다 회전시켰다는거도 자리 없다는거

1. 고를 위치를 찾는다.
	 
2-1. 붙일 위치 있으면 붙이고 넘어간다. return;
2-2. 없으면 회전하고 2번 반복 back(회전한스티커, map)

*/

public class Main {
	static int N, M, K;
	static int[][] map, sticker;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		
		map = new int[N][M];
		
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<M; j++) {
//				map[i][j] = sc.nextInt();
//			}
//		}
		
		// K개의 스티커 입력
		for(int i=0; i<K; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			
			sticker = new int[r][c];
			
			for(int x=0; x<r; x++) {
				for(int y=0; y<c; y++) {
					sticker[x][y] = sc.nextInt();
				}
			}
			
			
			
////			스티커 잘 회전하는지
//			int[][] newSticker = rotateSticker(sticker, c, r);
//			
//			for(int x=0; x<c; x++) {
//				for(int y=0; y<r; y++) {
//					System.out.print(newSticker[x][y] + " ");
//				}
//				System.out.println();
//			}
			
//			스티커 붙일 위치 있는지 
//			System.out.println(findSpace(newSticker, r, c, map));
//			System.out.println(i + "번째 스티커");
			
//			for(int x=0; x<N; x++) {
//				for(int y=0; y<M; y++) {
//					System.out.print(map[x][y] + " ");
//				}
//				System.out.println();
//			}
			
			back(sticker, r, c, map, 0);
			
//			System.out.println();
		}
		int ans = 0;
		for(int x=0; x<N; x++) {
			for(int y=0; y<M; y++) {
				if(map[x][y] == 1) ans++;
			}
		}
		System.out.println(ans);
	}
	
	private static boolean back(int[][] sticker, int r, int c, int[][] map, int idx) {
		// idx == 5 이면 다 회전시켰다는거도 자리 없다는거
		if(idx == 5) { // 붙이기 실패
//			System.out.println("붙이기 실패");
			return false;
		}
		// 배열 복사
		int[][] tmap = new int[N][M];
		for(int x=0; x<N; x++) {
			for(int y=0; y<M; y++) {
				tmap[x][y] = map[x][y];
			}
		}
//		System.out.println("idx " + idx);
		// 1. 고를 위치를 찾는다.
		int[] result = findSpace(sticker, r, c, map);
		// 스티커 시작 위치를 돌려받아야 하나
		// 그래야 스티커 시작 위치와 스티커 가지고 map을 채운다
					 
		// 2-1. 붙일 위치 있으면 붙이고 넘어간다. return;
		if(result != null) {
			// 스티커를 붙인다
			attachSticker(sticker, result[0], result[1], map);
//			for(int x=0; x<N; x++) {
//				for(int y=0; y<M; y++) {
//					System.out.print(tmap[x][y] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("붙이기 성공");
			return true;
		}
		
		// 2-2. 없으면 회전하고 2번 반복 back(회전한스티커, map)
		
		// 스티커 회전
		int[][] rotateSticker = rotateSticker(sticker, c, r);
		// 회전시킨거 가지고 다시 백트래킹
		back(rotateSticker, c, r, map, idx+1);
		
		return false;
		
	}

	// 스티커를 붙이는 메소드
	private static void attachSticker(int[][] sticker, int r, int c, int[][] map) {
		
		for(int i=r; i<r+sticker.length; i++) {
			for(int j=c; j<c+sticker[0].length; j++) {
				if(sticker[i-r][j-c] == 1) map[i][j] = 1;
			}
		}
	}

	// 현재 스티커를 시계 방향으로 90도 회전시킨 뒤 리턴한다.
	private static int[][] rotateSticker(int[][] sticker, int r, int c) {
		int[][] newSticker = new int[r][c];
//		System.out.println(r + " " + c);
		
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) { // 
//				System.out.println(sticker[j][c-i-1]);
//				System.out.println((c-j-1) + " " + (i));
				newSticker[i][j] = sticker[c-1-j][i];
			}
		}
		
		return newSticker;
	}

	// 스티커 붙일 위치 있는지 찾는다.
	// 있으면 시작점 리턴
	private static int[] findSpace(int[][] sticker, int r, int c, int[][] map) {
//		boolean result = true;
		int[] result = new int[2];
		for(int i=0; i<=N-r; i++) { // d
			for(int j=0; j<=M-c; j++) {
				// i,j : 스티커 시작점
//				System.out.println(i + " " + j);
				boolean flag = true;
				for(int x=i; x<i+r; x++) {
					for(int y=j; y<j+c; y++) {
						
						if(sticker[x-i][y-j] == 1) { // 스티커가 있는 자리인데
							if(map[x][y] == 1) flag = false;
						}
						
					}
				}
				if(flag) {
					result[0] = i;
					result[1] = j;
					return result;
				}
			}
		}
		
		return null;
	}

}
