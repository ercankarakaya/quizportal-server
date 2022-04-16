package com.ercan.aspects;

import com.ercan.annotations.LogEntryExit;
import com.ercan.utils.LogUtils;
import org.apache.logging.log4j.*;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.ercan.QuizportalServerApplication.main(..))")
    public void main() {
    }

    @Pointcut("within(@org.springframework.stereotype.Repository *)"
            + " || within(@org.springframework.stereotype.Service *)"
            + " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        throw new UnsupportedOperationException();
    }


    @Pointcut("within(com.ercan.repositories..*) || within(com.ercan.services.impl..*)")
    public void applicationPackagePointcut() {
        throw new UnsupportedOperationException();
    }


    @Pointcut("within(com.ercan.controllers..*)")
    public void controllersPointcut() {throw new UnsupportedOperationException();
    }

    @Pointcut("@annotation(com.ercan.annotations.LogEntryExit)")
    public void logEntryExitPointcut() {throw new UnsupportedOperationException();}


    @Around("controllersPointcut() && !logEntryExitPointcut()")
    public Object logAllMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        final String joinPoints = Arrays.toString(joinPoint.getArgs());
        if (joinPoints != null) {
            logger.info("Enter : {}.{}() with arguments[s] = {} ",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(),
                    Arrays.toString(joinPoint.getArgs()));
        }
        try {
            final StopWatch stopWatch = new StopWatch();

            //Measure method execution time
            stopWatch.start();
            Object result = joinPoint.proceed();
            stopWatch.stop();

            logger.info("Exit : {}.{}() with result = {}",
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);

            //Log method execution time
            //logger.info("Total process time :: {}", stopWatch.getTotalTimeMillis() + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            logger.error("Illegal argument : {} in {}.{}()",
                    Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName());
            throw e;
        }
    }

    /**
     * Alternative log method.
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("logEntryExitPointcut()")
    public Object logAllSaveMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        try {

            CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();

            var annotation = method.getAnnotation(LogEntryExit.class);
            LogLevel level = annotation.value();
            ChronoUnit unit = annotation.unit();
            boolean showArgs = annotation.showArgs();
            boolean showResult = annotation.showResult();
            boolean showExecutionTime = annotation.showExecutionTime();
            String methodName = method.getName();
            Object[] methodArgs = joinPoint.getArgs();
            String[] methodParams = codeSignature.getParameterNames();

            LogUtils.log(logger, level, LogUtils.entry(methodName, showArgs, methodParams, methodArgs));

            var start = Instant.now();
            var response = joinPoint.proceed();
            var end = Instant.now();
            var duration = String.format("%s %s", unit.between(start, end), unit.name().toLowerCase());

            LogUtils.log(logger, level, LogUtils.exit(methodName, duration, response, showResult, showExecutionTime));

            return response;
        } catch (Exception e) {
            throw e;
        }
    }

    @AfterThrowing(value = "applicationPackagePointcut()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        logger.error("EXCEPTION : "+ ex);
    }

}
