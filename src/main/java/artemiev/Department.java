package artemiev;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Department {
    private ArrayList<Employee> employees;
    private String dep;
    private ArrayList<int[]> averVariants;
    private int temp;
    private int[] mas;
    private BigDecimal sum;

    public Department(String dep){
        employees = new ArrayList<>();
        this.dep = dep;
    }

    public ArrayList<int[]> getAverVariants() {
        return averVariants;
    }

    public String getDep() {
        return dep;
    }
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public BigDecimal averSalary(){
        BigDecimal sum = BigDecimal.ZERO;
        for (Employee i: employees) {
            sum = i.getSalary().add(sum);
        }
        return sum.divide(new BigDecimal(employees.size()));
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void variantsAver(){
        averVariants = new ArrayList<>();
        for (int i = 1; i <= employees.size(); i++) {
            temp = i;
            mas = new int[temp];
            recursion(0, 0);
        }
    }

    private void recursion(int pos, int maxUsed){
        if (pos == temp) {
            int[] temp = new int[mas.length];
            for (int i = 0; i < mas.length; i++) {
                temp[i] = mas[i];
            }
            averVariants.add(temp);
        } else {
            for (int i = maxUsed + 1; i <= employees.size(); i++) {
                mas[pos] = i;
                recursion(pos + 1, i);
            }
        }
    }

    public BigDecimal averageCalculating(int i){
        sum = new BigDecimal(0);
        for (int j = 0; j < averVariants.get(i).length; j++) {
            sum = sum.add(employees.get(averVariants.get(i)[j]-1).getSalary());
        }
        return sum.divide((new BigDecimal(averVariants.get(i).length)), 2);
    }
}

