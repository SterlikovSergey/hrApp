package com.hrproj.service.impl;

import com.hrproj.entity.Employee;
import com.hrproj.entity.OrganizationStructure;
import com.hrproj.entity.Payroll;
import com.hrproj.repository.PayrollRepository;
import com.hrproj.service.PayrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
public class PayrollServiceImpl implements PayrollService {
    @Autowired
    private PayrollRepository payrollRepository;

    @Override
    public Payroll addPayroll(Payroll payroll) {
        return payrollRepository.saveAndFlush(payroll);
    }

    @Override
    public void deleteById(long id) {
        payrollRepository.deleteById(id);
    }

    @Override
    public List<Payroll> getByIdEmployee(Long id_employee) {
        return payrollRepository.findByIdEmployee(id_employee);
    }

    @Override
    public Payroll getByDate(Date date) {
        return payrollRepository.findByDate(date);
    }

    @Override
    public Payroll editPayroll(Payroll payroll) {
        return payrollRepository.saveAndFlush(payroll);
    }

    @Override
    public List<Payroll> getAll() {
        return payrollRepository.findAll();
    }


    public Double[] calcMiddlePayroll() {
        Double[] q = new Double[12];
        int[] k = new int[12];
        for (int i = 0; i< q.length; i++) {
            q[i] = 0.0;
        }
        for (int i = 0; i< q.length; i++) {
            k[i] = 0;
        }
        List<Payroll> payrolls = getAll();

        LocalDate dateNow = LocalDate.now();
        for (Payroll payroll: payrolls) {
            LocalDate date = payroll.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(date.getYear()==LocalDate.now().getYear()){
                if(date.getMonth().getValue()==1){
                    q[0]+=payroll.getTotal();
                    k[0]++;
                }
                else if(date.getMonth().getValue()==2){
                    q[1]+=payroll.getTotal();
                    k[1]++;
                }
                else if(date.getMonth().getValue()==3){
                    q[2]+=payroll.getTotal();
                    k[2]++;
                }
                else if(date.getMonth().getValue()==4){
                    q[3]+=payroll.getTotal();
                    k[3]++;
                }
                else if(date.getMonth().getValue()==5){
                    q[4]+=payroll.getTotal();
                    k[4]++;
                }
                else if(date.getMonth().getValue()==6){
                    q[5]+=payroll.getTotal();
                    k[5]++;
                }
                else if(date.getMonth().getValue()==7){
                    q[6]+=payroll.getTotal();
                    k[6]++;
                }
                else if(date.getMonth().getValue()==8){
                    q[7]+=payroll.getTotal();
                    k[7]++;
                }
                else if(date.getMonth().getValue()==9){
                    q[8]+=payroll.getTotal();
                    k[8]++;
                }
                else if(date.getMonth().getValue()==10){
                    q[9]+=payroll.getTotal();
                    k[9]++;
                }
                else if(date.getMonth().getValue()==11){
                    q[10]+=payroll.getTotal();
                    k[10]++;
                }
                else if(date.getMonth().getValue()==12){
                    q[11]+=payroll.getTotal();
                    k[11]++;
                }
            }
        }

        for (int i = 0; i< q.length; i++) {
            if(k[i]!=0)
                q[i]/= k[i];
        }

        return q;
    }

    public List<Payroll> getMonthReport(int month) {
        List<Payroll> payrolls = getAll();
        List<Payroll> totalPayrolls = new ArrayList<>();
        for (int i = 0; i<payrolls.size();i++) {
            if (payrolls.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == LocalDate.now().getYear()){
                if (payrolls.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().getValue() == month){
                    if(totalPayrolls.size()==0){
                        Payroll payroll = new Payroll();
                        Employee employee = new Employee();
                        OrganizationStructure organizationStructure = new OrganizationStructure();
                        organizationStructure.setDepartment(payrolls.get(i).getEmployee().getPost().getDepartment());
                        organizationStructure.setPost(payrolls.get(i).getEmployee().getPost().getPost());
                        employee.setPost(organizationStructure);
                        payroll.setEmployee(employee);
                        payroll.setAccrued(payrolls.get(i).getAccrued());
                        payroll.setAllowances(payrolls.get(i).getAllowances());
                        payroll.setHold(payrolls.get(i).getHold());
                        payroll.setTotal(payrolls.get(i).getTotal());
                        totalPayrolls.add(payroll);
                    }
                    else {
                        boolean t = false;
                        for (Payroll p: totalPayrolls) {
                            if(p.getEmployee().getPost().getDepartment().equals(payrolls.get(i).getEmployee().getPost().getDepartment()) && p.getEmployee().getPost().getPost().equals(payrolls.get(i).getEmployee().getPost().getPost())) {
                                p.setAccrued(p.getAccrued() + payrolls.get(i).getAccrued());
                                p.setAllowances(p.getAllowances() + payrolls.get(i).getAllowances());
                                p.setHold(p.getHold() + payrolls.get(i).getHold());
                                p.setTotal(p.getTotal() + +payrolls.get(i).getTotal());
                                t = true;
                            }
                        }
                        if(!t){
                            Payroll payroll = new Payroll();
                            Employee employee = new Employee();
                            OrganizationStructure organizationStructure = new OrganizationStructure();
                            organizationStructure.setDepartment(payrolls.get(i).getEmployee().getPost().getDepartment());
                            organizationStructure.setPost(payrolls.get(i).getEmployee().getPost().getPost());
                            employee.setPost(organizationStructure);
                            payroll.setEmployee(employee);
                            payroll.setAccrued(payrolls.get(i).getAccrued());
                            payroll.setAllowances(payrolls.get(i).getAllowances());
                            payroll.setHold(payrolls.get(i).getHold());
                            payroll.setTotal(payrolls.get(i).getTotal());
                            totalPayrolls.add(payroll);
                        }
                    }
                }
            }
        }
        return totalPayrolls;
    }

