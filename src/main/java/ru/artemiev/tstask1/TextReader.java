package ru.artemiev.tstask1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

class TextReader {

    void getInfoFromFile(Company company){
        try {
            File file = new File("employees.txt");
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            HashSet<String> departments = new HashSet<>();
            String string;
            int index = -1;
            while ((string = in.readLine()) != null) {
                List<String> datas = Arrays.asList(string.split(" ", 4));
                int tempDepSize = departments.size();
                departments.add(datas.get(1));
                if (tempDepSize < departments.size()){
                    company.addDepartment(datas.get(1));
                    index++;
                }
                for (String dep:
                     departments) {
                    if (datas.get(1).equals(dep)) {
                        company.addEmployee(index, new Employee(Integer.parseInt(datas.get(0)), checkNumericDatas(company, datas.get(2)), datas.get(3)));
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }
    private BigDecimal checkNumericDatas(Company company, String salary){
        BigDecimal decSalary = null;
        try
        {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("", symbols);
            decimalFormat.setParseBigDecimal(true);
            decSalary = (BigDecimal) decimalFormat.parse(salary);
            if (decSalary.compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
            return decSalary;
        }
        catch(ParseException pe)
        {
            System.out.println("В исходном файле неправильный тип данных зарплаты");
//            updateInFile(company);
        }
        catch (NumberFormatException nfe){
            System.out.println("Зарплата меньше 0. Исправьте данные в исходном файле");
//            updateInFile(company);
        }
        return decSalary;
    }

    private void updateInFile(Company company){
        System.out.println("После обновления файла с данными введите в консоль \"да\". \nДля выхода из программы введите \"выход\"");
        Scanner sc = new Scanner(System.in);
        String inComm = sc.nextLine();
        if (inComm.equals("выход")) System.exit(0);
        else getInfoFromFile(company);
    }
}
