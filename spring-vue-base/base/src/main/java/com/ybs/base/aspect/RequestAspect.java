package com.ybs.base.aspect;

import com.alibaba.fastjson.JSON;
import com.ybs.base.enums.StateEnums;
//import com.ybs.base.pojo.Log;
//import com.ybs.base.service.LogService;
import com.ybs.base.utils.StringUtils;
import com.ybs.base.utils.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面输出基本信息
 * 以及记录日志
 *
 * @author: 杨德石
 * @date: 2019/8/5 13:22
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

//    @Autowired
//    private LogService logService;

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * com.ybs.*.controller..*(..))")
    public void logPointCut() {
    }

    /**
     * 方法执行之前调用
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时 : {}", time);
       /* Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogTime(time);*/
        return ob;
    }

    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        String result = JSON.toJSONString(ret);
        log.info("返回值：{}",result);
       /* Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogResult(result);
        logService.save(logger);*/
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
       /* Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogStatus(StateEnums.REQUEST_ERROR.getCode());
        String exception = StringUtils.getPackageException(e, "com.jg");
        logger.setLogMessage(exception);
        logger.setLogTime(0L);
        logService.save(logger);*/
    }

    /**
     * 打印请求日志
     *
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        // 拿到切面方法
        log.info("请求地址 : {}", uri);
        log.info("请求方式 : {}", request.getMethod());
        // 获取真实的ip地址
        String ip = StringUtils.getRemoteIp(request);
        log.info("IP : {}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("方法 : {}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());
        log.info("请求参数：{}", params);
        // 获取日志实体
        String params_es = Arrays.toString(joinPoint.getArgs());
        log.info("请求参数：{}", params_es);



       /* Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogUrl(uri);
        logger.setLogParams(params);
        logger.setLogStatus(StateEnums.REQUEST_SUCCESS.getCode());
        logger.setLogMethod(request.getMethod());
        logger.setLogIp(ip);*/
    }

}
