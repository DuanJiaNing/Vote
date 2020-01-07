package com.duan.vote.config;

import com.duan.vote.utils.SpringUtil;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Created on 2019/11/25.
 *
 * @author DuanJiaNing
 */
@Configuration
public class AppConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.setApplicationContext(applicationContext);
    }
}
