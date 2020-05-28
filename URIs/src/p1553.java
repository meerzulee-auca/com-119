import java.util.Scanner;

public class p1553 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n, p;
        while ((n = in.nextInt())!=0 && (p = in.nextInt())!=0) {
            in.nextLine();
            int q, counter = 0, max = 0;
            int[] a = new int[1001];
            while (n-- > 0) {
                q = in.nextInt();
                a[q]++;
                if(max<q)
                    max=q;
            }

            for(int i = 0;i<=max;i++){
                if(a[i]>=p)
                    counter++;
            }

            System.out.println(counter);
        }

    }
}
