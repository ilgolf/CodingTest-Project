import java.io.IOException;
import java.util.HashSet;
import java.util.Stack;

class Solution {
  public static void DFS(int[][] graph, int start_vertex) {
		Stack<Integer> will_visit = new Stack<>();
		visited.add(start_vertex);
		will_visit.push(start_vertex);
		while (will_visit.isEmpty() == false) {
			Integer current_vertex = will_visit.pop();
			for(int i=1; i<graph.length;i++)
			{
				if(graph[current_vertex][i]==1&&!visited.contains(i))
				{
					will_visit.add(i);
					visited.add(i);
				}
			}
		}
	}
	static HashSet<Integer> visited = new HashSet<>();
	static int areaCount(int[][] graph, int start_vertex, int n) {

		int count = 0;
		for (int i=0; i<graph.length;i++)
		{
			if(!visited.contains(i))
			{
				DFS(graph,i);
				count++;
			}
		}
		return count;
	}

    public int solution(int n, int[][] computers) {
        int answer = areaCount(computers,0,n);
        return answer;
    }
}