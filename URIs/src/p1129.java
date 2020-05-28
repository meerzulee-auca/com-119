import java.util.Scanner;

public class p1129 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n;
        while ((n = Integer.parseInt(in.nextLine())) != 0) {
            while (n-- > 0) {
                int counter = 0, index = 0;
                String l = null;
                String[] s = in.nextLine().split(" ");
                for (int i = 0; i < s.length; i++) {
                    if (Integer.parseInt(s[i]) <= 127) {
                        counter++;
                        index = i;
                    }
                }
                if (counter > 1 || counter==0) {
                    l="*";
                }
                else {
                    switch(index){
                        case 0:
                            l="A";
                            break;
                        case 1:
                            l="B";
                            break;
                        case 2:
                            l="C";
                            break;
                        case 3:
                            l="D";
                            break;
                        case 4:
                            l="E";
                            break;

                    }
                }
                System.out.println(l);
            }
        }
    }
}
