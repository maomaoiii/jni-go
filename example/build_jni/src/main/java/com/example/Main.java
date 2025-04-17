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

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2000);
        CallSdk myObject = new CallSdk();
        for (int j = 0; j < 2000000; j++) {
            executor.execute(() -> {
                myObject.parseAddr();
            } );
        }
        try {
            Thread.sleep(1000 * 1000000);
        } catch (Exception e) {

        }
    }
}