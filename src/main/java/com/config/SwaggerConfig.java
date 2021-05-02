package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.ArrayList;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/5/2
 * @modify
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket docketFile() {
        return new Docket(DocumentationType.OAS_30).groupName("hdfs-api")
                .apiInfo(apiInfoFile()).select()
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(PathSelectors.ant("/file/**"))
                .build();
    }

    private ApiInfo apiInfoFile() {
        Contact contact = new Contact("何小树", "https://blog.csdn.net/hesqlplus730", "2810030998@qq.com");
        return new ApiInfo("文件控制器","hdfs控制文件上传,目录编辑等","v1.0","http://localhost/",contact,
                "","",new ArrayList<>()
        );
    }

    @Bean
    public Docket docketUser() {
        return new Docket(DocumentationType.OAS_30).groupName("user-api")
                .apiInfo(apiInfoUser()).select()
                .apis(RequestHandlerSelectors.basePackage("com.controller"))
                .paths(PathSelectors.ant("/user/**"))
                .build();
    }

    private ApiInfo apiInfoUser() {
       Contact contact = new Contact("何小树", "https://blog.csdn.net/hesqlplus730", "2810030998@qq.com");
       return new ApiInfo("用户控制器","用户新增,编辑等","v1.0","http://localhost/",contact,
               "","",new ArrayList<>()
       );
    }


}
