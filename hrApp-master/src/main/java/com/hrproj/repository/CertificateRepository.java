package com.hrproj.repository;

import com.hrproj.entity.Certificate;
import com.hrproj.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    @Query("select t from Certificate t where t.employee.id = :id_employee")
    List<Certificate> findByIdEmployee(@Param("id_employee") Long idEmployee);
    @Query("select t from Certificate t where t.type = :type")
    List<Certificate> findByType(@Param("type") String type);
    @Query("select t from Certificate t where t.type = :type and t.employee.id = :id_employee")
    List<Certificate> findByTypeAndIdEmployee(String type, long id_employee);
    @Query("select t from Certificate t where t.id = :id")
    Certificate getById(@Param("id") Long id);
}
