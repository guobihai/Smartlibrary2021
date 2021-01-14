package com.smart.textrunapp.utils;

public class Text {
    public static void textSub(){
        String value = "123/456";
        String s = value.substring(0,value.indexOf("/"));
        String s1 = value.substring(value.indexOf("/")+1,value.length());
        System.out.println("s===="+s);
        System.out.println("s1===="+s1);
    }
}
