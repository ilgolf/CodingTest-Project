import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n,i;
		Stack<Character> stack = new Stack<>();
		String str = br.readLine();
		n = str.length();
		for(i=0;i<n;i++) {
			if(str.charAt(i)=='<') {
				while(!stack.isEmpty()) sb.append(stack.pop());
				while(str.charAt(i)!='>') sb.append(str.charAt(i++));
				sb.append(">");
			}else if(str.charAt(i)==' ') {
				while(!stack.isEmpty()) sb.append(stack.pop());
				sb.append(" ");
			}else {
				stack.push(str.charAt(i));
			}
		}
		while(!stack.isEmpty()) sb.append(stack.pop());
		System.out.println(sb);
	}
}
