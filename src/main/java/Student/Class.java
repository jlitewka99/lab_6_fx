package Student;
import Database.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
public class Class implements Serializable {

    /*
    private String className;
    private List<Grade> grades;
    private double points;
    @Column(name = "status", order = 2)
     */
    @Column(name = "classNameAnn", order = 1)
    private String nazwaGrupy;
    private List<Student> listaStudentow = new ArrayList<>();
    private Integer maksymalnaIloscStudentow;
    @Column(name = "pointsAnn", order = 3)
    private Integer iloscStudentow = 0;

    public String getNazwaGrupy() {
        return nazwaGrupy;
    }
    public Student max() {
        return Collections.max(listaStudentow, Comparator.comparingDouble(Student::getIloscPunktow));
    }

    public void setListaStudentow(List<Student> listaStudentow) {
        this.listaStudentow = listaStudentow;
    }

    public void setMaksymalnaIloscStudentow(Integer maksymalnaIloscStudentow) {
        this.maksymalnaIloscStudentow = maksymalnaIloscStudentow;
    }

    public void setIloscStudentow(Integer iloscStudentow) {
        this.iloscStudentow = iloscStudentow;
    }

    public Integer getIloscStudentow() {
        return iloscStudentow;
    }

    public Class(List<Student> listaStudentow) {
        this.listaStudentow = listaStudentow;
    }

    public int getMaksymalnaIloscStudentow() {
        return maksymalnaIloscStudentow;
    }

    public Class(String nazwaGrupy, int maksymalnaIloscStudentow) {
        this.nazwaGrupy = nazwaGrupy;
        this.maksymalnaIloscStudentow = maksymalnaIloscStudentow;
    }

    public Class() {
    }

    public List<Student> getListaStudentow() {
        return listaStudentow;
    }

    public void addStudent(Student student) {

        if (maksymalnaIloscStudentow <= listaStudentow.size()) {
            System.err.println("Przekroczona maksymalna ilosc studentow!");
            return;
        }
        for (Student s : listaStudentow) {
            if (student == s) {
                System.err.println("Podany student jest juz zapisany");
                return;
            }
        }
        listaStudentow.add(student);
        iloscStudentow = listaStudentow.size();
    }

    public void addPoints(Student student, double punkty) {
        int index = 0;
        try {
            index = findIndexOfStudent(student);
            listaStudentow.get(index).addPoints(punkty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removePoints(Student student, double punkty) {
        try {
            int index = findIndexOfStudent(student);
            listaStudentow.get(index).addPoints(-punkty);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Student search(String imie) throws Exception {
        return listaStudentow.get(findIndexOfStudent(new Student(imie)));
    }

    public LinkedList<Student> searchPartial(String fragment) {
        LinkedList<Student> lista = new LinkedList<>();

        for (Student student : listaStudentow) {
            if (student.getImie().contains(fragment) || student.getNazwisko().contains(fragment)) {
                lista.add(student);
            }
        }
        return lista;
    }

    private int findIndexOfStudent(Student student) throws Exception {
        for (int i = 0; i < listaStudentow.size(); i++) {
            if (listaStudentow.get(i) == student) {
                return i;
            }
        }
        throw new Exception("Nie ma takiego studenta");
    }

    //removePoints
    public void removeStudentIfLowPoints(Student student) {
        try {
            int index = findIndexOfStudent(student);
            if (listaStudentow.get(index).shouldBeReMoved()) {
                listaStudentow.remove(index);
                System.out.println("Student usuni??ty.");
                iloscStudentow = listaStudentow.size();
                return;
            }
            System.out.println("Student nie usuni??ty.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeCondition(Student student, StudentCondition studentCondition) {
        try {
            int index = findIndexOfStudent(student);
            listaStudentow.get(index).setStanStudenta(studentCondition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void summary() {
        System.out.println(listaStudentow);
    }

    public List<Student> sortByName() {
        listaStudentow.sort(Comparator.comparing(Student::getNazwisko));
        return listaStudentow;
    }

    public List<Student> sortByPoints() {
        Collections.sort(listaStudentow, (o1, o2) -> Double.compare(o2.getIloscPunktow(), o1.getIloscPunktow()));
        return listaStudentow;
    }

    public void countByCondition() {
        long chory = listaStudentow.stream().filter(a -> a.getStanStudenta() == StudentCondition.CHORY).count();
        long odrabiajacy = listaStudentow.stream().filter(a -> a.getStanStudenta() == StudentCondition.ODRABIAJ??CY).count();
        long nieobecny = listaStudentow.stream().filter(a -> a.getStanStudenta() == StudentCondition.NIEOBECNY).count();
        System.out.println("Chory: " + chory + ", odrabiajacy: " + odrabiajacy + ", nieobecny: " + nieobecny);

    }

    @Override
    public String toString() {
        return nazwaGrupy + ';' +listaStudentow+ ';' +maksymalnaIloscStudentow ;
    }

    public void setNazwaGrupy(String nazwaGrupy) {
        this.nazwaGrupy = nazwaGrupy;
    }

    public void setMaksymalnaIloscStudentow(int maksymalnaIloscStudentow) {
        this.maksymalnaIloscStudentow = maksymalnaIloscStudentow;
    }
}
