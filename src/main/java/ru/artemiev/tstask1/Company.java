package ru.artemiev.tstask1;

import java.math.BigDecimal;
import java.util.*;

 public class Company {
    private ArrayList<Department> departments;

     public Company(){
        departments = new ArrayList<>();
        readFile();
    }

    private void readFile(){
        TextReader text = new TextReader();
        text.getInfoFromFile(this);
    }

    void addDepartment(String depName){
        departments.add(new Department(depName));
    }

    void addEmployee (int index, Employee employee){
        departments.get(index).addEmployee(employee);
    }

    public void checkTransfer(String[] args) {
        List<String> changeVariations, depInfo;
        ArrayList<List<String>> changeVariationForWriter = new ArrayList<>();
        depInfo = new ArrayList<>();
        for (int i = 0; i < departments.size(); i++) {
            departments.get(i).variantsAver();
            for (int j = 0; j < departments.get(i).variantsAver().size(); j++) {
                changeVariations = new ArrayList<>();
                BigDecimal averageVar = departments.get(i).averageCalculating(j, departments.get(i).variantsAver());
                for (int k = 0; k < departments.get(i).variantsAver().get(j).length; k++) {
                    changeVariations.add(departments.get(i).getEmployees().get(departments.get(i).variantsAver().get(j)[k] - 1).getFullInfo() +
                            " " + departments.get(i).getDep());
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
        writeFile(args, changeVariationForWriter, depInfo);
    }

    private void writeFile(String[] args, ArrayList<List<String>> changeVariationForWriter, List<String> depInfo){
        new TextWriter(args, changeVariationForWriter, depInfo);
    }
}
