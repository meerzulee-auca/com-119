import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {4,1,9,2,4,6,8,1,0};

        Arrays.sort(numbers);
        for (int n : numbers){
            System.out.print(n + " ");
        }

        System.out.println();
        String[] words = {"C" ,"Scala", "C++" ,"Java" ,"Kotlin" ,"C#"};
        Arrays.sort(words);

        for (String s : words){
            System.out.print(s + " ");
        }
        System.out.println();

        Rational[] rationals = {
            new Rational(1,2),new Rational(1,3),new Rational(2,3)
        };
        Arrays.sort(rationals);

        for (Rational rational : rationals){
            System.out.print(rational + " ");
        }
        System.out.println();
//        ----
        Student[] students = {
                new Student("A",3.2f,2000),
                new Student("X",3.5f,2001),
                new Student("D",3.1f,2002),
        };
        Arrays.sort(students, new CompareByName());

        for (Student student : students){
            System.out.print(student + " ");
        }
        System.out.println();


        Arrays.sort(students, new CompareByGPA());

        for (Student student : students){
            System.out.print(student + " ");
        }
        System.out.println();
//YEAR
        Arrays.sort(students, (s1, s2) -> Integer.compare(s1.getBirthYear(),s2.getBirthYear()));

        for (Student student : students){
            System.out.print(student + " ");
        }
        System.out.println();
    }
}
