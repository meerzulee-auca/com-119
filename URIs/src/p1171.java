import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class p1171 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] arr = new int[n];
        ArrayList<Integer> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(in.nextLine());
            a.add(arr[i]);

        }


        for (int i = 0; i < a.size() - 1; i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).equals(a.get(j))) {
                    a.remove(j);
                    j--;
                }
            }
        }

        int[][] newArr = new int[2][a.size()];
        for (int i = 0; i < a.size(); i++) {
            newArr[0][i] = a.get(i);
        }

        Arrays.sort(newArr[0]);

        for (int i = 0; i < newArr[0].length; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[j] == newArr[0][i])
                    newArr[1][i]++;
            }
        }

        for (int i = 0; i < newArr[0].length; i++) {
            System.out.printf("%d aparece %d vez(es)%n", newArr[0][i], newArr[1][i]);
        }



    }
}
