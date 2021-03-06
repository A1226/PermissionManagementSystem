package com.itheima.service.Impl;

import com.itheima.dao.SysLogDao;
import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    //保存日志
    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
    //查询所有日志
    @Override
    public List<SysLog> findAll() {
        return sysLogDao.findAll();
    }
}
