public class Main {
    public static void main(String[] args) {
        try {
            Date date = new Date(7, 15, 2025);
            date.printFormat1(); // 07/15/25
            date.printFormat2(); // July 15, 2025
            date.printFormat3(); // 15 July 2025
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date: " + e.getMessage());
        }
    }
}
