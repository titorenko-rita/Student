import java.util.Arrays;

public class Student {

    private String name;
    private String surname;

    // Храним не более 10 последних оценок
    private int[] grades = new int[0];

    public Student() {
    }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, int[] grades) {
        this.name = name;
        this.surname = surname;
        setGrades(grades); // используем сеттер с ограничением
    }

    // --- getters/setters for name, surname ---

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    // --- getters/setters for grades ---

    public int[] getGrades() {
        return Arrays.copyOf(grades, grades.length);
    }

    public void setGrades(int[] grades) {
        if (grades == null) {
            this.grades = new int[0];
            return;
        }

        if (grades.length > 10) {
            // Берем последние 10
            this.grades = Arrays.copyOfRange(grades, grades.length - 10, grades.length);
        } else {
            this.grades = Arrays.copyOf(grades, grades.length);
        }
    }

    /**
     * Добавляет новую оценку.
     * Если оценок меньше 10 — просто добавляем в конец.
     * Если уже 10 — удаляем самую первую и сдвигаем влево, добавляя новую в конец.
     */
    public void addGrade(int newGrade) {
        if (grades.length < 10) {
            int[] newArr = Arrays.copyOf(grades, grades.length + 1);
            newArr[newArr.length - 1] = newGrade;
            grades = newArr;
        } else {
            int[] newArr = new int[10];
            // сдвиг влево: [1..9] -> [0..8]
            System.arraycopy(grades, 1, newArr, 0, 9);
            newArr[9] = newGrade;
            grades = newArr;
        }
    }

    /**
     * Средний балл (среднее арифметическое всех оценок в массиве grades).
     * Если оценок нет — возвращаем 0.0
     */
    public double getAverageGrade() {
        if (grades.length == 0) return 0.0;

        int sum = 0;
        for (int g : grades) {
            sum += g;
        }
        return (double) sum / grades.length;
    }
}
