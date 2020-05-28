import java.util.Scanner;

public class p1558 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            int n = Integer.parseInt(in.nextLine());
            boolean condition = false;
            for (int i = 0; i <= Math.ceil(Math.sqrt(n)); i++) {
                for (int j = 0; j <= Math.ceil(Math.sqrt(n)); j++) {
                    if (n == ((i * i) + (j * j))) {
                        condition = true;
                        break;
                    }
                }
            }
            if(condition){
                System.out.println("YES");
            } else{
                System.out.println("NO");
            }
        }
    }
}
