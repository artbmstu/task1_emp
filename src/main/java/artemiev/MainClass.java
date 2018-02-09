package artemiev;

public class MainClass {
    public static void main(String[] args) {
        Company company = new Company(args);
        company.checkTransfer();
        company.writeFile(args);
    }
}