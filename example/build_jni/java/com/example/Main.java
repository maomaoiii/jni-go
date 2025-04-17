package com.example;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

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
            for (int i = 0; i < 1000; i++) {
                String input = datas.get( rand.nextInt( datas.size() ) );
                parseAddr(input);
            }
            //System.out.printf("end at %s\n", Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("testdata.txt"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        datas.add(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
        }
        System.out.printf("testdata.size=%d\n", datas.size());
        ExecutorService executor = Executors.newFixedThreadPool(5000);
        MyClass myObject = new MyClass();
        for (int j = 0; j < 2000000; j++) {
            executor.execute(() -> {
                myObject.myFunction();
            } );
        }
        try {
            Thread.sleep(1000 * 1000000);
        } catch (Exception e) {

        }
    }
}