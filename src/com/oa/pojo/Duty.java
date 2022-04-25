package com.oa.pojo;

import java.io.Serializable;
import java.sql.Date;

public class Duty implements Serializable {
    private String deptname;
    private String realname;

    private Integer dtid;

    private String emprid;

    private Date dtdate;

    private String signintime;

    private String signouttime;

    private static final long serialVersionUID = 1L;

    public Integer getDtid() {
        return dtid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }



    public void setDtid(Integer dtid) {
        this.dtid = dtid;
    }

    public String getEmprid() {
        return emprid;
    }

    public void setEmprid(String emprid) {
        this.emprid = emprid == null ? null : emprid.trim();
    }

    public Date getDtdate() {
        return dtdate;
    }

    public void setDtdate(Date dtdate) {
        this.dtdate = dtdate;
    }

    public String getSignintime() {
        return signintime;
    }

    public void setSignintime(String signintime) {
        this.signintime = signintime == null ? null : signintime.trim();
    }

    public String getSignouttime() {
        return signouttime;
    }

    public void setSignouttime(String signouttime) {
        this.signouttime = signouttime == null ? null : signouttime.trim();
    }

    @Override
    public String toString() {
        return "Duty{" +
                "deptname='" + deptname + '\'' +
                ", realname='" + realname + '\'' +
                ", dtid=" + dtid +
                ", emprid='" + emprid + '\'' +
                ", dtdate=" + dtdate +
                ", signintime='" + signintime + '\'' +
                ", signouttime='" + signouttime + '\'' +
                '}';
    }
}