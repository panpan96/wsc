package com.youngor.wsc.common;


/**
 * @Auther zhoutf
 * @Date 2019/1/8 9:18
 * @Description 产生唯一值 UUID作为主键ID
 */
public class GlobalFuns {

    public static String  getUUID(){

        return  java.util.UUID.randomUUID().toString().replaceAll("-", "");

    }

//    public static void main(String[] args) {
//        System.out.println(getUUID() );
//    }
}
