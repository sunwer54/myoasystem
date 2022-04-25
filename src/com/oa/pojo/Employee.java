package com.oa.pojo;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.sql.Date;  //java.sql.Date只包含年月日;而java.util.Date包含年月日和时分秒
import java.util.List;

public class Employee implements Serializable {
    //持有部门所为属性
    private Dept dept;
    //持有职位作为属性
    private Position position;

    private String empid;

    private String password;

    private Integer deptno;

    private Integer posid;

    private String mgrid;

    private String realname;

    private String sex;

    private Date birthdate;

    private Date hiredate;
    private Date leavedate;//默认是null 是不能转成java.sql.Date类型
    private Integer onduty;

    private Integer emptype;

    private String phone;

    private String qq;

    private String emercontactperson;

    private String idcard;

    public Employee() {
    }

    public Employee(String empid, Integer deptno, Integer posid, String mgrid, String realname, String sex, Date birthdate, Date hiredate, Date leavedate, Integer onduty, Integer emptype, String phone, String qq, String emercontactperson, String idcard) {
        this.empid = empid;
        this.deptno = deptno;
        this.posid = posid;
        this.mgrid = mgrid;
        this.realname = realname;
        this.sex = sex;
        this.birthdate = birthdate;
        this.hiredate = hiredate;
        this.leavedate = leavedate;
        this.onduty = onduty;
        this.emptype = emptype;
        this.phone = phone;
        this.qq = qq;
        this.emercontactperson = emercontactperson;
        this.idcard = idcard;
    }



    private static final long serialVersionUID = 1L;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid == null ? null : empid.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Integer getPosid() {
        return posid;
    }

    public void setPosid(Integer posid) {
        this.posid = posid;
    }

    public String getMgrid() {
        return mgrid;
    }

    public void setMgrid(String mgrid) {
        this.mgrid = mgrid == null ? null : mgrid.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public Integer getOnduty() {
        return onduty;
    }

    public void setOnduty(Integer onduty) {
        this.onduty = onduty;
    }

    public Integer getEmptype() {
        return emptype;
    }

    public void setEmptype(Integer emptype) {
        this.emptype = emptype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getEmercontactperson() {
        return emercontactperson;
    }

    public void setEmercontactperson(String emercontactperson) {
        this.emercontactperson = emercontactperson == null ? null : emercontactperson.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "dept=" + dept +
                ", position=" + position +
                ", empid='" + empid + '\'' +
                ", password='" + password + '\'' +
                ", deptno=" + deptno +
                ", posid=" + posid +
                ", mgrid='" + mgrid + '\'' +
                ", realname='" + realname + '\'' +
                ", sex='" + sex + '\'' +
                ", birthdate=" + birthdate +
                ", hiredate=" + hiredate +
                ", leavedate=" + leavedate +
                ", onduty=" + onduty +
                ", emptype=" + emptype +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", emercontactperson='" + emercontactperson + '\'' +
                ", idcard='" + idcard + '\'' +
                '}';
    }
}