package com.zhangyuan.app.leakcanary;

/**
 * Created by zhangyuan on 17/11/24.
 */

public class OutterClass {

    private String name;

    class Inner{
        public void list(){
            System.out.println("outter name is " + name);
        }
    }
}
