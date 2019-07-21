package com.ydcoding.configuration;

import org.springframework.context.annotation.Bean;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Configuration;


import java.util.Properties;


/**
 * @program: honeysalesplatform
 * @description
 * @author: ydcoding
 * @create: 2019-05-12 17:05
 **/
@Configuration
public class KaptchaConfiguration {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.font.color", "red");
        properties.setProperty("kaptcha.noise.color", "red");
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //properties.setProperty("kaptcha.border.color", "105,179,90");
        //properties.setProperty("kaptcha.image.width", "120");
        //properties.setProperty("kaptcha.image.height", "45");
        //properties.setProperty("kaptcha.textproducer.font.size", "30");
        //properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}
