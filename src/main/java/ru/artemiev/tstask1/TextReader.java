package ru.artemiev.tstask1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TextReader {
    private ArrayList<String> employees;
    private ArrayList<List<String>> empDatas;
    private List<String> datas;
    private BigDecimal[] salaries;
    private int[] nums;

    ArrayList<List<String>> getEmpDatas() {
        return empDatas;
    }

    BigDecimal[] getSalaries() {
        return salaries;
    }

    int[] getNums() {
        return nums;
    }

    void getInfoFromFile(){
        try {
            File file = new File("employees.txt");
            FileReader reader = new FileReader(file);
            BufferedReader in = new BufferedReader(reader);
            String string;
            employees = new ArrayList<>();
            while ((string = in.readLine()) != null) {
                employees.add(string);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        datas = new ArrayList<>();
        empDatas = new ArrayList<>();
        nums = new int[employees.size()];
        for (String emp: employees) {
            datas = Arrays.asList(emp.trim().split(" "));
            empDatas.add(datas);
        }
        checkNumericDatas();
    }
    private void checkNumericDatas(){
        try
        {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator('.');
            DecimalFormat decimalFormat = new DecimalFormat("", symbols);
            decimalFormat.setParseBigDecimal(true);
            salaries = new BigDecimal[employees.size()];
            for (int i = 0; i < employees.size(); i++) {
                salaries[i] = (BigDecimal) decimalFormat.parse(empDatas.get(i).get(4));
                if (salaries[i].compareTo(BigDecimal.ZERO) < 0) throw new NumberFormatException();
            }
        }
        catch(ParseException pe)
        {
            System.out.println("В исходном файле неправильный тип данных зарплаты");
            System.exit(0);
        }
        catch (NumberFormatException nfe){
            System.out.println("Зарплата меньше 0. Исправьте данные в исходном файле");
            System.exit(0);
        }
        for (int i = 0; i < employees.size(); i++) {
            nums[i] =  Integer.parseInt(empDatas.get(i).get(0));
        }
    }
}
