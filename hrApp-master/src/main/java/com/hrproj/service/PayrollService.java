package com.hrproj.service;

import com.hrproj.entity.Payroll;

import java.sql.Date;
import java.util.List;

public interface PayrollService {
    Payroll addPayroll(Payroll payroll);
    void deleteById(long id);
    List<Payroll> getByIdEmployee(Long id_employee);
    Payroll getByDate(Date date);
    Payroll editPayroll(Payroll payroll);
    List<Payroll> getAll();
}
