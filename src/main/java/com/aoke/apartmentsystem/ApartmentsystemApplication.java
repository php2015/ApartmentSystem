package com.aoke.apartmentsystem;

import com.aoke.apartmentsystem.common.properties.FebsProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableTransactionManagement
@MapperScan("com.aoke.apartmentsystem.*.mapper")
@EnableConfigurationProperties({FebsProperties.class})
@EnableCaching
@EnableAsync
public class ApartmentsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApartmentsystemApplication.class, args);
        System.out.println("公寓启动成功");
    }

}
