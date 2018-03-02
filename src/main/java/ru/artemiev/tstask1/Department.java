package ru.artemiev.tstask1;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

class Department {
    private String dep;
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
        try {
            for (Employee i: getEmployees()) {
                sum = i.getSalary().add(sum);
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка null зарплаты (рассчет средней зарплаты в департаменте)");
        }
        return sum.divide(new BigDecimal(getEmployees().size()), 2, RoundingMode.HALF_UP);
    }

    void addEmployee(Employee employee){
        getEmployees().add(employee);
    }

    ArrayList<int[]> variantsAver(){
        ArrayList<int[]> aver = new ArrayList<>();
        int temp;
        int[] mas;
        for (int i = 1; i <= getEmployees().size(); i++) {
            temp = i;
            mas = new int[temp];
            recursion(aver,0, 0, temp, mas);
        }
        return aver;
    }

    ArrayList<int[]> recursion(ArrayList<int[]> aver, int pos, int maxUsed, int temporary, int[] mas){
        if (pos == temporary) {
            int[] temp = mas.clone();
            aver.add(temp);
        } else {
            for (int i = maxUsed + 1; i <= getEmployees().size(); i++) {
                mas[pos] = i;
                recursion(aver,pos + 1, i, temporary, mas);
            }
        }
        return aver;
    }

    BigDecimal averageCalculating(int i, ArrayList<int[]> averVariants){
        BigDecimal sum = new BigDecimal(0);
        try {
            for (int j = 0; j < averVariants.get(i).length; j++) {
                sum = sum.add(getEmployees().get(averVariants.get(i)[j]-1).getSalary());
            }
        } catch (NullPointerException e) {
            System.out.println("Ошибка. При рассчете используется зарплата null. Исправьте тип данных в исходном файле");
        }
        return sum.divide((new BigDecimal(averVariants.get(i).length)), 2, RoundingMode.HALF_UP);
    }
}

