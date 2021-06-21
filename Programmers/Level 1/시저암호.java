class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch >= 'a' && ch <= 'z'){ // 소문자
                ch = (char)(ch+n);
                if (ch > 'z')
					        ch -= 26;
            }else if (ch >= 'A' && ch <= 'Z'){ // 대문자
                ch = (char)(ch+n);
                if (ch > 'Z')
					        ch -= 26;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
