package com.hrproj.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name="candidate")

public class Candidate {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id_candidate", length = 11, nullable = false)
    private long id;
    @Column(name = "surname")
    private String surname;
    @Column(name = "name")
    private String name;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "mob_phone")
    private String mobphone;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name="photo")
    private String photo;
    @Column(name="facebookLink")
    private String facebookLink;
    @Column(name="linkedLink")
    private String linkedLink;
    @Column(name="twitterLink")
    private String twitterLink;
    @Column(name="HrEmail")
    private String HrEmail;
    @Column(name="sessionCode")
    private String sessionCode;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name="id_user", unique = true, nullable = false, updatable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Timetable> timetableSet;

    @OneToOne(mappedBy="candidate", cascade = CascadeType.ALL)
    public Anketa anketa;

    public Candidate() {
    }

    public Candidate(String surname, String name, Date birthday, String mobphone, String email,  String gender, User user, Set<Timetable> timetableSet) {
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.mobphone = mobphone;
        this.email = email;
        this.gender = gender;
        this.user = user;
        this.timetableSet = timetableSet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobphone() {
        return mobphone;
    }

    public void setMobphone(String mobphone) {
        this.mobphone = mobphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Timetable> getTimetableSet() {
        return timetableSet;
    }

    public void setTimetableSet(Set<Timetable> timetableSet) {
        this.timetableSet = timetableSet;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Anketa getAnketa() {
        return anketa;
    }

    public void setAnketa(Anketa anketa) {
        this.anketa = anketa;
    }

    public String getHrEmail() {
        return HrEmail;
    }

    public void setHrEmail(String hrEmail) {
        HrEmail = hrEmail;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getLinkedLink() {
        return linkedLink;
    }

    public void setLinkedLink(String linkedLink) {
        this.linkedLink = linkedLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }
}
