package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName AppProducer
 * Description  TODO
 * @Author Administrator
 * @Date 2020/12/14 20:40
 * @Version 1.0
 */
@SpringBootApplication
// 打开事务
@EnableTransactionManagement
public class AppProducer {

    public static void main(String[] args) {
        SpringApplication.run(AppProducer.class);
    }

}
