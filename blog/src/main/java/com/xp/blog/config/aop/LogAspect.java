package com.xp.blog.config.aop;
import com.xp.blog.entity.LogEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
@Aspect//定义切面
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    //定义切点  execution(方法修饰符 返回类型 方法所属的包.类名.方法名称(方法参数)
    @Pointcut("execution(public * com.xp.blog.controller.*.*(..))")
    public void log(){

    }

    //前置通知  通知方法会在目标方法调用之前执行
    @Before("log()")
    public void doBefore(){
        LOGGER.info("...............前置通知执行了.............");
    }

    //后置通知 通知方法会在目标方法返回或抛出异常后执行
    @After("log()")
    public void doAfter(){
        LOGGER.info("...............后置通知执行了.............");
    }


    //通知方法会在目标方法返回后执行
    @AfterReturning(pointcut = "log()", returning = "ret")
    public void doAfterReturning(Object ret){
        LOGGER.info("--------------处理完请求返回内容----------{}",ret);
    }

    //通知方法会在目标方法抛出异常后执行
    @AfterThrowing(pointcut = "log()")
    public void doAfterThrowing(){
        LOGGER.info("---------------方法发生异常时执行---------------");
    }

    //通知包裹了目标方法，在目标方法调用之前和之后执行自定义的行为。
    @Around("log()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        //获取当前请求对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //记录请求信息
        LogEntity logEntity = new LogEntity();
        Object result = joinPoint.proceed();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        //获取swaggger注解上面的描述
       /* if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            webLog.setDescription(apiOperation.value());
        }*/
       long endTime = System.currentTimeMillis();

       logEntity.setBasePath(signature.getDeclaringTypeName()+"."+signature.getName());
       logEntity.setIp(request.getRemoteAddr());
       logEntity.setMethod(request.getMethod());
       logEntity.setParameter(getParameter(method,joinPoint.getArgs()));
       logEntity.setResult(result);
       logEntity.setSpendTime((int) (endTime - startTime));
       logEntity.setStartTime(startTime);
       logEntity.setUri(request.getRequestURI());
       logEntity.setUrl(request.getRequestURL().toString());
        LOGGER.info("{}",logEntity);
       return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }

}
