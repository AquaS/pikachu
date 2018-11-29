package com.oee.pikachu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@ServletComponentScan
public class PikachuApplication extends SpringBootServletInitializer {

//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        return new ServletRegistrationBean(new WechatCoreServlet(), "/wechatSign");
//    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(PikachuApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(PikachuApplication.class, args);
    }


}
