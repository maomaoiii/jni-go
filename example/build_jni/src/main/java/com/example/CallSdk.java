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
import org.springframework.stereotype.Service;

@Service
public class CallSdk {
        Random rand = new Random();
        static ArrayList<String> datas = new ArrayList();
        static ArrayList<String> txs = new ArrayList();
        static {
                    datas.add("");
                    datas.add("\n");
                    datas.add("asdfasdf\0asdfasdf");
             try (BufferedReader br = new BufferedReader(new FileReader("testdata.txt"))) {
                                String line;
                                while ((line = br.readLine()) != null) {
                                    datas.add(line);
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                    }
             try (BufferedReader br = new BufferedReader(new FileReader("tx.txt"))) {
                                             String line;
                                             while ((line = br.readLine()) != null) {
                                                 txs.add(line);
                                             }
                                         } catch (IOException e) {
                                             e.printStackTrace();
                                 }

                    System.out.printf("testdata.size=%d tx.size=%d\n", datas.size(), txs.size());
        }
        static boolean firstPrint = false;
        private String parseAddr1(String input) {
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

        public String parseAddr() {
            //System.out.printf("run at %s\n", Thread.currentThread().getName());
            String ret = "";
            for (int i = 0; i < 1; i++) {
                String input = datas.get( rand.nextInt(datas.size()) );
                ret = parseAddr1(input);
            }
            return ret;
            //System.out.printf("end at %s\n", Thread.currentThread().getName());
        }

        public String calcTxId() {
            String input = txs.get( rand.nextInt(txs.size()) );
            String result = SdkUtils.calcTxId(input, "bitcoin");
            CommandResponse response = JSONObject.parseObject(result, CommandResponse.class);
            if (!firstPrint) {
                firstPrint = true;
                System.out.printf("response=%s result=%s input=%s\n", response, result, input);
            }
            if(response.getCode() != 0 ) {
                return "";
            }
            return (String) response.getData();
        }
}
