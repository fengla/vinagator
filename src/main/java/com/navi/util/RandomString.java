package com.navi.util;

import java.util.Random;

public class RandomString {

    public static String getString(int length){

        char[] charArray = new char[length];

        for(int i=0;i<length;i++){

            Random r = new Random();
            int n = r.nextInt(123);
            while(n<48 || (n>57 && n<65) || (n>90 && n <97) || n>122){//(!((n>=48 && n<=57) || (n>=65 && n<=90) && (n>=97 && n<=122))){
                n = r.nextInt(123);
            }
            charArray[i] = (char)n ;
        }

        return String.valueOf(charArray) ;

    }

    //此方法可以用来生成用户id(实际使用的时候需要先查询一下，如果数据库中已经有这个数据了就重新再生成while循环，直到数据库中不存在新的这个id)
    //生成仅仅有数字组成的字符串
    public static String getNumString(int length){

        char[] charArray = new char[length];

        for(int i=0;i<length;i++){
            Random r = new Random();
            int n = 48 + r.nextInt(10);
            if(n<=57 && n>=48){//(!((n>=48 && n<=57) || (n>=65 && n<=90) && (n>=97 && n<=122))){
                charArray[i] = (char)(n) ;//todo: 0会不会出现？
            }

        }

        return String.valueOf(charArray) ;

    }

    public static void main(String args[]){
        int i=100;
        while(i>0){
            String uid = new RandomString().getNumString(10);//长度为10位的id
            System.out.println("uid:"+uid);
            i--;
        }
        //todo:用户用10位，doc用8位的id
        //上传的文件名字呢？也用8位随机命名（所有上传的文件可以都用这个统一的方法，位数来实现，但是数据库查询去重的时候只需要判断子系统中是否有重读就可以了）
//        String uid = new RandomString().getNumString(8);
//        System.out.println("uid:"+uid);
    }
}
