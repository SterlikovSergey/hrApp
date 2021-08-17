package com.hrproj.service.impl;

import com.hrproj.entity.Candidate;
import com.hrproj.entity.Employee;
import com.hrproj.entity.OrganizationStructure;
import com.hrproj.entity.User;
import com.hrproj.entity.enums.RoleEnum;
import com.hrproj.repository.EmployeeRepository;
import com.hrproj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired OrganizationStructureServiceImpl organizationStructureService;
    @Autowired
    UserServiceImpl userService;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee getByIdE(long id) {
        return employeeRepository.findByIDE(id);
    }

    @Override
    public Employee getBySurname(String surname) {
        return employeeRepository.findBySurname(surname);
    }
    @Override
    public Employee getByName(String name) {
        return employeeRepository.findByName(name);
    }
    @Override
    public Employee getByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    @Override
    public Employee editEmployee(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllHr() {
        return employeeRepository.findAllHr();
    }

    public Employee getInfoEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = getByEmail(auth.getName());
        return employee;
    }
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Double[] genderPercent(List<Employee> employeeList) {
        Double[] q = new Double[2];
        for (int i = 0; i< q.length; i++) {
            q[i] = 0.0;
        }
        for (Employee employee: employeeList) {
            if(employee.getGender().equals("female"))
                q[0]++;
            else
                q[1]++;
        }
        for (int i = 0; i< q.length; i++) {
            q[i] = q[i] / employeeList.size() * 100;
        }
        return q;
    }

    public void saveRegistrEmployee(String department, String post, int rate, String datePasp, String seria, String numpassport, String idP, Employee employee) {

        User user = userService.getByUsername(employee.getEmail());
        OrganizationStructure organizationStructure = organizationStructureService.getByDepartmentAndPost(department, post);
        if(organizationStructure==null) {
            organizationStructure = new OrganizationStructure();
            organizationStructure.setDepartment(department);
            organizationStructure.setPost(post);
            organizationStructure.setSalary(1000);
            organizationStructureService.addOrganizationStructure(organizationStructure);
            organizationStructure = organizationStructureService.getByDepartmentAndPost(department, post);
            if(post.equals("hr")) {
                user.setRole(RoleEnum.ROLE_HR);
                userService.editUser(user);
            }

        }
        try {
        employee.setPasp_id(idP);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date datePasp1= null;

            datePasp1 = format.parse(datePasp);

        employee.setPasp_date(datePasp1);
        employee.setPasp_num(numpassport);
        employee.setPost(organizationStructure);
        editEmployee(employee);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Double[] getAgeGroup(List<Employee> employeeList) {
        Double[] q = new Double[5];
        for (int i = 0; i< q.length; i++) {
            q[i] = 0.0;
        }
        for (Employee employee: employeeList) {
            LocalDate startDate = employee.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//            LocalDate endDate = LocalDate.parse("05.03.2013", formatter);
            Period period = Period.between(startDate, LocalDate.now());
            if(period.getYears()<20)
                q[0]++;
            else if(period.getYears()<30 && period.getYears()>=20)
                q[1]++;
            else if(period.getYears()>=30 && period.getYears()<40)
                q[2]++;
            else if(period.getYears()>=40 && period.getYears()<50)
                q[3]++;
            else if(period.getYears()>=50)
                q[4]++;
        }
//        for (int i = 0; i< q.length; i++) {
//            q[i] = q[i] / employeeList.size() * 100;
//        }
        return q;
    }
}
