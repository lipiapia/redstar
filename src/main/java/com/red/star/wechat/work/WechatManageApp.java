package com.red.star.wechat.work;

import com.red.star.wechat.data.utils.CheckUtil;
import com.red.star.wechat.work.core.security.MyInvocationSecurityMetadataSource;
import com.red.star.wechat.work.site.admin.service.ResourceService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.red.star.wechat.work", "com.red.star.wechat.data.core.config", "com.red.star.wechat.data"})
@MapperScan(basePackages = {"com.red.star.wechat.data.mappers", "com.red.star.wechat.work.site"})
@EnableSwagger2
@EnableCaching
@EnableConfigurationProperties
public class WechatManageApp implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatManageApp.class);

    public static void main(String[] args) {
        SpringApplication.run(WechatManageApp.class, args);
    }


    @Autowired
    @Qualifier(value = "resService")
    private ResourceService resourceService;

    @Override
    public void run(String... args) {
        if (CheckUtil.isEmpty(MyInvocationSecurityMetadataSource.resourceMap)) {
            MyInvocationSecurityMetadataSource.resourceMap = resourceService.getResourceMap();
        }
    }
}
