package com.example;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Main {

    static ArrayList<String> datas = new ArrayList();
    static Random rand = new Random(System.currentTimeMillis());
    static boolean firstPrint = false;

    static class MyClass {
        private String parseAddr(String input) {
            String result = SdkUtils.transformAddress(input, "ton");
            CommandResponse response = JSONObject.parseObject(result, CommandResponse.class);
             if (!firstPrint) {
                firstPrint = true;
                System.out.printf("response=%s result=%s input=%s\n", response, result, input);
            }
            if(response.getCode() != 0 ) {
                 return "";
            }
            JSONArray jsonArray = JSONArray.parseArray(String.valueOf(response.getData() ));
            for (Object o : jsonArray) {
                JSONObject jo = (JSONObject) o;
                if (0 == jo.getInteger("type")) {
                     return jo.getString("addr");
                }
            }
            return "";
        }
        public void myFunction() {
            //System.out.printf("run at %s\n", Thread.currentThread().getName());
//             for (int i = 0; i < datas.size(); i++) {
                int randomIndex = rand.nextInt(datas.size());
                String input = datas.get(randomIndex);
                parseAddr(input);
//             }
            //System.out.printf("end at %s\n", Thread.currentThread().getName());
        }
    }

	public static void main(String[] args) {
	    try (BufferedReader br = new BufferedReader(new FileReader("data/testdata2.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                datas.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("testdata.size=%d\n", datas.size());

		SpringApplication.run(Main.class, args);
	}

    @GetMapping("/index")
    public String index() {
        MyClass myObject = new MyClass();
        myObject.myFunction();
        return "";
    }
}
