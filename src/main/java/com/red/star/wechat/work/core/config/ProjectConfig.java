package com.red.star.wechat.work.core.config;

import com.red.star.wechat.data.entity.SysParam;
import com.red.star.wechat.data.mappers.SysParamMapper;
import com.red.star.wechat.data.utils.CheckUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.MultipartConfigElement;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author AMGuo
 * @Description
 * @date 2018/03/06 16:15
 */
@Configuration
public class ProjectConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectConfig.class);

    @Autowired
    private SysParamMapper sysParamMapper;

    @PostConstruct
    public void postConstruct() {
        initSysParam();
    }

    /**
     * 初始服务器参数
     *
     * @throws Exception
     */
    private void initSysParam() {
        // 获取所有配置的环境变量
        List<Map<String, String>> list = sysParamMapper.findSysParam();
        Map<String, String> params = new HashMap();
        list.stream()
                .filter(map -> map.containsKey("param_key") && map.containsKey("param_value"))
                .forEach(map -> {
                    String paramKey = map.get("param_key");
                    String paramValue = map.get("param_value");
                    params.put(paramKey, paramValue);
                });
        if (!CheckUtil.isEmpty(params)) {
            Class clazz = SysParam.class;
            // 获取所有配置属性
            Field[] fields = clazz.getFields();
            Arrays.stream(fields)
                    .filter(Objects::nonNull)
                    .forEach(field -> {
                        // 获取数据库配置的环境变量值(根据配置名称获取)
                        String paramKey = field.getName();
                        String paramValue = params.get(paramKey);
                        if (!CheckUtil.isEmpty(paramValue)) {
                            try {
                                if (field.getType() == Integer.class) {
                                    field.set(null, Integer.valueOf(paramValue));
                                } else {
                                    field.set(null, paramValue);
                                }
                            } catch (Exception e) {
                                LOGGER.error("can not find config for {}", paramKey);
                                e.printStackTrace();
                            }
                        }
                    });
        }
        System.out.println("init SysParam success...");
    }

    /**
     * 文件上传大小设置
     */
    @Bean
    public MultipartConfigElement createMultipartConfigElement() {
        MultipartConfigFactory mcf = new MultipartConfigFactory();
        mcf.setMaxFileSize("50MB");
        mcf.setMaxRequestSize("100MB");
        return mcf.createMultipartConfig();
    }
}
