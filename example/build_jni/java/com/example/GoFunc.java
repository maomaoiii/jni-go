package com.example;
public class GoFunc {
    static {
        System.loadLibrary("native-sign");
    }
    public static native String Dispatch(String var0);
}