package com.enespolat.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class PostContructorTutorial {

    @Autowired
    Logger LOG;

    /*public BeanController() {
        LOG.info("Log info çağırıldı");
    }*/
    // bean henüz başlamadığından null pointer exception alıyoruz ve burada bağımlılık enjekte edilmiyor.
    // görevi: Bir bean nesnesi oluşturulduğunda hemen oluşur
    @PostConstruct
    public void init(){
        LOG.info("Log info çağırıldı");
    }

    public static void main(String[] args) {
        PostContructorTutorial beanController= new PostContructorTutorial();
        System.out.println(beanController);
    }


}
