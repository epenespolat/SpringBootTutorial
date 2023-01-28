package com.enespolat.tutorials.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
public class BeanDto {
    private  Long id;
    private  String beanName;
    private  String beanData;

    public  void initialBeanMethod(){
        log.info("Bean başlamadan önce");
        System.out.println("Bean başlamadan önce çalışacak");
    }
    public  void destroyBeanMethod(){
        log.info("Bean bittikten sonra");
        System.out.println("Bean bittikten sonra çalışacak");
    }

}
