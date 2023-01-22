package com.enespolat.bean;

import com.enespolat.dto.BeanDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
@Bean(initMethod = "initialBeanMethod",destroyMethod = "destroyBeanMethod")
@Scope("singleton")
    public BeanDto beanDto(){

        return BeanDto.
                builder().
                    id(0L).beanName("bean Adi").beanData("bean data")
                .build();
    }
}
