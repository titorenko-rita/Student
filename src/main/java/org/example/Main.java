import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        // 1) Создаем студентов
        Student s1 = new Student("Ivan", "Petrov");
        Student s2 = new Student("Anna", "Ivanova");
        Student s3 = new Student("Petr", "Sidorov");

        // 2) Демонстрация setGrades / getGrades
        s1.setGrades(new int[]{5, 4, 5});
        s2.setGrades(new int[]{5, 5, 5});

        // Sidorov сделаем "лучше", чтобы сортировки отличались от сортировки по фамилии
        s3.setGrades(new int[]{5, 5, 4, 5}); // средний 4.75 (выше, чем у Petrov 4.67)

        System.out.println("== Демонстрация getter/setter (grades) ==");
        System.out.println("Petrov grades:  " + Arrays.toString(s1.getGrades()));
        System.out.println("Ivanova grades: " + Arrays.toString(s2.getGrades()));
        System.out.println("Sidorov grades: " + Arrays.toString(s3.getGrades()));

        // 3) Демонстрация addGrade + сдвига при заполнении 10 оценок
        System.out.println("\n== Демонстрация addGrade и сдвига массива до 10 ==");
        Student s4 = new Student("Max", "ShiftTest");
        for (int i = 1; i <= 12; i++) {
            s4.addGrade(i);
        }
        // Должно остаться 10 последних: 3..12
        System.out.println("ShiftTest grades (ожидается 3..12): " + Arrays.toString(s4.getGrades()));

        // 4) Демонстрация среднего балла
        System.out.println("\n== Демонстрация getAverageGrade ==");
        System.out.println("Petrov avg:  " + s1.getAverageGrade());
        System.out.println("Ivanova avg: " + s2.getAverageGrade());
        System.out.println("Sidorov avg: " + s3.getAverageGrade());

        // 5) Массив студентов (для сортировок)
        Student[] students = {s1, s2, s3};

        // 6) Сортировка по фамилии
        System.out.println("\n== Сортировка по фамилии ==");
        StudentUtils.sortBySurname(students);
        printStudents(students);

        // 7) Сортировка по лучшему среднему
        System.out.println("\n== Сортировка по лучшему среднему (убывание) ==");
        StudentUtils.sortByBestAverage(students);
        printStudents(students);

        // 8) Лучший студент
        System.out.println("\n== Лучший студент ==");
        Student best = StudentUtils.getBestStudent(students);
        System.out.println(best);
    }

    private static void printStudents(Student[] students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
