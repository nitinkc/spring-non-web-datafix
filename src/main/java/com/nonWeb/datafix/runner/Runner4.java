package com.nonWeb.datafix.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.nonWeb.datafix.util.DateTimeUtil.getLocalDateTime;

@Component
@Slf4j
@Order(value = 4)
@ConditionalOnExpression("${myDataFix4:false}")
public class Runner4 implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Starting Runner 4");
        //Do processing

        log.info(("Ending Runner 4 at : " + getLocalDateTime()));
    }
}
