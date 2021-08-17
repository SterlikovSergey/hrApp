package com.hrproj.service;

import com.hrproj.entity.Anketa;
import com.hrproj.entity.Log;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface LogService {
    Log addLog(Log log);
    void deleteById(long id);
    Log getByIdUser(Long id);
    List<Log> getByInfo(String info);
    Log getByEmailUser(String email);
    List<Log> getAll();
    List<Log> findByDate(Date date);
    Log editLog(Log log);
}
