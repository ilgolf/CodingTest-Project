import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
    public static void main(String args[]) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String str = br.readLine();
    	String pattern = br.readLine();
    	System.out.println(KMP(str,pattern));
    }
    
    public static int KMP(String str, String pattern) {
    	int[] pi = getPI(pattern);
    	char[] s = str.toCharArray();
    	char[] p = pattern.toCharArray();
    	int sz = str.length();
    	int j = 0;
    	for(int i=0;i<sz;i++) {
    		while(j>0&&s[i]!=p[j]) j = pi[j-1];
    		if(s[i]==p[j]) {
    			if(j==p.length-1) return 1;
    			j++;
    		}
    	}
    	return 0;
    }
    
    public static int[] getPI(String pattern) {
    	char[] p = pattern.toCharArray();
    	int j = 0;
    	int sz = p.length;
    	int[] pi = new int[sz];
    	for(int i=1;i<sz;i++) {
    		while(j>0&&p[i]!=p[j]) j = pi[j-1];
    		if(p[i]==p[j]) pi[i] = ++j;
    	}
    	return pi;
    }
}