    public int getAmountPeople(int month) {
        List<Payroll> payrolls = getAll();
        int amount = 0;
        for (int i = 0; i<payrolls.size();i++) {
            if (payrolls.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == LocalDate.now().getYear()){
                if (payrolls.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().getValue() == month){
                        amount++;
                }
            }
        }
        return amount;
    }

    public List<Integer> getAmountPeoplePost(int month) {
        List<Payroll> payrolls = getAll();
        List<Payroll> totalPayrolls = new ArrayList<>();
        List<Integer> amount = new ArrayList<>();
        for (int i = 0; i<payrolls.size();i++) {
            if (payrolls.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() == LocalDate.now().getYear()){
                if (payrolls.get(i).getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth().getValue() == month){
                    if(totalPayrolls.size()==0){
                        Payroll payroll = new Payroll();
                        Employee employee = new Employee();
                        OrganizationStructure organizationStructure = new OrganizationStructure();
                        organizationStructure.setDepartment(payrolls.get(i).getEmployee().getPost().getDepartment());
                        organizationStructure.setPost(payrolls.get(i).getEmployee().getPost().getPost());
                        employee.setPost(organizationStructure);
                        payroll.setEmployee(employee);
                        payroll.setAccrued(payrolls.get(i).getAccrued());
                        payroll.setAllowances(payrolls.get(i).getAllowances());
                        payroll.setHold(payrolls.get(i).getHold());
                        payroll.setTotal(payrolls.get(i).getTotal());
                        totalPayrolls.add(payroll);
                        amount.add(1);
                    }
                    else {
                        boolean t = false;
                        for (int j = 0; j<totalPayrolls.size();j++) {
                            if(totalPayrolls.get(j).getEmployee().getPost().getDepartment().equals(payrolls.get(i).getEmployee().getPost().getDepartment()) && totalPayrolls.get(j).getEmployee().getPost().getPost().equals(payrolls.get(i).getEmployee().getPost().getPost())) {
                                totalPayrolls.get(j).setAccrued(totalPayrolls.get(j).getAccrued() + payrolls.get(i).getAccrued());
                                totalPayrolls.get(j).setAllowances(totalPayrolls.get(j).getAllowances() + payrolls.get(i).getAllowances());
                                totalPayrolls.get(j).setHold(totalPayrolls.get(j).getHold() + payrolls.get(i).getHold());
                                totalPayrolls.get(j).setTotal(totalPayrolls.get(j).getTotal() + +payrolls.get(i).getTotal());
                                amount.set(j,amount.get(j)+1);
                                t = true;
                            }
                        }
                        if(!t){
                            Payroll payroll = new Payroll();
                            Employee employee = new Employee();
                            OrganizationStructure organizationStructure = new OrganizationStructure();
                            organizationStructure.setDepartment(payrolls.get(i).getEmployee().getPost().getDepartment());
                            organizationStructure.setPost(payrolls.get(i).getEmployee().getPost().getPost());
                            employee.setPost(organizationStructure);
                            payroll.setEmployee(employee);
                            payroll.setAccrued(payrolls.get(i).getAccrued());
                            payroll.setAllowances(payrolls.get(i).getAllowances());
                            payroll.setHold(payrolls.get(i).getHold());
                            payroll.setTotal(payrolls.get(i).getTotal());
                            totalPayrolls.add(payroll);
                            amount.add(1);
                        }
                    }
                }
            }
        }
        return amount;
    }
}
