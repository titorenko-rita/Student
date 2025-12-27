import java.util.Arrays;
import java.util.Comparator;

public class StudentUtils {

    private StudentUtils() { }

    /** Сортировка массива студентов по фамилии (и имени вторым ключом) */
    public static void sortBySurname(Student[] students) {
        if (students == null) return;
        Arrays.sort(students); // использует Student.compareTo()
    }

    /** Сортировка "лучших" студентов: по среднему баллу по убыванию */
    public static void sortByBestAverage(Student[] students) {
        if (students == null) return;

        Arrays.sort(students, Comparator
                .comparingDouble(Student::getAverageGrade)
                .reversed()
                .thenComparing(Student::getSurname, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER))
                .thenComparing(Student::getName, Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER))
        );
    }

    /** Возвращает лучшего студента по среднему баллу (если массив пуст — null) */
    public static Student getBestStudent(Student[] students) {
        if (students == null || students.length == 0) return null;

        Student best = students[0];
        for (int i = 1; i < students.length; i++) {
            if (students[i] == null) continue;
            if (best == null || students[i].getAverageGrade() > best.getAverageGrade()) {
                best = students[i];
            }
        }
        return best;
    }
}
