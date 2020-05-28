import java.util.HashSet;
import java.util.Scanner;

public class p1609 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        while (n-- > 0) {
            int m = in.nextInt();
            HashSet<Integer> hashSet = new HashSet<>();
            for (int i = 0; i < m; i++) {
                hashSet.add(in.nextInt());
            }
            System.out.println(hashSet.size());
        }
    }
}
