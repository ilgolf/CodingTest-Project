import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int i,j,sz,t,n;
		String[] mi  = new String[101];
		mi[2] = "1";
		mi[3] = "7";
		mi[4] = "4";
		mi[5] = "2";
		mi[6] = "6";
		mi[7] = "8";
		mi[8] = "10";
		for(i=9;i<=100;i++) {
			mi[i] = "88888888888888888";
			sz = mi[i-2].length();
			for(j=0;j<sz;j++) mi[i] = comp(mi[i],mi[i-2].substring(0, j)+"1"+mi[i-2].substring(j));
			sz = mi[i-3].length();
			for(j=0;j<sz;j++) mi[i] = comp(mi[i],mi[i-3].substring(0, j)+"7"+mi[i-3].substring(j));
			sz = mi[i-4].length();
			for(j=0;j<sz;j++) mi[i] = comp(mi[i],mi[i-4].substring(0, j)+"4"+mi[i-4].substring(j));
			sz = mi[i-5].length();
			for(j=0;j<sz;j++) mi[i] = comp(mi[i],mi[i-5].substring(0, j)+"2"+mi[i-5].substring(j));
			mi[i] = comp(mi[i],"6"+mi[i-6]);
			sz = mi[i-6].length();
			for(j=1;j<sz+1;j++) mi[i] = comp(mi[i],mi[i-6].substring(0, j)+"0"+mi[i-6].substring(j));
			sz = mi[i-7].length();
			for(j=0;j<sz;j++) mi[i] = comp(mi[i],mi[i-7].substring(0, j)+"8"+mi[i-7].substring(j));
		}
		t = Integer.parseInt(br.readLine());
		while(t-->0) {
			n = Integer.parseInt(br.readLine());
			sb.append(mi[n]+" ");
			if(n%2==1) {
				sb.append("7");
				n-=3;
			}
			for(i=0;i<n;i+=2) sb.append("1");
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static String comp(String str1, String str2) {
		if(str1.length()<str2.length()) {
			return str1;
		}else if(str2.length()<str1.length()) {
			return str2;
		}else {
			if(str1.compareTo(str2)<0) {
				return str1;
			}else {
				return str2;
			}
		}
	}
}
