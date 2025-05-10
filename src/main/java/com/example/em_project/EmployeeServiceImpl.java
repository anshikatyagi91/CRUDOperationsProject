package com.example.em_project;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

   // List<Employee> employees= new ArrayList<>();
    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee,employeeEntity);
        employeeRepository.save(employeeEntity);
        //employees.add(employee);
        return "Saved Successfully ";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity emp = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(emp,employee);
        return employee;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeeList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeList){

            Employee emp = new Employee();
            emp.setId(employeeEntity.getId());
            emp.setName(employeeEntity.getName());
            emp.setPhone(employeeEntity.getPhone());
            emp.setEmail(employeeEntity.getEmail());


            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntity emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
       // employees.remove(id);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity exestingEmployee = employeeRepository.findById(id).get();
        exestingEmployee.setEmail(employee.getEmail());
        exestingEmployee.setName(employee.getName());
        exestingEmployee.setPhone(employee.getPhone());

        employeeRepository.save(exestingEmployee);
        return "Update Successfully ";
    }


}
