package com.hrproj.service;

import com.hrproj.entity.Certificate;
import com.hrproj.entity.Timetable;

import java.util.List;

public interface CertificateService {
    Certificate addCertificate(Certificate certificate);
    void deleteById(long id);
    List<Certificate> getByIdEmployee(Long idEmployee);
    Certificate getById(Long id);
    List<Certificate> getByType(String type);
    List<Certificate> getByTypeAndIdEmployee(String type, long id);
    Certificate editCertificate(Certificate certificate);
    List<Certificate> getAll();
}
