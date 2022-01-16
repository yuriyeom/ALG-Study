class Solution {
    public String solution(int n, int t, int m, int p) {
        String str = "0";
        StringBuilder sb = new StringBuilder();
        // 총 t*m 개, p+m간격으로 말함
        int num = 0;
        while(str.length() < t * m){
            String temp = "";
            int nn = num++;
            while(nn > 0){
                if(nn%n >= 10){
                    temp = (char) (nn % n + 55) + temp;
                }else{
                    temp = nn % n + temp;
                }
                nn /= n; 
            }
            str += temp;
        }

        for(int i=0; i<t; i++){
            int idx = p-1 + m*i ;
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }
}
