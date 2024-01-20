import java.util.*;
/*
[a,b]
1. a 다음 b로 정렬
2. visited 배열

*/
class Solution {
    boolean[] visited;
    ArrayList<String> output;
    boolean end = false;
    String[] answer;
    public String[] solution(String[][] tickets) {
        
        // a 다음 b 순서로 정렬
        Arrays.sort(tickets, new Comparator<>(){
            public int compare(String[] ticket1, String[] ticket2){
                if(ticket1[0].equals(ticket2[0])){
                    return ticket1[1].compareTo(ticket2[1]);
                }
                return ticket1[0].compareTo(ticket2[0]);
            }
        });
        
        for(int i=0; i<tickets.length; i++){
            if(tickets[i][0].equals("ICN")){
                visited = new boolean[tickets.length];
                output = new ArrayList<>();
                
                output.add("ICN");
                visited[i] = true;
                dfs(tickets, "ICN", tickets[i][1], 1, i, tickets.length);
                if(end) break;
            }
        }
        
        return answer;
    }
    
    void dfs(String[][] tickets, String from, String to, int depth, int idx, int n){
        visited[idx] = true;
        
        if(end) return; // 이미 경로를 완성했으면 끝
        if(isVisitedAll(visited)){ // 모든 도시를 방문했으면 경로 완성
            output.add(to); 
            end = true;
            answer = new String[output.size()];
            answer = output.toArray(answer);
            return;
        }
        
        for(int i=0; i<tickets.length; i++){
            if(visited[i]) continue;
            
            if(tickets[i][0].equals(to)){
                output.add(to);
                dfs(tickets, to, tickets[i][1], depth+1, i, n);
                output.remove(output.size()-1);
            }
        }
        visited[idx] = false;
    }
    
    boolean isVisitedAll(boolean[] visited){ // 모든 도시를 방문했는가
        for(int i=0; i<visited.length; i++){
            if(!visited[i])
                return false;
        }
        return true;
    }
}