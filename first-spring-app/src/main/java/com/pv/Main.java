package com.pv;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        // no object creation
        // TransferService transferService = new TransferService(new TransferRepository());
        
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        TransferService transferService = context.getBean(TransferService.class);
        transferService.execute();
    }
}