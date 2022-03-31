package com.xiezhixian.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <Description> LogAspect 日志切面配置
 *
 * @author 26802
 * @version 1.0
 * @ClassName LogAspect
 * @taskId
 * @see com.xiezhixian.blog.aspect
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //配置切面
    @Pointcut("execution(* com.xiezhixian.blog.controller.*.*(..))") //配置切入点 execution([方法修饰符] 包的完整路径.[类].[方法](参数))
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        //        logger.info("-----before-------");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = attributes.getRequest();
        String url = httpServletRequest.getRequestURL().toString();
        String ip = httpServletRequest.getRemoteAddr(); //获取ip
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request : {}",requestLog);
    }

    @After("log()")  //After才是最终通知
    public void doAfter(){
        logger.info("-----after-------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")  //AfterReturn是后置通知
    public void doAfterReturn(Object result){
        logger.info("result,{}",result);
    }


    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog() {
        }

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
