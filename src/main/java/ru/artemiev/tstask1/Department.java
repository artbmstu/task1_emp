package ru.artemiev.tstask1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

class Department {
    private String dep;
    private int temp;
    private int[] mas;
    private BigDecimal sum;
    private ArrayList<Employee> employees = new ArrayList<>();

    Department(String dep){
        this.dep = dep;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    String getDep() {
        return dep;
    }

    BigDecimal averSalary(){
        BigDecimal sum = BigDecimal.ZERO;
        for (Employee i: getEmployees()) {
            sum = i.getSalary().add(sum);
        }
        return sum.divide(new BigDecimal(getEmployees().size()), 2, RoundingMode.HALF_UP);
    }

    void addEmployee(Employee employee){
        getEmployees().add(employee);
    }

    ArrayList<int[]> variantsAver(){
        ArrayList<int[]> aver = new ArrayList<>();
        for (int i = 1; i <= getEmployees().size(); i++) {
            temp = i;
            mas = new int[temp];
            recursion(aver,0, 0);
        }
        return aver;
    }

    ArrayList<int[]> recursion(ArrayList<int[]> aver, int pos, int maxUsed){
        if (pos == temp) {
            int[] temp = new int[mas.length];
            for (int i = 0; i < mas.length; i++) {
                temp[i] = mas[i];
            }
            aver.add(temp);
        } else {
            for (int i = maxUsed + 1; i <= getEmployees().size(); i++) {
                mas[pos] = i;
                recursion(aver,pos + 1, i);
            }
        }
        return aver;
    }

    BigDecimal averageCalculating(int i, ArrayList<int[]> averVariants){
        sum = new BigDecimal(0);
        for (int j = 0; j < averVariants.get(i).length; j++) {
            sum = sum.add(getEmployees().get(averVariants.get(i)[j]-1).getSalary());
        }
        return sum.divide((new BigDecimal(averVariants.get(i).length)), 2, RoundingMode.HALF_UP);
    }
}

