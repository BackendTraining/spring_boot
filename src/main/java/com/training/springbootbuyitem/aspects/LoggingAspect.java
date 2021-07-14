package com.training.springbootbuyitem.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.MDC;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import static com.training.springbootbuyitem.constant.BuyItemConstant.LOGGING_HANDLER_PROCESS_TIME_MSG;
import static com.training.springbootbuyitem.constant.BuyItemConstant.OPERATION_HEADER;

@Slf4j
@Aspect
@EnableAspectJAutoProxy
@Component
public class LoggingAspect {

    private Long start;

    @Before("execution(* com.training.springbootbuyitem.controller.BuyController.*(..)) && @annotation(com.training.springbootbuyitem.utils.annotation.ServiceOperation)")
    public void beforeBuyController(JoinPoint joinPoint) {
        start = System.currentTimeMillis();
        MDC.put(OPERATION_HEADER, joinPoint.getSignature().getName());
    }

    @After("execution(* com.training.springbootbuyitem.controller.BuyController.*(..)) && @annotation(com.training.springbootbuyitem.utils.annotation.ServiceOperation)")
    public void afterBuyController() {
        Long end = System.currentTimeMillis();
        log.info(String.format(LOGGING_HANDLER_PROCESS_TIME_MSG, end - start));
    }

}
