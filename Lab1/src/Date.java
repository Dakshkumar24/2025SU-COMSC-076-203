public class Date {
    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31");
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public void printFormat1() {
        System.out.printf("%02d/%02d/%02d\n", month, day, year % 100);
    }

    public void printFormat2() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.printf("%s %d, %d\n", months[month - 1], day, year);
    }

    public void printFormat3() {
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        System.out.printf("%d %s %d\n", day, months[month - 1], year);
    }
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
