import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] gear = new int[4][8];

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n,i,j,grn,grr,score;
		for(i=0;i<4;i++) {
			String str = br.readLine();
			for(j=0;j<8;j++) gear[i][j] = str.charAt(j)-'0';
		}
		n = Integer.parseInt(br.readLine());
		while(n-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			grn = Integer.parseInt(st.nextToken())-1;
			grr = Integer.parseInt(st.nextToken());
			lcheck(grn,grr,true);
			rcheck(grn,grr,true);
			rotate(grn,grr);
		}
		score = 0;
		for(i=3;i>=0;i--) {
			score <<= 1;
			score += gear[i][0];
		}
		System.out.println(score);
	}
	static void lcheck(int gn, int gr, boolean chk) {
		if(gn>=1&&gear[gn-1][2]!=gear[gn][6]) lcheck(gn-1,gr*-1,false);
		if(!chk) rotate(gn,gr);
	}
	static void rcheck(int gn, int gr, boolean chk) {
		if(gn<=2&&gear[gn+1][6]!=gear[gn][2]) rcheck(gn+1,gr*-1,false);
		if(!chk) rotate(gn,gr);
	}
	static void rotate(int gn, int gr) {
		int tn = (8-gr)%9;
		int tmp = gear[gn][tn];
		for(int i=0;i<7;i++) gear[gn][tn-gr*i]=gear[gn][tn-gr*(i+1)];
		gear[gn][7-tn]=tmp;
	}		
}
