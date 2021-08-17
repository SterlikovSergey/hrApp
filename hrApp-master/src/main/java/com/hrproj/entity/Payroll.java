package com.hrproj.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="payroll")
public class Payroll {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", length = 11, nullable = false)
    private long id;

    @Column(name = "date")//Дата начисления
    private Date date;
    @Column(name = "accrued")//Начисленно
    private double accrued;
    @Column(name = "allowances")//Надбавки
    private double allowances;
    @Column(name = "hold")//Удержано
    private double hold;
    @Column(name = "total")
    private double total;


    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_employee", nullable = false)
    private Employee employee;

    public Payroll() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAccrued() {
        return accrued;
    }

    public void setAccrued(double accrued) {
        this.accrued = accrued;
    }

    public double getAllowances() {
        return allowances;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public double getHold() {
        return hold;
    }

    public void setHold(double hold) {
        this.hold = hold;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
