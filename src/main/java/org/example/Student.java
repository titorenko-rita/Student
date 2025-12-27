import java.util.Arrays;

public class Student implements Comparable<Student> {

    private String name;
    private String surname;

    // последние 10 оценок (может быть меньше, но не больше 10)
    private int[] grades = new int[0];

    public Student() { }

    public Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Student(String name, String surname, int[] grades) {
        this.name = name;
        this.surname = surname;
        setGrades(grades);
    }

    // --- getters/setters ---

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

    public int[] getGrades() {
        return Arrays.copyOf(grades, grades.length);
    }

    public void setGrades(int[] grades) {
        if (grades == null) {
            this.grades = new int[0];
            return;
        }

        if (grades.length > 10) {
            // берём последние 10
            this.grades = Arrays.copyOfRange(grades, grades.length - 10, grades.length);
        } else {
            this.grades = Arrays.copyOf(grades, grades.length);
        }
    }

    // --- business methods ---

    /**
     * Добавляет новую оценку.
     * Если оценок < 10 — добавляет в конец.
     * Если оценок == 10 — удаляет первую, сдвигает влево, новую добавляет в конец.
     */
    public void addGrade(int newGrade) {
        if (grades.length < 10) {
            int[] newArr = Arrays.copyOf(grades, grades.length + 1);
            newArr[newArr.length - 1] = newGrade;
            grades = newArr;
        } else {
            int[] newArr = new int[10];
            System.arraycopy(grades, 1, newArr, 0, 9);
            newArr[9] = newGrade;
            grades = newArr;
        }
    }

    /**
     * Средний балл как среднее арифметическое всех оценок.
     * Если оценок нет — 0.0
     */
    public double getAverageGrade() {
        if (grades.length == 0) return 0.0;

        int sum = 0;
        for (int g : grades) sum += g;
        return (double) sum / grades.length;
    }

    /**
     * Сортировка "по умолчанию": по фамилии, затем по имени (оба по возрастанию).
     */
    @Override
    public int compareTo(Student other) {
        if (other == null) return 1;

        int bySurname = safeString(this.surname).compareToIgnoreCase(safeString(other.surname));
        if (bySurname != 0) return bySurname;

        return safeString(this.name).compareToIgnoreCase(safeString(other.name));
    }

    private static String safeString(String s) {
        return s == null ? "" : s.trim();
    }

    @Override
    public String toString() {
        return safeString(surname) + " " + safeString(name) +
                " | avg=" + String.format("%.2f", getAverageGrade()) +
                " | grades=" + Arrays.toString(grades);
    }
}
