import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static char[] exps;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        exps = br.readLine().toCharArray();

        ans = (int)-Math.pow(2,31);
        // 연산자 개수 : N/2
        for(int i=0; i<N/2; i++)
            comb(0, 0, N/2, i, new boolean[N/2]);

        System.out.println(N == 1 ? exps[0] - '0' : ans);
    }

    public static void comb(int idx, int depth, int opCnt, int size, boolean[] visited){
        if(depth == size){
            // true이면 left, right 구해서 먼저 계산
            // [3, +, 8, *, 7, -, 9, *, 2]
            List<String> afterExp = new ArrayList<>();
            int expIdx = 0;
            for(int i=0; i<exps.length; i++){
                if(exps[i] - '0' < 0 && visited[i/2]) { // 괄호 씌울 연산자면
                    afterExp.remove(afterExp.size()-1);
                    int result = calNum(exps[i-1] - '0', exps[i+1] - '0', exps[i]);
                    afterExp.add(String.valueOf(result));
                    i += 1;
                    expIdx++;
                }else{
                    afterExp.add(String.valueOf(exps[i]));
                }
            }
            ans = Math.max(ans, finalCal(afterExp));
            return;
        }

        for(int i=idx; i<opCnt; i++){
            if(visited[i]) continue;
            if(i != 0 && visited[i-1]) continue; // 괄호가 겹치면 안되니까
            visited[i] = true;
            comb(i+1, depth+1, opCnt, size, visited);
            visited[i] = false;
        }
    }

    public static int calNum(int left, int right, char exp){
        if(exp == '+'){
            return left+right;
        }else if(exp == '-'){
            return left-right;
        }else if(exp == '*'){
            return left*right;
        }else{
            return left/right;
        }
    }

    public static int finalCal(List<String> expList){
        int sum = Integer.parseInt(expList.get(0));
        int i=1;
        while(i < expList.size()){
            int result = calNum(sum, Integer.parseInt(expList.get(i+1)), expList.get(i).charAt(0));
            sum = result;
            i += 2;
        }
        return sum;
    }
}
