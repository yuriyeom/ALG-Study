import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        // wires에서 연결끊을 하나 선택해서 
        for(int i=0; i<wires.length; i++){
            ArrayList<Integer>[] adjList = new ArrayList[n+1];
            for(int j=1; j<=n; j++){
                adjList[j] = new ArrayList<>();
            }
            
            // 걔 빼고 인접리스트 그리기
            for(int j=0; j<wires.length; j++){
                if(j == i) continue;
                adjList[wires[j][0]].add(wires[j][1]);
                adjList[wires[j][1]].add(wires[j][0]);
            }
            
            // 선택한 하나의 v1, v2를 시작으로 개수 세기
            int group1Cnt = bfs(wires[i][0], n, wires, adjList);
            int group2Cnt = bfs(wires[i][1], n, wires, adjList);
            
            // 개수 차이
            answer = Math.min(Math.abs(group1Cnt-group2Cnt), answer);
        }
        return answer;
    }
    
    public int bfs(int start, int n, int[][] wires, ArrayList<Integer>[] adjList){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        queue.offer(start);
        visited[start] = true;
        int cnt = 0;
        
        while(!queue.isEmpty()){
            Integer q = queue.poll();
            cnt++;    
            for(Integer next : adjList[q]){
                if(visited[next]) continue;
                queue.offer(next);
                visited[next] = true;
            }
        }
        
        return cnt;
    }
}