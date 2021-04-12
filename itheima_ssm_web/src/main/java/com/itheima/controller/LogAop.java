package com.itheima.controller;


import com.itheima.domain.SysLog;
import com.itheima.service.SysLogService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.jar.JarFile;

@Component
@Aspect
public class LogAop {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private HttpServletRequest request;//需要在web.xml中配置RequestContextListener

    private Date visitTime;//开始时间
    private Class calzz;//访问的类
    private Method method;//方法的方法

    /**
     * 前置通知  主要获取开始时间，执行哪一个类，执行的是哪一个方法
     * @param joinPoint
     */
    @Before("execution(* com.itheima.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        visitTime = new Date();//开始时间
        calzz = joinPoint.getTarget().getClass();//具体访问的类
        String methodName = joinPoint.getSignature().getName();//获取访问的方法名称
        Object[] args = joinPoint.getArgs();//获取访问的方法参数

        //获取具体执行的方法的method对象
        if (args == null || args.length == 0){
            method = calzz.getMethod(methodName);//只能获取无参的方法
        }else {
            Class[] classes = new Class[args.length];
            for (int i = 0;i < classes.length;i++){
                classes[i] = args[i].getClass();
            }
            calzz.getMethod(methodName,classes);//获取有参的方法
        }
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @After("execution(* com.itheima.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint){
        long date = new Date().getTime() -  visitTime.getTime();//获取访问时长

        String url = "";
        //获取URL
        if (calzz!=null&&method!=null&&calzz!=LogAop.class){

            //1.获取类上的@RequestMapping("/product")的value
            RequestMapping classAnnotation = (RequestMapping) calzz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null){
                String[] classValue = classAnnotation.value();

                //2.获取方法上的@RequestMapping("xxxxx")的value
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();

                    url = classValue[0]+methodValue[0];

                    //获取ip地址
                    String ip = request.getRemoteAddr();

                    //获取当前操作的用户
                    SecurityContext getContext = new SecurityContextHolder().getContext();
                    User principal = (User) getContext.getAuthentication().getPrincipal();
                    String username = principal.getUsername();

                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(date);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setMethod("[类名] " + calzz.getName() + "[方法名] " + method.getName());
                    sysLog.setVisitTime(visitTime);

                    //调用service完成保存
                    sysLogService.save(sysLog);
                }
            }
        }

    }
}
