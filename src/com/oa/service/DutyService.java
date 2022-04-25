package com.oa.service;

import com.oa.pojo.Duty;
import com.oa.pojo.DutyData;

import java.sql.Date;
import java.util.List;

public interface DutyService {
    public Duty findBy(String empid, Date date);

    public int signIn(Duty duty);
    public int signOut(Duty duty);
    public List<Duty> showDutys();
    public List<DutyData> findMultyDuty(DutyData dutyData);
}
