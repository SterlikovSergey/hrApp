package com.hrproj.controller;

import com.hrproj.entity.*;
import com.hrproj.entity.enums.RoleEnum;
import com.hrproj.service.LogService;
import com.hrproj.service.UserService;
import com.hrproj.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
public class AdminController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private CandidateServiceImpl candidateService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private OrganizationStructureServiceImpl organizationStructureService;
    @Autowired
    private ChatMessageServiceImpl chatMessageService;


    @GetMapping("/admin")
    public String userList(Model model) {
        List<Log> logs = logService.getByInfo("Вошёл");
        model.addAttribute("logs", logs);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String ipAddress ="";
        Object details =
                SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof WebAuthenticationDetails) {
            ipAddress = ((WebAuthenticationDetails) details).getRemoteAddress();
            Log log = new Log();
            log.setInfo("Вошёл " + ipAddress);
            log.setUser(userService.getByUsername(auth.getName()));
            log.setTime(new Date());
            logService.addLog(log);
        }
        return "admin";
    }

    @GetMapping("/admin/changes")
    public String adminchanges(Model model) {
        model.addAttribute("changes", logService.getChanges());

        return "admin_changes";
    }
    @GetMapping("/admin/employee")
    public String workEmployee(Model model) {
        model.addAttribute("employees", employeeService.getAll());

        return "admin_employee";
    }
    @GetMapping("/admin/user")
    public String workUser(Model model) {
        model.addAttribute("candidates", candidateService.getAll());

        return "admin_user";
    }
    @GetMapping("/admin/2")
    public String admin2(Model model) {
        model.addAttribute("admins", userService.getAdmin());
        return "admin_2";
    }

    @PostMapping("/admin/employee/delete")
    public String adminEmployeedelete(@RequestParam("id") long id) {
        Employee employee = employeeService.getByIdE(id);
        long idUser = employee.getUser().getId_user();
        userService.deleteById(idUser);
        employeeService.deleteById(id);
        if(employee.getPost().getPost().equals("hr")) {
            List<Candidate> candidates = candidateService.getByHREmail(employee.getEmail());
            List<Employee> HrList = employeeService.getAllHr();
            Random rand = new Random();
            for (Candidate candidate: candidates) {
                candidate.setHrEmail(HrList.get(rand.nextInt(HrList.size())).getEmail());
                candidateService.editCandidate(candidate);
            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Удален сотрудник "+ employee.getSurname() + " "+ employee.getName());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "redirect:/admin/employee";
    }
    @PostMapping("/admin/employee/edit")
    public String adminEmployeeEdit(@RequestParam("id") long id,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("department") String department,
                               @RequestParam("post") String post,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        Employee employee = employeeService.getByIdE(id);
        User user = employee.getUser();
        if(name!=null)
            employee.setName(name);
        if(surname!=null)
            employee.setSurname(surname);
        if(department!=null || post!=null) {
            if(department==null)
                department = employee.getPost().getDepartment();
            if(post==null)
                post = employee.getPost().getPost();
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
            employee.setPost(organizationStructure);
        }
        if(email!=null) {
            employee.setEmail(email);
            user.setUsername(email);
        }
        if(password!=null) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
        }
        userService.editUser(user);
        employeeService.editEmployee(employee);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Изменена информация о сотруднике "+ employee.getSurname() + " "+employee.getName());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

            return "redirect:/admin/employee";
    }

    @PostMapping("/admin/user/delete")
    public String adminCandidateDelete(@RequestParam("id") long id) {
        Candidate candidate = candidateService.getById(id);
        long idUser = candidate.getUser().getId_user();
        userService.deleteById(idUser);
        candidateService.deleteById(id);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Удален соискатель "+ candidate.getSurname() + " "+candidate.getName());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "redirect:/admin/user";
    }
    @PostMapping("/admin/user/edit")
    public String adminCandidateEdit(@RequestParam("id") long id,
                                    @RequestParam("name") String name,
                                    @RequestParam("surname") String surname,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password) {
        Candidate candidate = candidateService.getById(id);
        User user = candidate.getUser();
        if(name!=null)
            candidate.setName(name);
        if(surname!=null)
            candidate.setSurname(surname);
        if(email!=null) {
            candidate.setEmail(email);
            user.setUsername(email);
        }
        if(password!=null) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
        }
        userService.editUser(user);
        candidateService.editCandidate(candidate);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Изменена информация о соискателе "+ candidate.getSurname() + " "+candidate.getName());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "redirect:/admin/user";
    }


    @PostMapping("/admin/2/delete")
    public String adminDelete(@RequestParam("id") long id) {
        User user = userService.getById_user(id);
        long idUser = user.getId_user();
        userService.deleteById(idUser);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Удален admin " + user.getUsername());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "redirect:/admin/2";
    }
    @PostMapping("/admin/2/edit")
    public String adminEdit(@RequestParam("id") long id,
                                     @RequestParam("username") String username,
                                     @RequestParam("password") String password) {
        User user = userService.getById_user(id);
        if(username!=null) {
            user.setUsername(username);
        }
        if(password!=null) {
            user.setPassword(bCryptPasswordEncoder.encode(password));
        }
        userService.editUser(user);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Изменена информация о admin "+ user.getUsername());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "redirect:/admin/2";
    }

    @PostMapping("/admin/2/add")
    public String adminAdd(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole(RoleEnum.ROLE_ADMIN);
        userService.addUser(user);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Log log = new Log();
        log.setInfo("Добавлен новый admin "+ user.getUsername());
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "redirect:/admin/2";
    }

//    @PostMapping("/admin")
//    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
//                             @RequestParam(required = true, defaultValue = "") String action,
//                             Model model) {
//        if (action.equals("delete")) {
//            userService.deleteUser(userId);
//        }
//        return "redirect:/admin";
//    }

//    @GetMapping("/logout")
//    public String logout(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("DSITK");
//        Log log = new Log();
//        log.setInfo("Вышел");
//        log.setUser(userService.getByUsername(auth.getName()));
//        log.setTime(new Date());
//        logService.addLog(log);
//        return "/logout";
//    }

}
