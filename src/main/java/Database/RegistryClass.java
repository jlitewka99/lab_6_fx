package Database;

import Student.StudentCondition;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class RegistryClass implements Serializable {

    @Column(name = "nazwaGrupy", order = 1)
    private String className;
    private List<Grade> grades;
    @Column(name = "iloscPunktow", order = 3)
    private double iloscPunktow;
    @Column(name = "status", order = 2)
    private transient StudentCondition studentStatus;

    public RegistryClass(String className,  double iloscPunktow, StudentCondition studentStatus, List<Grade> grades) {
        this.className = className;
        this.grades = grades;
        this.studentStatus = studentStatus;
        this.iloscPunktow = iloscPunktow;
    }
}
