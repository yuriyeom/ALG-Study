import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int L = sc.nextInt();
		int C = sc.nextInt();
		
		char[] arr = new char[C];
		
		for(int i=0; i<C; i++) {
			arr[i] = sc.next().charAt(0);
		}
		
		Arrays.sort(arr);
		
		perm(arr, new char[L], 0, 0);
	}
	
	public static void perm(char[] arr, char[] output, int idx,int k) {
		if(k == output.length) {
			String str = "";
			boolean aeiou = false;
			int jaum = 0;
			for(char o : output) {
				str += o;
				if(o == 'a' || o == 'e' || o == 'i' || o == 'o' || o == 'u') aeiou = true;
				else jaum++;
			}
			if(aeiou && jaum >= 2) System.out.println(str);
			
			return;
		}
		
		for(int i=idx; i<arr.length; i++) {
			output[k] = arr[i];
			perm(arr, output, i+1, k+1);
		}
	}

}
