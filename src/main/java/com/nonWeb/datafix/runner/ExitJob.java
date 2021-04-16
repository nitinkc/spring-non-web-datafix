package com.nonWeb.datafix.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
public class ExitJob implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        log.info("Exiting the execution");
        System.exit(0);

    }
}
