class Solution {
    public int solution(int n) {
        int answer = n+1;
        
        int cntBinary = countOne(Integer.toBinaryString(n));
        
        while(true){
            if(cntBinary == countOne(Integer.toBinaryString(answer)))
                break;
            answer++;
        }
        
        return answer;
    }
    
    public int countOne(String str){
        int cnt = 0;
        for(int i=0; i<str.length(); i++)
            if(str.charAt(i) == '1') cnt++;
        return cnt;
    }
}