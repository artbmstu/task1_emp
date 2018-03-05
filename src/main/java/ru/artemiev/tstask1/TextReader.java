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
            Set<String> departments = new HashSet<>();
            String string;
            int index = -1;
            while ((string = in.readLine()) != null) {
                List<String> data = Arrays.asList(string.split(" ", 4));
                int tempDepSize = departments.size();
                departments.add(data.get(1));
                if (tempDepSize < departments.size()){
                    company.addDepartment(data.get(1));
                    index++;
                }
                for (String dep:
                     departments) {
                    if (data.get(1).equals(dep)) {
                        company.addEmployee(index, new Employee(Integer.parseInt(data.get(0)), checkNumericData(data.get(2)), data.get(3)));
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }
    private BigDecimal checkNumericData(String salary){
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
        }
        catch (NumberFormatException nfe){
            System.out.println("Зарплата меньше 0. Исправьте данные в исходном файле");
        }
        return decSalary;
    }
}
