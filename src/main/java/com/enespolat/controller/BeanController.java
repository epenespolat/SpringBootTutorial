package com.enespolat.controller;

import com.enespolat.bean.BeanConfig;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
public class BeanController {
    @Autowired
    BeanConfig beanConfig;


    @GetMapping("/bean/beanDto") // http://localhost:8080/bean/beanDto
    @ResponseBody
    public String getBeanDto(){

        return beanConfig.beanDto()+ "";
    }

    @Autowired
    Logger LOG;

    /*public BeanController() {
        LOG.info("Log info çağırıldı");
    }*/
    @PostConstruct
    public void init(){
        LOG.info("Log info çağırıldı");
    }

    public static void main(String[] args) {
        BeanController beanController= new BeanController();
        System.out.println(beanController);
    }


}
