package Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassContainer {
    private  Map<String, Class> mapaGrup = new HashMap<>();
    private  List<Class> listaGrup = new ArrayList<Class>();

    public ClassContainer() {
        infill();
    }

    public List<Class> getListaGrup() {
        return listaGrup;
    }

    @Override
    public String toString() {
        return "ClassContainer{" +
                "listaGrup=" + mapaGrup +
                '}';
    }

    public Class getClassById(int id) {
        return listaGrup.get(id);
    }

    public Class getClassByName(String name) {
        return mapaGrup.get(name);
    }

    public Integer getGroupSize() {
        return listaGrup.size();
    }

    public void addClass(String nazwa, int pojemnosc) {
        Class temp = new Class(nazwa, pojemnosc);
        mapaGrup.put(nazwa, temp);
        listaGrup.add(temp);
        System.out.println(listaGrup);
//        System.out.println("TUUUU " + getGroupSize());
    }

    public void removeClass(String nazwa) {
        if (mapaGrup.containsKey(nazwa)) {
            mapaGrup.remove(nazwa);
            for (int i = 0; i < listaGrup.size(); i++) {
                if (listaGrup.get(i).getNazwaGrupy().equals(nazwa)) {
                    listaGrup.remove(i);
                    break;
                }
            }
            System.out.println("Usunieto klase");
        } else {
            System.out.println("Taka klasa nie istnieje!");
        }
    }

    public List<Class> findEmpty() {
        List<Class> listaPustychKlas = new ArrayList<Class>();
        for (Map.Entry<String, Class> klasa : mapaGrup.entrySet()) {
            if (klasa.getValue().getListaStudentow().size() == 0) {
                listaPustychKlas.add(klasa.getValue());
            }
        }
        return listaPustychKlas;
    }

    public void summary() {
        int zapelnienie = 0;
        for (Map.Entry<String, Class> klasa : mapaGrup.entrySet()) {
            zapelnienie = (int) ((double) klasa.getValue().getListaStudentow().size() / (double) klasa.getValue().getMaksymalnaIloscStudentow() * 100);
            System.out.println(klasa.getKey() + " zapelnienie = " + zapelnienie + "%");
        }
    }

    public void changeGroupName(int id, String newName) {
        String oldName = listaGrup.get(id).getNazwaGrupy();
        Class temp = mapaGrup.remove(oldName);
        mapaGrup.put(newName, temp);
        listaGrup.get(id).setNazwaGrupy(newName);
    }


    private void infill() {
        Student student0 = new Student("ZZZ", "ZZZ", StudentCondition.ODRABIAJĄCY, "1999", 1.1, "Pawlo");
        Student student1 = new Student("Pawel", "Kowalski", StudentCondition.CHORY, "1999", 1.1, "Pawlo");
        Student student6 = new Student("Pawel", "Kowalski", StudentCondition.NIEOBECNY, "1999", 1.1, "Pawlo");
        Student student2 = new Student("Pawel2", "Nowak", StudentCondition.ODRABIAJĄCY, "1999", 7.1, "Pawlo");
        Student student3 = new Student("Aawel", "Test", StudentCondition.CHORY, "1999", 3.1, "Pawlo");
        Student student4 = new Student("Bawel", "Litewka", StudentCondition.NIEOBECNY, "1999", 4.1, "Pawlo");
        Student student5 = new Student("Bawel", "Litewka", StudentCondition.ODRABIAJĄCY, "1999", 0, "Pawlo");


        addClass("Klasa 1", 10);
        addClass("Klasa 2", 20);
        addClass("Klasa 3", 30);
        addClass("Klasa 4", 40);

        getClassByName("Klasa 1").addStudent(student0);
        getClassByName("Klasa 1").addStudent(student1);
        getClassByName("Klasa 1").addStudent(student2);
        getClassByName("Klasa 1").addStudent(student2);
        getClassByName("Klasa 1").addStudent(student3);
        getClassByName("Klasa 1").addStudent(student5);


//        getClassByName("Klasa 1").sortByName();
//        System.out.println("Posortowane po imieniu: " + getClassByName("Klasa 1"));
//        getClassByName("Klasa 1").sortByPoints();
//        System.out.println("Posortowane po punktach: " + getClassByName("Klasa 1"));
        getClassByName("Klasa 2").addStudent(student4);
        System.out.println("Lista pustych: " + findEmpty());

        summary();

        System.out.println(getClassByName("Klasa 1").max());

        System.out.println("Wyszukane 1");
        System.out.println(getClassByName("Klasa 2").searchPartial("wka"));
        System.out.println("Wyszukane 2");
        System.out.println(getClassByName("Klasa 1").searchPartial("ski"));
        getClassByName("Klasa 1").removeStudentIfLowPoints(student5);
        getClassByName("Klasa 1").removeStudentIfLowPoints(student1);

    }
}
