package com.hrproj.service.impl;

import com.hrproj.entity.Anketa;
import com.hrproj.entity.Log;
import com.hrproj.repository.AnketaRepository;
import com.hrproj.repository.LogRepository;
import com.hrproj.service.AnketaService;
import com.hrproj.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public Log addLog(Log log) {
        return logRepository.saveAndFlush(log);
    }

    @Override
    public void deleteById(long id) {
        logRepository.deleteById(id);
    }

    @Override
    public Log getByIdUser(Long id) {
        return logRepository.findByIdUser(id);
    }

    @Override
    public Log editLog(Log log) {
        return logRepository.saveAndFlush(log);
    }

    @Override
    public List<Log> getAll() {
        return logRepository.findAll();
    }

    public Log updateLog(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log getByEmailUser(String email) {
        return logRepository.findByEmailUser(email);
    }

    @Override
    public List<Log> findByDate(Date date) {
        return logRepository.findByDate(date);
    }

    @Override
    public List<Log> getByInfo(String info) {
        List<Log> logs = logRepository.findByInfo(info);
        for (Log log: logs) {
            String ip = "";
            String str = log.getInfo();
            int sch = 0;
            for (String retval : str.split(" ")) {
                if(sch == 1)
                    ip = retval;
                sch++;
            }
            log.setIp(ip);
        }
        return logs;
    }

    public List<Log> getChanges() {
        return logRepository.findByChanges();
    }
}


