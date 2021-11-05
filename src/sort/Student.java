package sort;

import java.util.Locale;
import java.util.Objects;

public class Student implements Comparable<Student>{

    private String name;
    private int score;

    public Student(String name, int score){
        this.name = name;
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Student(name: %s, score: %d)", name, score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return this.name.toLowerCase().equals(student.name.toLowerCase());
    }

    @Override
    public int compareTo(Student o) {
        return this.score - o.score;
    }
}
