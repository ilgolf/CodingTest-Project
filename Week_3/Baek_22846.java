package Week_3;

import java.io.*;

public class Baek_22846 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
		System.out.print(n == 2 || n == 6 ? "Kali" : "Ringo");
    }
}
