import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
///// 백트래킹 ㅡㅡ
public class Main {

	static int N;
	static int[] num = {9,8,7,6,5,4,3,2,1,0};
	static ArrayList<Long> list;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		list = new ArrayList<>();

		dfs(0, 0);
		
		Collections.sort(list);
		
		try {
			System.out.println(list.get(N-1));
		}catch(Exception e) {
			System.out.println(-1);
		}
		
	}
	
	public static void dfs(long n, int idx) {
		
		if(!list.contains(n)) {
			list.add(n);
		}
		
		if(idx >= 10) {
			return;
		}
		
		dfs(n*10 + num[idx], idx+1);
		dfs(n, idx+1);
		
	}

}
