package com.cqut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:mybatis-config.properties")
@MapperScan("com.cqut.mapper")
public class DaoConfiguration {

}
