package com.cpswork.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootTest
class CpsWorkApplicationTests {

    @Test
    void contextLoads() {
    }

}
