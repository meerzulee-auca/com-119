import java.util.Scanner;

public class p1030 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        for (int j = 1; j <= n; j++) {
            int amount = in.nextInt();
            int step = in.nextInt();
            int left = 0;
            for (int i = 2; i <= amount; i++) {
                left = (left + step) % i;
            }
            System.out.printf("Case %d: %d%n", j, left + 1);

            in.nextLine();
        }


    }
}
