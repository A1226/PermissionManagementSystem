package com.itheima.service;

import com.itheima.domain.SysLog;

import java.util.List;

public interface SysLogService {
    //日志保存
    void save(SysLog sysLog);

    //查询所有日志
    List<SysLog> findAll();
}