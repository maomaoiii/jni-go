package com.example;
public class GoFunc {
    static {
        System.loadLibrary("native-sign");
        System.setProperty("GODEBUG", "invalidptr=1");
        System.setProperty("GOEXPERIMENT", "cgocheck2");
        System.setProperty("GOGC", "30");
    }
    public static native String Dispatch(String var0);
    public static native String Dispatch2(String var0);
}