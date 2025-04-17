package com.example;
public class GoFunc {
    static {
        System.loadLibrary("native-sign");
        System.setProperty("GODEBUG", "invalidptr=1");
        System.setProperty("GOEXPERIMENT", "cgocheck2");
    }
    public static native String Dispatch(String var0);
}