import java.util.*;
class Solution {
    boolean[] visited;
    ArrayList<String> list;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];
        list = new ArrayList<>();
        
        dfs(tickets, 0, tickets.length, "ICN", "ICN");
        
        Collections.sort(list);
        
        return list.get(0).split(" ");
    }
    
    public void dfs(String[][] tickets, int depth, int n, String from, String route){
        
        if(depth == n){
            list.add(route);
            return;
        }
        
        for(int i=0; i<tickets.length; i++){
            if(visited[i]) continue;
            
            if(tickets[i][0].equals(from)){
                visited[i] = true;
                dfs(tickets, depth+1, n, tickets[i][1], route + " " + tickets[i][1]);
                visited[i] = false;
            }
        }
    }
}