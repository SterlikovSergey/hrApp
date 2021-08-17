package com.hrproj.service.impl;

import com.hrproj.entity.Certificate;
import com.hrproj.entity.Timetable;
import com.hrproj.repository.CertificateRepository;
import com.hrproj.repository.TimetableRepository;
import com.hrproj.service.CertificateService;
import com.hrproj.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public Certificate addCertificate(Certificate certificate) {
        return certificateRepository.saveAndFlush(certificate);
    }

    @Override
    public void deleteById(long id) {
        certificateRepository.deleteById(id);
    }

    @Override
    public List<Certificate> getByIdEmployee(Long idEmployee) {
        return certificateRepository.findByIdEmployee(idEmployee);
    }

    @Override
    public Certificate getById(Long id) {
        return certificateRepository.getById(id);
    }

    @Override
    public Certificate editCertificate(Certificate certificate) {
        return certificateRepository.saveAndFlush(certificate);
    }

    @Override
    public List<Certificate> getByType(String type) {
        return certificateRepository.findByType(type);
    }

    @Override
    public List<Certificate> getByTypeAndIdEmployee(String type, long id) {
        return certificateRepository.findByTypeAndIdEmployee(type,id);
    }


    @Override
    public List<Certificate> getAll() {
        return certificateRepository.findAll();
    }
}
