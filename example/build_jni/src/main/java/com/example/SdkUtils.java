package com.example;

import com.alibaba.fastjson.JSON;

public class SdkUtils {
    private static final String TransformAddress = "transform_address";

    public static String transformAddress(String address, String coinName) {
        CommandParam param = new CommandParam("transform_address", coinName, address);

        return GoFunc.Dispatch2(JSON.toJSONString(param));
    }

}