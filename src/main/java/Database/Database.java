package Database;


import Student.Class;
import Student.ClassContainer;
import Student.StudentCondition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Database {

    public static void setClassesR(List<Class> classesR) {
        Database.classesR = classesR;
    }

    private static final String FILENAME = "database.txt";
    private static final String CSV_FILENAME = "database.csv";

    private static Database instance;

    private static ClassContainer classContainer = new ClassContainer();

    private static List<Class> classesR = classContainer.getListaGrup();
    private static List<StudentCondition> studentConditions;


    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }
    public void loadDatabase() {
        studentConditions = new ArrayList<>();
        studentConditions.addAll(List.of(StudentCondition.ODRABIAJĄCY, StudentCondition.CHORY, StudentCondition.ODRABIAJĄCY));

        classesR = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                try {
                    RegistryClass registryClass = (RegistryClass) in.readObject();
                    registryClass.setStudentStatus(StudentCondition.ODRABIAJĄCY);
//                    registryClass;
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveDatabase() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            for (Class obj : classesR) {
                out.writeObject(obj);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Saved objects to file " + FILENAME + ".");
    }

    public static void saveToCsvFile() {
        try (FileWriter writer = new FileWriter(CSV_FILENAME)) {
            List<Field> fields = Arrays.stream(Class.class.getDeclaredFields())
                    .filter(field -> field.getAnnotation(Column.class) != null)
                    .sorted(Comparator.comparingInt(f -> f.getAnnotation(Column.class).order()))
                    .toList();
            for (int i = 0; i < fields.size(); i++) {
                writer.append(fields.get(i).getAnnotation(Column.class).name());
                if (i + 1 == fields.size()) {
                    writer.append("\n");
                } else {
                    writer.append(",");
                }
            }
            for (Class registryClass : classesR) {
                System.out.println(fields.size() + "eee");
                for (int i = 0; i < fields.size(); i++) {
                    try {
                        fields.get(i).setAccessible(true);
                        writer.append(fields.get(i).get(registryClass).toString());
                        if (i + 1 == fields.size()) {
                            writer.append("\n");
                        } else {
                            writer.append(",");
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved objects to file " + CSV_FILENAME + ".");
    }


    public ObservableList<Class> getRegistryClasses() {
        return FXCollections.observableArrayList(classesR);
    }
}
