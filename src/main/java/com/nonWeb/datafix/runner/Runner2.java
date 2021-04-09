package com.nonWeb.datafix.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.nonWeb.datafix.util.DateTimeUtil.getLocalDateTime;

@Component
@Slf4j
@Order(value = 2)
@ConditionalOnExpression("${myDataFix2:false}")
public class Runner2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Starting Runner 2");
        //Do processing

        log.info(("Ending Runner 2 at : " + getLocalDateTime()));
    }
}
