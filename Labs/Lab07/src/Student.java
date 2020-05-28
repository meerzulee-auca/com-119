import java.util.Comparator;

class CompareByName implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getName().compareTo(s2.getName());
    }
}
class CompareByGPA implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2) {
        return Float.compare(s1.getGpa(),s2.getGpa());
    }
}
class CompareByYear implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2) {
        return Integer.compare(s1.getBirthYear(),s2.getBirthYear());
    }
}

public class Student implements Comparable<Student>{
    private String name;
    private float gpa;
    private int birthYear;

    public String getName() {
        return name;
    }

    public float getGpa() {
        return gpa;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public Student(String name, float gpa, int birthYear) {
        this.name = name;
        this.gpa = gpa;
        this.birthYear = birthYear;
    }

    @Override
    public int compareTo(Student s) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Student(%s, %.1f, %d)",name,gpa,birthYear);
    }
}
