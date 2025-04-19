package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.JSON;


@RestController
@SpringBootApplication
public class DemoApplication {
    @Autowired
    private CallSdk callSdk;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("/addr")
    public String addr() {
        String ret = "";
        for (int i =0; i < 100; i++) {
            ret = callSdk.parseAddr();
        }
        CommandResponse resp = new CommandResponse();
        resp.setData(ret);
        return JSON.toJSONString(resp);
    }
     @GetMapping("/tx")
     public String tx() {
         String ret = "";
         for (int i =0; i < 100; i++) {
             ret = callSdk.calcTxId();
         }
         CommandResponse resp = new CommandResponse();
         resp.setData(ret);
         return JSON.toJSONString(resp);
     }

}