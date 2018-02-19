package ru.artemiev.tstask1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

class TextReader {
    private Company company;

    void getInfoFromFile(Company company){
        try {
            this.company = company;
            File file = new File("employees.txt");
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            HashSet<String> departments = new HashSet<>();
            String string;
            int index = -1;
            while ((string = in.readLine()) != null) {
                List<String> datas = Arrays.asList(string.trim().split(" "));
                int tempDepSize = departments.size();
                departments.add(datas.get(3));
                if (tempDepSize < departments.size()){
                    company.addDepartment(datas.get(3));
                    index++;
                }
                for (String dep:
                     departments) {
                    if (datas.get(3).equals(dep)) {
                        company.addEmployee(index, new Employee(Integer.parseInt(datas.get(0)), datas.get(1), datas.get(2), checkNumericDatas(datas.get(4))));
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла");
        }
    }
    private BigDecimal checkNumericDatas(String salary){
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
            updateInFile();
        }
        catch (NumberFormatException nfe){
            System.out.println("Зарплата меньше 0. Исправьте данные в исходном файле");
            updateInFile();
        }
        return decSalary;
    }

    private void updateInFile(){
        System.out.println("После обновления файла с данными введите в консоль \"да\". \n Для выхода из программы введите \"нет\"");
        Scanner sc = new Scanner(System.in);
        if (sc.nextLine().equals("да")) getInfoFromFile(company);
        else if (sc.nextLine().equals("нет")) System.exit(0);
    }
}
