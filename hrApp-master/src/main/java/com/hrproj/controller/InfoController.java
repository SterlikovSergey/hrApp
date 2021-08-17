package com.hrproj.controller;

import com.hrproj.entity.Log;
import com.hrproj.entity.User;
import com.hrproj.service.impl.EmployeeServiceImpl;
import com.hrproj.service.impl.LogServiceImpl;
import com.hrproj.service.impl.MailSenderServiceIml;
import com.hrproj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

@Controller
public class InfoController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MailSenderServiceIml mailSender;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    LogServiceImpl logService;


    @GetMapping("/inf/{code}")
    public String inf(Model model, @PathVariable String code){
        boolean isActivated = userService.activatedUser(code);
        if(isActivated) {
            Log log = new Log();
            log.setInfo("Заполнение формы сотрудника");
            log.setUser(userService.getByActivationCode(code));
            log.setTime(new Date());
            logService.addLog(log);
            return "dop_inf";
        }
        else
            return "index";
    }

    @PostMapping("/inf/{code}")
    public String addInf(@RequestParam("department") String department,
                         @RequestParam("post") String post,
                         @RequestParam("rate") int rate,
                         @RequestParam("datePasp") String datePasp,
                         @RequestParam("seria") String seria,
                         @RequestParam("numpassport") String numpassport,
                         @RequestParam("idP") String idP,
                         @PathVariable String code, Model model) {
        employeeService.saveRegistrEmployee(department, post, rate, datePasp, seria, numpassport, idP, employeeService.getByEmail(userService.getByActivationCode(code).getUsername()));
        userService.saveRegistrUser(code);
        return "index";
    }
}
