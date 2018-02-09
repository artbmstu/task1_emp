package artemiev;

import java.math.BigDecimal;
import java.util.*;

public class Company {
    private BigDecimal[] salaries;
    private ArrayList<List<String>> empDatas, changeVariationForWriter;
    private ArrayList<Department> departments;
    private ArrayList<Employee> employees;
    private List<String> changeVariations, depInfo;
    private String[] args;
    private int[] nums;
    private BigDecimal averageVar;

    public Company(String[] args){
        this.args = args;
        readFile();
        createEmployees();
        createDepartments();
    }

    private void readFile(){
        empDatas = new ArrayList();
        TextReader text = new TextReader();
        text.getInfoFromFile();
        this.empDatas = text.getEmpDatas();
        this.salaries = text.getSalaries();
        this.nums = text.getNums();
    }

    private void createEmployees(){
        employees = new ArrayList<>();
        for (int i = 0; i < empDatas.size(); i++) {
            addEmployee(nums[i], empDatas.get(i).get(1), empDatas.get(i).get(2), empDatas.get(i).get(3), salaries[i]);
        }
    }

    private void addEmployee(int nums, String empName, String empSurname, String department, BigDecimal salary){
        employees.add(new Employee(nums, empName, empSurname, department, salary));
    }

    private void createDepartments(){
        HashSet<String> tempDepart = new HashSet<>();
        for (int i = 0; i < employees.size(); i++) {
            tempDepart.add(employees.get(i).getDepartment());
        }
        departments = new ArrayList<>();
        Iterator<String> iterator = tempDepart.iterator();
        String tempDep;
        int counter = 0;
        while (iterator.hasNext()){
            tempDep = iterator.next();
            departments.add(new Department(tempDep));
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getDepartment().equals(tempDep)){
                    departments.get(counter).addEmployee(employees.get(i));
                }
            }
            counter++;
        }
    }

    public void checkTransfer() {
        changeVariationForWriter = new ArrayList<>();
        depInfo = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            departments.get(i).variantsAver();
            for (int j = 0; j < departments.get(i).getAverVariants().size(); j++) {
                changeVariations = new ArrayList<>();
                averageVar = departments.get(i).averageCalculating(j);
                for (int k = 0; k < departments.get(i).getAverVariants().get(j).length; k++) {
                    changeVariations.add(departments.get(i).getEmployees().get(departments.get(i).getAverVariants().get(j)[k] - 1).getFullInfo());
                }
                if (averageVar.compareTo(departments.get(i).averSalary()) == -1) {
                    for (int k = 0; k < departments.size(); k++) {
                        if (averageVar.compareTo(departments.get(k).averSalary()) == 1) {
                            changeVariationForWriter.add(changeVariations);
                            depInfo.add(departments.get(k).getDep());
                        }
                    }
                }
            }
        }
    }

    public void writeFile(String[] args){
        TextWriter writer = new TextWriter(args, changeVariationForWriter, depInfo);
    }
}
