/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.floor.advice;

import com.mycompany.floor.dao.LoggingDao;
import java.util.ArrayList;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Becca
 */
public class LoggingAdvice {

    LoggingDao dao;
    ArrayList<String> log = new ArrayList<>();

    public LoggingAdvice(LoggingDao dao) {
        this.dao = dao;
    }

    public void logAdd(JoinPoint jp) {
        String auditEntry = "";
        Object[] args = jp.getArgs();
        String date = args[1].toString();
        String orderNum = args[2].toString();
        String name = jp.getSignature().getName();
        auditEntry = name + "," + date + "," + orderNum;
        log.add(auditEntry);

    }
    public void logRemove(JoinPoint jp) {
        String auditEntry = "";
        Object[] args = jp.getArgs();
        String date = args[0].toString();
        String orderNum = args[1].toString();
        String name = jp.getSignature().getName();
        auditEntry = name + "," + date + "," + orderNum;
        log.add(auditEntry);

    }
    public void logEdit(JoinPoint jp) {
        String auditEntry = "";
        Object[] args = jp.getArgs();
        String date = args[1].toString();
        String orderNum = args[0].toString();
        String name = jp.getSignature().getName();
        auditEntry = name + "," + date + "," + orderNum;
        log.add(auditEntry);

    }

    public void logSave() {
        for (String entry : log) {
            dao.write(entry);

        }
        log.clear();
    }
}
