package Database;



import java.io.Serializable;

public class Grade implements Serializable {

    private String gradeTitle;
    private double grade;

    public String getGradeTitle() {
        return gradeTitle;
    }

    public void setGradeTitle(String gradeTitle) {
        this.gradeTitle = gradeTitle;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
