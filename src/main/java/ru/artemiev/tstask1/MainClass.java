package ru.artemiev.tstask1;

public class MainClass {
    public static void main(String[] args) {
        Company company = new Company();
        company.checkTransfer();
        company.writeFile(args);
    }
}