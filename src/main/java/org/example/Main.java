public class Main {
    public static void main(String[] args) {
        Student s = new Student("Ivan", "Petrov");

        for (int i = 1; i <= 12; i++) {
            s.addGrade(i);
        }

        int[] g = s.getGrades();
        System.out.println("Кол-во оценок: " + g.length); // 10
        System.out.print("Оценки: ");
        for (int x : g) System.out.print(x + " "); // должно быть 3..12
        System.out.println("\nСредний: " + s.getAverageGrade());
    }
}
