class Solution {
    static int answer = 0;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];

        dfs(begin, target, words, 0);

        return answer;
    }
    public void dfs(String begin, String target, String[] words, int n){

        if(begin.equals(target)){
            answer = n;
            return;
        }

        for(int i=0; i<words.length; i++){ // 0 1 2
            if(!visited[i] && match_one(begin, words[i])){
                visited[i] = true;
                dfs(words[i], target, words, n+1);
                visited[i] = false;
            }
        }
    }

    // 한 글자만 다른 단어 찾기
    public boolean match_one(String s1, String s2){
        int count = 0;
        for(int i=0; i<s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)) count++;
        }

        return count == 1;
    }
}
