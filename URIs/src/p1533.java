import java.util.Arrays;
import java.util.Scanner;

public class p1533 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n;
        while (true) {
            n = Integer.parseInt(in.nextLine());
            if (n == 0) break;
            int[] a = new int[n];
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                arr[i] = a[i];
            }
            Arrays.sort(a);
            for (int i = 0; i < n; i++) {
                if (arr[i] == a[n - 2])
                    System.out.println(i + 1);
            }
            in.nextLine();
        }
    }
}
