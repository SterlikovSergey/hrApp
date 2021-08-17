package com.hrproj.controller;

import com.hrproj.entity.*;
import com.hrproj.entity.enums.RoleEnum;
import com.hrproj.service.impl.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Controller
public class HrController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private CandidateServiceImpl candidateService;
    @Autowired
    private PayrollServiceImpl payrollService;
    @Autowired
    private TimetableServiceImpl timetableService;
    @Autowired
    private CertificateServiceImpl certificateService;
    @Autowired
    private ChatMessageServiceImpl chatMessageService;
    @Autowired
    private AnketaServiceImpl anketaService;
    @Autowired
    private MailSenderServiceIml mailSender;
    @Autowired
    LogServiceImpl logService;

    @Value("${upload.path}")
    private  String uploadPath;
    @Value("${upload.pathJ}")
    private  String uploadPathJ;


    @GetMapping("/hr")
    public String hrList(Model model) {
        Employee employee = employeeService.getInfoEmployee();
        model.addAttribute("name", employee.getName()+" "+employee.getSurname());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("birthday", format.format(employee.getBirthday()));
        model.addAttribute("mobphone", employee.getMobphone());
        model.addAttribute("email", employee.getEmail());
       model.addAttribute("gender", employee.getGender());
        model.addAttribute("department", employee.getPost().getDepartment());
        model.addAttribute("post", employee.getPost().getPost());
        model.addAttribute("uploadPath",uploadPath);
       model.addAttribute("photo",employee.getPhoto());
        model.addAttribute("facebook",employee.getFacebookLink());
        model.addAttribute("twitter",employee.getTwitterLink());
        model.addAttribute("linked",employee.getLinkedLink());
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
        return "hr";
    }
    @GetMapping("/hr/certificate")
    public String  certificateHR(Model model) {
        List<Certificate> certificates = certificateService.getAll();
        model.addAttribute("CertificateForm", certificates);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.getByEmail(auth.getName());

        List<Certificate> certificateList = certificateService.getByIdEmployee(employee.getId());
        model.addAttribute("certificateList", certificateList);

        model.addAttribute("name", employee.getSurname()+" "+employee.getName());
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        model.addAttribute("date", date);

        Log log = new Log();
        log.setInfo("Вошёл");
        log.setUser(userService.getByUsername(auth.getName()));
        log.setTime(new Date());
        logService.addLog(log);

        return "hr_certificate";
    }
    @GetMapping("/hr/interviewer")
    public String  interviewerHR(Model model) {
        List<Timetable> timetables = timetableService.getByType("resume");
        model.addAttribute("resumeForm", timetables);
        return "hr_interviewer";
    }
    @GetMapping("/hr/diagram")
    public String  diagramEmployee(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employeeForm", employees);
        List<Candidate> candidates = candidateService.getAll();
        model.addAttribute("candidateForm", candidates);
        return "hr_diagram";
    }
    @GetMapping("/hr/event")
    public String  eventHR(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.getByEmail(auth.getName());
//        List<Timetable> timetables = timetableService.getByTypeAndIdEmployee("resume",employee.getId());
//        model.addAttribute("resumeForm", timetables);
        List<Timetable> events = timetableService.getByIdEmployee(employee.getId());
        model.addAttribute("eventForm", events);
        return "hr_event";
    }
    @GetMapping("/hr/profile")
    public String  profileHR(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employeeForm", employees);
        List<Candidate> candidates = candidateService.getAll();
        model.addAttribute("candidateForm", candidates);

        return "hr_profile";
    }
    @GetMapping("/hr/report")
    public String  reportEmployee(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.getByEmail(auth.getName());
        List<Payroll> payrolls = payrollService.getByIdEmployee(employee.getId());
        model.addAttribute("payrollForm", payrolls);
        return "hr_report";
    }
    @GetMapping("/hr/staffingTable")
    public String  staffingTableHR(Model model) {
        return "hr_staffing_table";
    }


    @GetMapping("/hr/statistics")
    public String  statisticsHR(Model model) {
        List<Anketa> anketaList = anketaService.getAll();
        List<Employee> employeeList = employeeService.getAll();
        if(anketaList!=null) {
            Double[] question4 = anketaService.question4Percent(anketaList);
            Double[] question5 = anketaService.question5Percent(anketaList);
            Double[] question1 = anketaService.question1Percent(anketaList);
            Double[] question2 = anketaService.question2Percent(anketaList);
            model.addAttribute("question4Perfect", question4[0]);
            model.addAttribute("question4Good", question4[1]);
            model.addAttribute("question4Ok", question4[2]);
            model.addAttribute("question4Bad", question4[3]);
            model.addAttribute("question5Hour", question5[0]);
            model.addAttribute("question5Day", question5[1]);
            model.addAttribute("question5Week", question5[2]);
            model.addAttribute("question5Year", question5[3]);
            model.addAttribute("question1Yes", question1[0]);
            model.addAttribute("question1No", question1[1]);
            model.addAttribute("question2Yes", question2[0]);
            model.addAttribute("question2No", question2[1]);
        }
        Double[] gender = employeeService.genderPercent(employeeList);
        model.addAttribute("woman", gender[0]);
        model.addAttribute("man", gender[1]);
        Double[] age = employeeService.getAgeGroup(employeeList);
        model.addAttribute("aTwenty", age[0]);
        model.addAttribute("twenty30", age[1]);
        model.addAttribute("thirty40", age[2]);
        model.addAttribute("forty50", age[3]);
        model.addAttribute("fiftyPlus", age[4]);
        Double[] payroll = payrollService.calcMiddlePayroll();
        model.addAttribute("one", payroll[0]);
        model.addAttribute("two", payroll[1]);
        model.addAttribute("three", payroll[2]);
        model.addAttribute("four", payroll[3]);
        model.addAttribute("five", payroll[4]);
        model.addAttribute("six", payroll[5]);
        model.addAttribute("seven", payroll[6]);
        model.addAttribute("eight", payroll[7]);
        model.addAttribute("nine", payroll[8]);
        model.addAttribute("ten", payroll[9]);
        model.addAttribute("eleven", payroll[10]);
        model.addAttribute("twelve", payroll[11]);

        return "hr_statistics";
    }

    @GetMapping("/hr/message")
    public String  message(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.getByEmail(auth.getName());
        List<Candidate> candidates = candidateService.getByHREmail(employee.getEmail());
        model.addAttribute("candidate",null);
        model.addAttribute("candidates", candidates);
        model.addAttribute("code", 0);
        model.addAttribute("username", employee.getUser().getUsername());
        model.addAttribute("to",  "");
        List<ChatMessage> messagesList = null;
        model.addAttribute("list",  messagesList);
        return "hr_message";
    }

    @GetMapping("/hr/message-{code}")
    public String  messageHR(Model model, @PathVariable String code) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.getByEmail(auth.getName());
        List<Candidate> candidates = candidateService.getByHREmail(employee.getEmail());
        model.addAttribute("candidates", candidates);
        Candidate candidate = candidateService.getBySessionCode(code);
        model.addAttribute("photo",candidate.getPhoto());
        model.addAttribute("candidate",candidate.getName()+" "+candidate.getSurname());
        model.addAttribute("code",code);
        model.addAttribute("username", employee.getUser().getUsername());
        model.addAttribute("to",  candidate.getEmail());
        List<ChatMessage> messagesList = chatMessageService.getByToTwice(employee.getUser().getId(), candidate.getUser().getId());
        Comparator<ChatMessage> comparator = new Comparator<ChatMessage>() {
            @Override
            public int compare(ChatMessage left, ChatMessage right) {
                return (int) (left.getId() - right.getId());
            }
        };
        messagesList.sort(comparator);
        model.addAttribute("list",  messagesList);
        return "hr_message";
    }


    @PostMapping("/hr")
    public String addPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        Employee employee = employeeService.getInfoEmployee();
        if (file != null) {
            File uploadDir = new File(uploadPathJ);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPathJ+"/"+resultFileName));

            employee.setPhoto(resultFileName);
            employeeService.updateEmployee(employee);
        }
        return "redirect:/hr";
    }

    @PostMapping("/hr/facebook")
    public String addFacebook(@RequestParam("facebookLink") String link) throws IOException {
        Employee employee = employeeService.getInfoEmployee();
        if (link != null) {
            employee.setFacebookLink(link);
            employeeService.updateEmployee(employee);
        }
        return "redirect:/hr";
    }

    @PostMapping("/hr/linked")
    public String addLinked(@RequestParam("linkedLink") String link) throws IOException {
        Employee employee = employeeService.getInfoEmployee();
        if (link != null) {
            employee.setLinkedLink(link);
            employeeService.updateEmployee(employee);
        }
        return "redirect:/hr";
    }

    @PostMapping("/hr/twitter")
    public String addtwitter(@RequestParam("twitterLink") String link) throws IOException {
        Employee employee = employeeService.getInfoEmployee();
        if (link != null) {
            employee.setTwitterLink(link);
            employeeService.updateEmployee(employee);
        }
        return "redirect:/hr";
    }

    @PostMapping("/hr/certificate")
    public String addCertificate(@RequestParam("typeCertificate") String type){ //, @RequestParam("numCert") String numCert
        if(type!=null) {
            Employee employee = employeeService.getInfoEmployee();
            Certificate certificate = new Certificate();
            certificate.setOrderDate(new Date());
            certificate.setPercent(0);
            certificate.setType(type);
            certificate.setEmployee(employee);
            certificateService.addCertificate(certificate);
        }
        return "redirect:/hr/certificate";
    }
    @PostMapping("/hr/certificate/percent")
    public String setPercentCertificate(@RequestParam("id") long id,
                                 @RequestParam("percent") int percent){
            Certificate certificate = certificateService.getById(id);
            certificate.setPercent(percent);
            certificateService.editCertificate(certificate);
        return "redirect:/hr/certificate";
    }

    @PostMapping("/hr/interviewer/delete")
    public String deleteInterview(@RequestParam("id") long id) {
        timetableService.deleteById(id);
        return "redirect:/hr/interviewer";
    }

    @PostMapping("/download/fullReport.xlsx")
    public void   getFullReport( HttpServletResponse response, @RequestParam("month") int month,
                                 Model model) throws IOException {
        List<Payroll> payrolls = payrollService.getMonthReport(month);
        int amountPeople = payrollService.getAmountPeople(month);
        List<Integer> amountPeoplePost = payrollService.getAmountPeoplePost(month);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=fullReport.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(payrolls,month,amountPeople,amountPeoplePost);
        IOUtils.copy(stream, response.getOutputStream());

    }

    @PostMapping("/hr/staffingTable/download/report.xlsx")
    public String  getReport(@RequestParam("department") String department,
                             @RequestParam("month") int month, HttpServletResponse response,
                             Model model) throws IOException {
        List<Payroll> payrolls = payrollService.getMonthReport(month);
        int amountPeople = payrollService.getAmountPeople(month);
        List<Integer> amountPeoplePost = payrollService.getAmountPeoplePost(month);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=fullReport.xlsx");
        ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(payrolls,month,amountPeople,amountPeoplePost);
        IOUtils.copy(stream, response.getOutputStream());

        return "hr_staffing_table";
    }


    @PostMapping("/hr/interviewer/edit")
    public String setInterview(@RequestParam("id") long id,
                               @RequestParam("EmailE") String mailE,
                               @RequestParam("EmailC") String mailC,
                               @RequestParam("startedOn") String timeStart,
                               @RequestParam("dob") String dateStart) {
        Employee employee = employeeService.getByEmail(mailE);
        Candidate candidate = candidateService.getByEmail(mailC);
        Timetable timetable = new Timetable();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            timetable.setId(id);
            String start = dateStart+" "+timeStart;
            String end = dateStart+" "+timeStart;
            Date dateS= format.parse(start);
            LocalDateTime timeS = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Date dateE= format.parse(end);
            LocalDateTime timeE = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            timetable.setStartDate(dateS);
            timetable.setStartTime(timeS);
            timetable.setEndDate(dateE);
            timetable.setEndTime(timeE);
            timetable.setEmployee(employee);
            timetable.setCandidate(candidate);
            timetable.setType("resume");
            timetable.setColor("#FFDEAD");
            timetableService.editTimetable(timetable);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/hr/interviewer";
    }

    @PostMapping("/hr/interviewer/add")
    public String setInterview(@RequestParam("EmailE") String mailE,
                               @RequestParam("EmailC") String mailC,
                               @RequestParam("startedOn") String timeStart,
                               @RequestParam("dob") String dateStart) {
        Employee employee = employeeService.getByEmail(mailE);
        Candidate candidate = candidateService.getByEmail(mailC);
        Timetable timetable = new Timetable();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            String start = dateStart+" "+timeStart;
            String end = dateStart+" "+timeStart;
            Date dateS= format.parse(start);
            LocalDateTime timeS = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Date dateE= format.parse(end);
            LocalDateTime timeE = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            timetable.setStartDate(dateS);
            timetable.setStartTime(timeS);
            timetable.setEndDate(dateE);
            timetable.setEndTime(timeE);
            timetable.setEmployee(employee);
            timetable.setCandidate(candidate);
            timetable.setType("resume");
            timetable.setColor("#FFDEAD");
            timetableService.addTimetable(timetable);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/hr/interviewer";
    }



    @PostMapping("/hr/event")
    public String setEvent(@RequestParam("dateStart") String dateStart,
                           @RequestParam("timeStart") String timeStart,
                           @RequestParam("dateEnd") String dateEnd,
                           @RequestParam("timeEnd") String timeEnd,
                           @RequestParam("type") String type,
                           Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeService.getByEmail(auth.getName());
        Timetable timetable = new Timetable();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String[] colors = {"#FFDEAD","#ADD8E6","#4fab40", "#DDA0DD"};
        Random random = new Random();
        try {
            String start = dateStart+" "+timeStart;
            String end = dateEnd+" "+timeEnd;
            Date dateS= format.parse(start);
            LocalDateTime timeS = LocalDateTime.parse(start, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Date dateE= format.parse(end);
            LocalDateTime timeE = LocalDateTime.parse(end, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            timetable.setStartDate(dateS);
            timetable.setStartTime(timeS);
            timetable.setEndDate(dateE);
            timetable.setEndTime(timeE);
            timetable.setEmployee(employee);
            timetable.setType(type);
            timetable.setColor(colors[random.nextInt(colors.length)]);
            timetableService.addTimetable(timetable);
        } catch (ParseException| DateTimeParseException e) {
            e.printStackTrace();
            model.addAttribute("dateError", "Неверный ввод даты или времени");
            return "hr_event";
        }
        return "redirect:/hr/event";
    }

    @PostMapping("/hr/profile/employee")
    public String setWorkEmployee(@RequestParam("email") String email){ //, @RequestParam("numCert") String numCert
        if(email!=null) {
            Employee employee = employeeService.getByEmail(email);
            if(employee.getIsWorking()==1)
                employee.setIsWorking(0);
            else
                employee.setIsWorking(1);
            employeeService.editEmployee(employee);
        }
        return "redirect:/hr/profile";
    }
    @PostMapping("/hr/profile/candidate")
    public String setWorkCandidate(@RequestParam("email") String email){ //, @RequestParam("numCert") String numCert
        if(email!=null) {
            Candidate candidate = candidateService.getByEmail(email);

            User user = userService.getByUsername(email);
            if(user!=null) {
                user.setActivationCode(UUID.randomUUID().toString());
                userService.editUser(user);

                String message = String.format("Здравствуйте, %s!\n"
                        + "Поздравляем! Вас приняли на работу!"
                        + "Вот ваша ссылка на заполнение данных: " +
                        "http://localhost:8080/inf/%s", user.getUsername(), user.getActivationCode());
                mailSender.sendActivationPage(email, message);
            }
            //employeeService.editEmployee(employee);
            Employee employee = new Employee();
            employee.setEmail(candidate.getEmail());
            employee.setName(candidate.getName());
            employee.setSurname(candidate.getSurname());
            employee.setBirthday(candidate.getBirthday());
            employee.setGender(candidate.getGender());
            employee.setIsWorking(1);
            employee.setMobphone(candidate.getMobphone());
            employee.setUser(candidate.getUser());
            employee.setPhoto(candidate.getPhoto());
            employeeService.addEmployee(employee);
            user.setRole(RoleEnum.ROLE_WORKER);
            userService.editUser(user);
            candidateService.deleteById(candidate.getId());
        }
        return "redirect:/hr/profile";
    }
}
