package com.hrproj.entity;

import com.hrproj.entity.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name="user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(generator = "native")
    @GenericGenerator(name= "native", strategy= "native")
    @Column(name = "id_user", length = 11, nullable = false)
    private long id_user;

    @Transient
    private String passwordConfirm;


    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @Column(name = "role")
    private RoleEnum role;

    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="activation_code")
    private String activationCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userFrom", cascade = CascadeType.ALL)
    private Set<ChatMessage> chatMessagesFrom;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userTo", cascade = CascadeType.ALL)
    private Set<ChatMessage> chatMessagesTo;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Log> logs;

//    @Column(name="access")
//    @Enumerated(EnumType.STRING)
 //   private RoleEnum access;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    public Employee employee;

    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    private Candidate candidate;

    //for registration
    @Transient
    private Person person=new Person();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        list.add(new SimpleGrantedAuthority(role.getAuthority()));

        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    //for registration
    public String getIdP() {
        return person.getId();
    }

    public void setIdP(String id) {
        this.person.setId(id);
    }

    public String getSurname() {
        return person.getSurname();
    }

    public void setSurname(String surname) {
        this.person.setSurname(surname);
    }

    public String getName() {
        return person.getName();
    }

    public void setName(String name) {
        this.person.setName(name);
    }

    public String getPatronymic() {
        return person.getPatronymic();
    }

    public void setPatronymic(String patronymic) {
        this.person.setPatronymic(patronymic);
    }

    public String getBirthday() {
        String date ="";
        if(person.getBirthday()!=null)
            if(!person.getBirthday().equals(""))
                date = new SimpleDateFormat("dd/MM/yyyy").format(person.getBirthday());
        return date;
    }

    public void setBirthday(String birthdayS) throws ParseException {
        if(birthdayS!=null) {
            if(!birthdayS.equals("")) {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date birthday = format.parse(birthdayS);
                this.person.setBirthday(birthday);
            }
        }
    }

    public String getMobphone() {
        return person.getMobphone();
    }

    public void setMobphone(String mobphone) {
        this.person.setMobphone(mobphone);
    }

    public String getGender() {
        return person.getGender();
    }

    public void setGender(String gender) {
        this.person.setGender(gender);
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        User user = (User) o;
        return getId_user() != null && Objects.equals(getId_user(), user.getId_user());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
