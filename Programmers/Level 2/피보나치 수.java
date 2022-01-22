import java.util.*;

class Solution {
    public int solution(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        
        for(int i=2; i<=n; i++){
            list.add((list.get(i-1) % 1234567) + (list.get(i-2) % 1234567));
        }
        return list.get(n) % 1234567;
    }
}
