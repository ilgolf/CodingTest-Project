import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Process implements Comparable<Process> {
		int id;
		int time;
		int priority;

		public Process() {
		}

		public Process(int id, int time, int priority) {
			this.id = id;
			this.time = time;
			this.priority = priority;
		}

		@Override
		public int compareTo(Process target) {
			if (target.priority < this.priority)
				return -1;
			else if (target.priority > this.priority)
				return 1;
			else if (this.id < target.id)
				return -1;
			else
				return 1;
		}
	}

	public static void main(String[] agrs) throws NumberFormatException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int T = Integer.parseInt(tokenizer.nextToken()); // T초
		int n = Integer.parseInt(tokenizer.nextToken());
		PriorityQueue<Process> priorityQueue = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			priorityQueue.offer(new Process(Integer.parseInt(tokenizer.nextToken()),
					Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < T; i++) {
			if(priorityQueue.isEmpty())break;
			Process p = priorityQueue.poll();
			builder.append(p.id + "\n");
			if (p.time != 1)
				priorityQueue.offer(new Process(p.id, p.time - 1, p.priority - 1));
		}
		System.out.println(builder.toString());
	}
}