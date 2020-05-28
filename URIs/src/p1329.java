import java.util.Scanner;

public class p1329 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        while ((n = in.nextInt()) != 0) {
            int m = 0, j = 0, num;
            for (int i = 0; i < n; i++) {
                num = in.nextInt();
                if(num==0)
                    m++;
                else j++;
            }
            System.out.printf("Mary won %d times and John won %d times\n", m, j);
        }
    }
}
