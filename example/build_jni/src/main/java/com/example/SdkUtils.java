package com.example;

import com.alibaba.fastjson.JSON;

public class SdkUtils {
    private static final String TransformAddress = "transform_address";
    private static final String  CalcTxId = "calc_tx_id";

    public static String transformAddress(String address, String coinName) {
        CommandParam param = new CommandParam(TransformAddress, coinName, address);

        return GoFunc.Dispatch(JSON.toJSONString(param));
    }

    public static String calcTxId(String data, String coinName) {
        CommandParam param = new CommandParam(CalcTxId, coinName, data);
        return GoFunc.Dispatch(JSON.toJSONString(param));
    }
}