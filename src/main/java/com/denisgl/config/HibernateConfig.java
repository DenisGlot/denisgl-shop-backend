package com.denisgl.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"com.denisgl.dto"})
@EnableTransactionManagement
public class HibernateConfig {
}
