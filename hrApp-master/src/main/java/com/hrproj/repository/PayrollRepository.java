package com.hrproj.repository;

import com.hrproj.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Long> {
    @Query("select a from Payroll a where a.employee.id = :id_employee")
    List<Payroll> findByIdEmployee(@Param("id_employee") Long id_employee);
    @Query("select a from Payroll a where a.date = :date")
    Payroll findByDate(@Param("date") Date date);
}
